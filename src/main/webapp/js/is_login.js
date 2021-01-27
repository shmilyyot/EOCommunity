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