<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/publicUrl/images/favicon.ico">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/publicUrl/css/glyphicons.css" />
    <!--Oswald Font -->
    <link href="https://fonts.googleapis.com/css?family=Niramit:200,200i,300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=vietnamese" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/publicUrl/css/tooltipster.css" />
    <!-- home slider-->
    <link href="${pageContext.request.contextPath}/publicUrl/css/pgwslider.css" rel="stylesheet" >
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/publicUrl/css/font-awesome.min.css" >
    <link href="${pageContext.request.contextPath}/publicUrl/css/style.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/publicUrl/css/hover.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/publicUrl/css/responsive.css" rel="stylesheet" media="screen">
    <!-- style of wowslider -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/publicUrl/wowslider/style.css" />
</head>

<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
    <section id="content_area">
        <div class="clearfix wrapper main_content_area">

            <div class="clearfix main_content floatleft">
				<tiles:insertAttribute name="main"></tiles:insertAttribute>
            </div>
            <div class="clearfix sidebar_container floatright">
            	<tiles:insertAttribute name="right-bar"></tiles:insertAttribute>
            </div>
        </div>
    </section>
   <tiles:insertAttribute name="footer"></tiles:insertAttribute>
   <tiles:insertAttribute name="commonJs"></tiles:insertAttribute>
</body>

</html>