<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>班级列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="${basePath}manage/classroom!add.action" method="post">
        <!-- 服务端验证错误信息 -->
        <s:fielderror fieldName="classroom.name"></s:fielderror><br/>
        <span>班级名称：</span>
        <input type="text" name="classroom.name" value=""/><br/>
        <span>班级编号：</span>
        <input type="text" name="classroom.code" value=""/><br/>
        <input type="submit"/>
    </form>
    
    <a href="${basePath}manage/classroom!list.action">返回班级列表</a>
</body>
</html>