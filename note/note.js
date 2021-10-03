window.onload = function(){
    if(!window.name){
        localStorage.setItem("content","");
        localStorage.setItem("ms","");
        window.name = "ok";
    }
    else if(window.name === "ok"){
        console.log(window.name);

    }
}
function encrypt(){ //显示修改界面
    let condition = document.getElementById("key-area").style.display;
    if(condition === "none")
        document.getElementById('key-area').style.display = "block";
    else
        document.getElementById('key-area').style.display = "none";
}


//第一次设置密匙
 // 第一次输入内容
function change(){
    let content = document.getElementById("content").value;
    let ms = document.getElementById("ms").value;

    let con = localStorage.getItem("content");
    let flag = localStorage.getItem("ms");

    if(!flag && !ms && !con)  //第一次输入内容 没有密匙
    {
        if(content){
            localStorage.setItem("content", content);
            alert("修改成功");
        }else{
            alert("您有事吗？");
        }return;
    }else if(!flag && ms && !con){    //第一次输入内容 有密匙
        if(content){
            localStorage.setItem("content", content);
            localStorage.setItem("ms", ms);
            alert("修改成功");
        }else{
            alert("您有事吗？");
        }return;
    }

    if(!flag && !ms && con){ //不是第一次输入 之前无密匙 现在也没有
        if(content !== con) {
            localStorage.setItem("content",content);
            alert("修改成功");
        }
    else{
        alert("您有事吗？");
    }return;
    }else if(!flag && ms && con){   //不是第一次输入 之前无密匙 现在要设置密匙
        if(content !== con){
            localStorage.setItem("content",content);
            localStorage.setItem("ms",ms);
            alert("修改成功");
        }
    else{
        alert("您有事吗？");
    }return;}

      //有密匙
    if(content !== con) {
        if(ms === flag){
            localStorage.setItem("content",content);
            alert("修改成功");
        }
        else if(ms !== flag){
            alert("密码错误");
        }
    }else{
        alert("您有事吗？");
    }
}

const share =async =>{
    //1.原生js
    let input = document.createElement("input");
    input.value = window.location.href;
    let url = input.value;
    navigator.clipboard.writeText(url);
    alert("已将链接复制至粘贴板");
}