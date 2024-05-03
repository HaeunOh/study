console.log("boardRegister.js in");
//trigger 버튼 처리
document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('file').click();
});

//실행파일과 파일 최대 사이즈에 대한 정규표현식
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

//'change' => 해당 타겟에 변화가 생기면 바로 인지될 수 있게끔 설정
document.addEventListener('change', (e)=>{
    if(e.target.id == 'file'){ //파일에 변화가 생겼다면..
        //input type="file" element에 저장된 file의 정보를 가져오는 property
         const fileObj = document.getElementById('file').files;
        console.log(fileObj);

        //버튼을 미리 활성화시켜놓는 작업 => disabled 설정해놓으면 자동으로 풀리지 못해서...
        document.getElementById('regBtn').disabled = false;

        //등록된 file의 정보를 fileZone에 기록
        let div = document.getElementById('fileZone');
        div.innerHTML = '';

        //여러개의 등록파일이 모두 검증을 통과해야 하기 때문에 isOk * 로 각각 파일이 통과할 때마다 연산을 실행 => 통과 여부 확인
        let isOk = 1;
        let ul = ` <ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            //하나의 파일이 검증을 통과하는지 결과 확인
            let validResult = fileValidation(file.name, file.size);
            isOk *= validResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${validResult ? '<div class="fw-bold text-info"> 업로드 가능 </div>' : '<div class="fw-bold text-danger"> 업로드 불가능 </div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg-${validResult ? 'success' : 'danger'}">${file.size}bytes</span>`;
            ul += `</li>`;
        }

            ul += `</ul>`;
            div.innerHTML = ul;

            if(isOk == 0){
                //모든 파일이 통과하지 못했을 때
                document.getElementById('regBtn').disabled = true;
            }
    } 
})

