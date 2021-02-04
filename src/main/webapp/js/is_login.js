<!--注册form表单非空判断-->
function registerCheckNull(form){
    if(form.accountName.value == null || form.accountName.value === ""){
        document.getElementById("errinfo").innerText = "用户名不能为空";
        document.getElementById("errinfo").style.display = "";
        form.accountName.focus();
        return false;
    }
    if(form.accountPassword.value == null || form.accountPassword.value === ""){
        document.getElementById("errinfo").innerText = "";
        document.getElementById("errinfo").innerText = "密码不能为空";
        document.getElementById("errinfo").style.display = "";
        form.accountPassword.focus();
        return false;
    }
    if(form.accountPassword2.value == null || form.accountPassword2.value === ""){
        document.getElementById("errinfo").innerText = "密码不能为空";
        document.getElementById("errinfo").style.display = "";
        form.accountPassword2.focus();
        return false;
    }
    if(form.accountPassword.value === form.accountPassword2.value) return true;
    else{
        document.getElementById("errinfo").innerText = "两次密码输入不一致！！！";
        document.getElementById("errinfo").style.display = "";
        return false;
    }
}

<!--登录form表单非空判断-->
function loginCheckNull(form){
    if(form.accountName.value == null || form.accountName.value === ""){
        document.getElementById("errinfo").innerText = "";
        document.getElementById("errinfo").innerText = "用户名不能为空";
        document.getElementById("errinfo").style.display = "";
        form.accountName.focus();
        return false;
    }
    if(form.accountPassword.value == null || form.accountPassword.value === ""){
        document.getElementById("errinfo").innerText = "";
        document.getElementById("errinfo").innerText = "密码不能为空";
        document.getElementById("errinfo").style.display = "";
        form.accountPassword.focus();
        return false;
    }
    return true;
}

/*账户或密码错误跳转login页执行这两个方法*/
// 获取地址栏中的name参数
function getUrlParam(name) {
    return decodeURIComponent(
        (new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null
}
// 检查有没有获取到error
function loginCheckError() {
    let res = getUrlParam("error");
    if (res != null) {
        document.getElementById("loginError").innerText = "用户名或者密码错误";
        document.getElementById("loginError").style.display = "";
    }
}

//后期需要改进成鼠标失去焦点就自动检查
//检查用户名是否被占用
function registerCheckExist(form){
    var username = form.accountName.value;
    var status = true;
    $.ajax({
        url:"/account/findByName",    //请求的url地址
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        dataType:'json',
        data:{'username':username},    //参数值
        type:"POST",   //请求方式
        success:function(data){
            temp = JSON.parse(JSON.stringify(data))
            if(temp.username === "true"){
                status = false;
                document.getElementById("errinfo").innerText = "用户名已存在";
                document.getElementById("errinfo").style.display = "";
            }else{
                alert("注册成功，即将跳转登录页");
            }
        }
    })
    return status;
}