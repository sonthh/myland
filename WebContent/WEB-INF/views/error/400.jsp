<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
	<title>Cland.pro</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/publicUrl/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/adminUrl/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/adminUrl/css/style.css" rel="stylesheet">
</head>
<body>

	<div class="header">
	    <div class="container">
	        <div class="row">
	            <div class="col-md-5">
	                <div class="logo">
	                    <h1><a href="">VNE-Admin</a></h1>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    <div class="page-content container">
		<div class="row">
		    <div class="col-md-4 col-md-offset-4">
		        <div class="login-wrapper">
		            <div class="box">
		                <div class="content-wrap">
		                    <img width="100px" height="100px" class="img-circle" src="${pageContext.request.contextPath}/adminUrl/images/icon-180x180.png">
					        <h6>Vui lòng truy cập hợp lệ</h6>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
	<script src="${pageContext.request.contextPath}/adminUrl/js/jquery-2.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/adminUrl/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/adminUrl/js/custom.js"></script>
</body>
</html>