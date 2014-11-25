<%@ page language="java" pageEncoding="utf-8"%>
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
    <title>添加学生</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="${basePath}manage/student!add.action" method="post">
        <h4>添加学生</h4>
        <span>所属班级：</span>
        <select name="student.classroom.id">
            <s:iterator var="c" value="#request.classroomList">
                <option value="<s:property value='#c.id'/>"><s:property value="#c.name"/></option>
            </s:iterator>
        </select><br/>
        <span>姓名：</span>
        <s:textfield name="student.name"/><br/>
        <span>学号：</span>
        <s:textfield name="student.code"/><br/>
        <s:submit value="添加"/>
    </form>
    <a href="${basePath}manage/student!list.action">返回列表</a>
    
    <%--
    <form action="${basePath}manage/fileupload.action" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"/><br/>
        <input type="file" name="upload"/><br/>
        <input type="submit"/>
    </form>
    --%>
    </body>
</html>