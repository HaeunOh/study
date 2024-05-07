console.log("board modify js!");
document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid;

        removeFileToServer(uuid).then(result => {
            if(result == 1){
                alert("파일 삭제 완료!");
                e.target.closest('li').remove();
            }
        })
    }
})

async function removeFileToServer(uuid){
    try {
        const url = "/board/"+uuid;
        const config = {
            method : 'delete'
        }
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}