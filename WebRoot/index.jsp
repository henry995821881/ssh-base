<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
%>
<body>
    <a href="${basePath}manage/classroom!list.action">班级列表</a>
    <a href="${basePath}manage/student!list.action">学生列表</a>
</body>