<%--
  Created by IntelliJ IDEA.
  User: 2500594037@qq.com
  Date: 2024/1/4
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
         isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>药品下单页</title>
</head>
<body>
<a href="/shopping/customer/selectedAll">获取所有用户</a>
<form action="/shopping/customer/selectedByTelephone" method="post">
    根据电话查找：<input type="text" name="id">
    <input type="submit" value="提交"/>
</form>
</body>
</html>
