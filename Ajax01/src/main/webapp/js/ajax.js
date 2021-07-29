//自定义Ajax函数
function ajax(items) {
    //1.创建原生Ajax请求对象
    let xhr = new XMLHttpRequest();//可以发送异步请求
    //2.获取请求方式
    let url=items.url; //请求路由
    let data=items.data; //请求参数
    let method = items.type; //请求类型

    if (method == "get" || method == "GET") {
        xhr.open("get", url+"?"+data);
        xhr.send();
    } else if (method == "post" || method == "POST") {
        xhr.open("post", url);
        xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xhr.send(data);
    }
    //处理服务器响应
    xhr.onload=function () {
        let text=xhr.responseText;
        items.success(text); //成功是返回服务器响应
    }

}

