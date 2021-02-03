<!--注册form表单非空判断-->
function registerCheckNull(form){
    if(form.accountName.value == null || form.accountName.value == ""){
        document.getElementById("errinfo1").innerText = "用户名不能为空";
        document.getElementById("errinfo1").style.display = "";
        form.accountName.focus();
        return false;
    }
    if(form.accountPassword.value == null || form.accountPassword.value == ""){
        document.getElementById("errinfo2").innerText = "密码不能为空";
        document.getElementById("errinfo2").style.display = "";
        form.accountPassword.focus();
        return false;
    }
    if(form.accountPassword2.value == null || form.accountPassword2.value == ""){
        document.getElementById("errinfo3").innerText = "密码不能为空";
        document.getElementById("errinfo3").style.display = "";
        form.accountPassword2.focus();
        return false;
    }
    document.getElementById("errinfo1").style.display = "none";
    document.getElementById("errinfo2").style.display = "none";
    document.getElementById("errinfo3").style.display = "none";
    if(form.accountPassword.value === form.accountPassword2.value) return true;
    else{
        document.getElementById("errinfo4").innerText = "两次密码输入不一致！！！";
        document.getElementById("errinfo4").style.display = "";
        return false;
    }
}

<!--注册form表单非空判断-->
function loginCheckNull(form){
    if(form.accountName.value == null || form.accountName.value == ""){
        document.getElementById("errinfo1").innerText = "用户名不能为空";
        document.getElementById("errinfo1").style.display = "";
        form.accountName.focus();
        return false;
    }
    if(form.accountPassword.value == null || form.accountPassword.value == ""){
        document.getElementById("errinfo2").innerText = "密码不能为空";
        document.getElementById("errinfo2").style.display = "";
        form.accountPassword.focus();
        return false;
    }
    document.getElementById("errinfo1").style.display = "none";
    document.getElementById("errinfo2").style.display = "none";
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