
<!--注册form表单非空判断-->
function registerCheckNull(form){
    var accountName = $("#accountName").val();
    var accountPassword = $("#accountPassword").val();
    var accountPassword2 = $("#accountPassword2").val();
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
    if(form.accountPassword.value != form.accountPassword2.value){
        document.getElementById("errinfo4").innerText = "两次密码输入不一致！！！";
        document.getElementById("errinfo4").style.display = "";
        return false;
    }
    return true;
}

<!--注册form表单非空判断-->
function loginCheckNull(form){
    var accountName = $("#accountName").val();
    var accountPassword = $("#accountPassword").val();
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
// 检查有没有获取到register
function registerCheckSuccess(){
    let res = getUrlParam("Success");
    if (res != null) {
        alert("注册成功,点击确认即跳转登陆界面");
        location.assign("login.html");
    }
}
function registerCheckError(){
    let res = getUrlParam("Error");
    if (res != null) {
        document.getElementById("errinfo1").innerText = "此用户名已被占用";
        document.getElementById("errinfo1").style.display = "";
    }
}

// /*
// 判断用户是否登录
//  */
// $(function () {
//     $.post('user/is_loginServlet', {}, function (info) {
//         let login_exit = $("#h_login_exit");
//         if (info.flag) {
//             // 已登录
//             let msg = "<b style='color:#000000;'>" + info.data.name + "</b>" + " ，欢迎您";
//             $("#h_userMsg").html(msg);
//             // 退出，删除session，返回主页
//             login_exit.html("退出");
//             login_exit.prop("href", "user/exitLoginServlet");
//
//         } else {
//             // 未登录
//             login_exit.html("请登录");
//             login_exit.prop("href", "login.html");
//         }
//     })
// })