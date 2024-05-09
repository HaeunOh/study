console.log("user register js in");

const EmailInput =  document.getElementById('e');
const pwdInput = document.getElementById('p');

document.getElementById('emailCheckBtn').addEventListener('click', ()=>{
    let myEmail = EmailInput.value;
    if(myEmail == null || myEmail.length == 0){
        alert('이메일을 입력해주세요!');
        document.getElementById('e').focus();
        return false;
    }else{
        console.log(myEmail);
        idCheck(myEmail).then(result => {
            if(result == '-1'){
                alert(myEmail+" 은 사용 가능한 이메일입니다.");
                pwdInput.focus();
            }else if(result == '1'){
                alert(myEmail+" 은 사용 불가능한 이메일입니다.");
                EmailInput.innerHTML = '';
                EmailInput.focus();
            }
        }) 
    }
})

async function idCheck(myEmail){
    try {
        const url = "/user/idCheck"
        const config = {
            method : "post",
            headers : {
                "content-type" : "text/plain; charset=utf-8"
            },
            body : myEmail
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}
