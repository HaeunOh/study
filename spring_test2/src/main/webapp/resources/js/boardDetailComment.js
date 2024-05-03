console.log("js in");
document.getElementById('cmtAddBtn').addEventListener('click', ()=>{
    const cmtWriter = document.getElementById('cmtWriter').innerText;
    const cmtText = document.getElementById('cmtText').value;
    if(cmtText == null || cmtText == ''){
        alert('댓글 내용을 입력해주세요!');
        document.getElementById('cmtText').focus();
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        }
        console.log(cmtData);
        postCommentToServer(cmtData).then(result =>{
            if(result == '1') {
                alert('댓글 등록 성공!');
                document.getElementById('cmtText').value = "";
                //화면에 뿌리기
                spreadCommentList(bnoVal);
            }
        })
    }
})

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post"
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset = utf-8"
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }

}

//댓글 뿌리는 서식
function spreadCommentList(bno, page=1) {
    getCommentListFromServer(bno, page).then(result => {
    console.log(result);
        const div = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0){
            if(page == 1){
                div.innerHTML = "";
            }
                for(let cvo of result.cmtList){
                let add = `<ul class="list-group list-group-flush" id="cmtListArea" data-cno="${cvo.cno}">`;
                add +=  `<li class="list-group-item"></li>`;
                add += ` <div class="mb-3 fw-bold"> no. ${cvo.cno} </div>`;
                add += `<div class="fw-bold">${cvo.writer}</div> <br>`;
                add += `<div class="myContent">${cvo.content}</div>`;
                add += `</div>`;
                add += `<span class="badge rounded-pill text-bg-dark">${cvo.regDate}</span>`;
                add += `<button type="button" data-cno="${cvo.cno}" class="btn btn-outline-warning btn-sm cmtModBtn" data-bs-toggle="modal" data-bs-target="#myModal" >수정</button>`;
                add += `<button type="button" data-cno="${cvo.cno}" class="btn btn-outline-danger btn-sm cmtDelBtn" id="cmtDelBtn">삭제</button>`;
                add += `</li> </ul>`;
                div.innerHTML += add;
            }
            //더보기 button 코드
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            //moreBtn이 표시되는 조건 :
            //realEndPage보다 현재 내 페이지(pgvo.pageNo) 가 작으면 표시... 
            if(result.pgvo.pageNo < result.realEndPage){
                moreBtn.style.visibility = 'visible';
                moreBtn.dataset.page = page+1;
            }else{
                moreBtn.style.visibility = 'hidden';
            }


        }else{
            div.innerHTML = `<li class="list-group-item"> no comment </li>`;
        }

    })
    

}

//댓글 가져오기
async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//댓글 더보기 버튼 클릭시 남은 댓글 뿌리기
document.addEventListener('click', (e)=>{
    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page); //해당 값은 숫자여야 함
        spreadCommentList(bnoVal, page);
    }
    else if(e.target.classList.contains('cmtModBtn')){
        console.log('click modal')
        //내가 수정버튼을 누른 댓글의 li 가져오기
        let ul = e.target.closest('ul');
        //nextSibling : 한 부모 안에서 다음 형제 찾기
        let cmtText = ul.querySelector('.myContent').innerText;
        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText;

        //수정 -> cno dataset으로 버튼에 달아주기 (modify)
        document.getElementById('cmtModBtn').setAttribute("data-cno", ul.dataset.cno);
        document.getElementById('cmtDelBtn').setAttribute("data-cno", ul.dataset.cno);
    }else if(e.target.id == 'cmtModBtn'){
        let cmtModData = {
            cno : e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value
        };

        console.log(cmtModData);
        postModCommentToServer(cmtModData).then(result =>{
            if(result == '1') {
                alert('댓글 수정 성공!');
                document.querySelector(".btn-close").click();
            }else{
                alert('수정 실패!');
                document.querySelector(".btn-close").click();
            }
            spreadCommentList(bnoVal);
        })

        //비동기로 수정 댓글 내용 보내기

    }else if(e.target.classList.contains('cmtDelBtn')){
        let delCno = e.target.dataset.cno;
        deleteCommentToServer(delCno).then(result => {
            if(result == '1'){
                alert("댓글 삭제 성공!");
                spreadCommentList(bnoVal);
            }
        })
    }
})

//모달창으로 수정받아서 댓글 수정하기
async function postModCommentToServer(cmtModData){
    try {
        const url = "/comment/modify";
        const config = {
            method : "PUT",
            headers : {
                "content-type" : "application/json; charset = utf-8"
            },
            body : JSON.stringify(cmtModData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }

}

async function deleteCommentToServer(cno){
    try {
        const url = "/comment/"+cno;
        const config = {
            method : "delete"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}


