<%--
  Created by IntelliJ IDEA.
  User: Letu
  Date: 2021/2/2
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%pageContext.setAttribute("ctp", request.getContextPath());%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="${ctp}/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${ctp}/js/jquery-3.5.1.min.js"></script>
    <!--    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>-->
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${ctp}/js/bootstrap.min.js"></script>
    <!--                                引入Bootstrap                             -->

    <script type="text/javascript" src="${ctp}/js/include.js"></script>
    <link href="${ctp}/css/login.css" rel="stylesheet">
    <title>EOCommunity社区首页</title>
    <link rel="shortcut icon" href="${ctp}/favicon.ico" type="image/x-icon"/>
    <!--    判断用户是否登录-->
    <script type="text/javascript" src="${ctp}/js/is_login.js"></script>

</head>
<body>
<div id="header"></div>
<div id="footer"></div>
</body>
</html>
