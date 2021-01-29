
<!--form表单非空判断-->
function checkNull(form){
    var accountName = $("#accountName").val();
    var accountPassword = $("#accountPassword").val();
    if(form.accountName.value == null || form.accountName.value == ""){
        alert("用户名不能为空");
        form.accountName.focus();
        return false;
    }
    if(form.accountPassword.value == null || form.accountPassword.value == ""){
        alert("密码不能为空");
        form.accountPassword.focus();
        return false;
    }
    return true;
}

/*
判断用户是否登录
 */
$(function () {
    $.post('user/is_loginServlet', {}, function (info) {
        let login_exit = $("#h_login_exit");
        if (info.flag) {
            // 已登录
            let msg = "<b style='color:black;'>" + info.data.name + "</b>" + " ，欢迎您";
            $("#h_userMsg").html(msg);
            // 退出，删除session，返回主页
            login_exit.html("退出");
            login_exit.prop("href", "user/exitLoginServlet");

        } else {
            // 未登录
            login_exit.html("请登录");
            login_exit.prop("href", "login.html");
        }
    })
})