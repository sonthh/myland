<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/publicUrl/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/adminUrl/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminUrl/css/style.css" rel="stylesheet">
</head>
<body>
    <tiles:insertAttribute name="header"></tiles:insertAttribute>
    <div class="page-content">
        <div class="row">
            <div class="col-md-2">
                <div class="sidebar content-box" style="display: block;">
                   <tiles:insertAttribute name="left-bar"></tiles:insertAttribute>
                </div>
            </div>
            <div class="col-md-10">
                <tiles:insertAttribute name="main"></tiles:insertAttribute>
            </div>
            <!-- /.col-md-10 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.page-content -->
    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
    <tiles:insertAttribute name="commonJs"></tiles:insertAttribute>
</body>
</html>
<!-- /.footer -->