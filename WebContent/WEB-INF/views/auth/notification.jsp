<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
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
	                    <h1><a href="${pageContext.request.contextPath}/auth/login">VNE-Admin</a></h1>
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
		                    <c:if test="${not empty param['msg']}">
		                    	<c:choose>
		                    		<c:when test="${param['msg'] eq 'success'}">
					                    <h6>Tài khoản đã được kích hoạt</h6>
		                    		</c:when>
		                    		<c:when test="${param['msg'] eq 'error'}">
					                    <h6>Có gì đó không may xảy xa. Vui lòng liên hệ ban quả trị</h6>
		                    		</c:when>
		                    		<c:when test="${param['msg'] eq 'errorPass'}">
					                    <h6>Có gì đó không may xảy xa. Vui lòng liên hệ ban quả trị</h6>
		                    		</c:when>
		                    		<c:when test="${param['msg'] eq 'successPass'}">
					                    <h6>Xác nhận đổi mật khẩu thành công, bây giờ bạn có thể đăng nhập được</h6>
		                    		</c:when>
		                    	</c:choose>
		                    </c:if>
		                    <p><a href="${pageContext.request.contextPath}/auth/login">Login now</a></p>
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