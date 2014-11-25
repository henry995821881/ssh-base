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
    <title>班级列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- CSS 样式表 -->
    <link rel="stylesheet" href="${basePath}static/common/js/pager/kkpager.css" type="text/css"></link>
    <!-- JS 文件 -->
    <script type="text/javascript" src="${basePath}static/common/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${basePath}static/common/js/pager/kkpager.js"></script>
    
    <script type="text/javascript">
    	//init
        $(function(){
            var totalPage = ${pager.pageCount};
            var totalRecords = ${pager.totalCount};
            var pageNo = getParameter('currentPage');
            if(!pageNo){
                pageNo = 1;
            }
            //生成分页
            //有些参数是可选的，比如lang，若不传有默认值
            kkpager.generPageHtml({
                pno : pageNo,
                //总页码
                total : totalPage,
                //总数据条数
                totalRecords : totalRecords,
                //链接前部
                hrefFormer : '${basePath}manage/student!list',
                //链接尾部
                hrefLatter : '.action',
                getLink : function(n){
                    return this.hrefFormer + this.hrefLatter + "?currentPage="+n;
                }
            });
        });
    </script>
</head>
<body>
    <a href="${basePath}manage/student!toAdd.action">添加学生</a><br/>
    
    <s:iterator var="s" value="#request.pager.dataList">
        <s:property value="#s.name"/> - <s:property value="#s.code"/> - <s:property value="#s.classroom.name"/>
        <a href="${basePath}manage/student!toEdit.action?student.id=${s.id}">修改</a>
        <a href="${basePath}manage/student!delete.action?student.id=${s.id}">删除</a>
        <br/>
    </s:iterator>
    
    <!-- 分页 -->
    <div id="kkpager"></div>
</body>
</html>