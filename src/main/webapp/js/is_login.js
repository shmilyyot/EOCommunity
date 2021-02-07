<!--注册form表单非空判断-->

/*要改成只要非空就不能提交*/

function registerCheckNull(form){
    if(form.accountName.value == null || form.accountName.value === ""){
        document.getElementById("errinfo").innerText = "用户名不能为空";
        document.getElementById("errinfo").style.display = "";
        $("#accountName").attr("style","border:red 1px solid");
        $("#accountPassword").attr("style","border:black 1px solid");
        $("#accountPassword2").attr("style","border:black 1px solid");
        form.accountName.focus();
        return false;
    }else{
        $("#accountName").attr("style","border:black 1px solid");
    }
    if(form.accountPassword.value == null || form.accountPassword.value === ""){
        document.getElementById("errinfo").innerText = "密码不能为空";
        document.getElementById("errinfo").style.display = "";
        $("#accountPassword").attr("style","border:red 1px solid");
        $("#accountName").attr("style","border:black 1px solid");
        $("#accountPassword2").attr("style","border:black 1px solid");
        form.accountPassword.focus();
        return false;
    }else{
        $("#accountPassword").attr("style","border:black 1px solid");
    }
    if(form.accountPassword2.value == null || form.accountPassword2.value === ""){
        document.getElementById("errinfo").innerText = "密码不能为空";
        document.getElementById("errinfo").style.display = "";
        $("#accountPassword2").attr("style","border:red 1px solid");
        $("#accountPassword").attr("style","border:black 1px solid");
        $("#accountName").attr("style","border:black 1px solid");
        form.accountPassword2.focus();
        return false;
    }else{
        $("#accountPassword2").attr("style","border:black 1px solid");
    }
    if(form.accountPassword.value === form.accountPassword2.value) {
        $("#accountPassword2").attr("style","border:black 1px solid");
        $("#accountPassword").attr("style","border:black 1px solid");
        $("#accountName").attr("style","border:black 1px solid");
        return true;
    }
    else{
        document.getElementById("errinfo").innerText = "两次密码输入不一致！！！";
        document.getElementById("errinfo").style.display = "";
        $("#accountPassword").attr("style","border:red 1px solid");
        $("#accountPassword2").attr("style","border:red 1px solid");
        $("#accountName").attr("style","border:black 1px solid");
        form.accountPassword.focus();
        return false;
    }
}

<!--登录form表单非空判断-->
function loginCheckNull(form){
    if(form.accountName.value == null || form.accountName.value === ""){
        document.getElementById("errinfo").innerText = "用户名不能为空";
        document.getElementById("errinfo").style.display = "";
        $("#accountName").attr("style","border:red 1px solid");
        $("#accountPassword").attr("style","border:black 1px solid");
        form.accountName.focus();
        return false;
    }
    if(form.accountPassword.value == null || form.accountPassword.value === ""){
        document.getElementById("errinfo").innerText = "密码不能为空";
        document.getElementById("errinfo").style.display = "";
        $("#accountName").attr("style","border:black 1px solid");
        $("#accountPassword").attr("style","border:red 1px solid");
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
        document.getElementById("errinfo").innerText = "";
        document.getElementById("errinfo").innerText = "用户名或者密码错误";
        document.getElementById("errinfo").style.display = "";
        form.accountName.focus();
    }
}

