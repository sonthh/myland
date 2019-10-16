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

	<!-- Nếu đã đăng nhập rồi thì chuyển hướng sang trang admin có 2 cách -->
	<%-- <c:if test="${not empty pageContext.request.userPrincipal}">
	   <c:redirect url="${pageContext.request.contextPath}/admin" />
	</c:if> --%>
	<security:authorize access="isAuthenticated()">
		<c:redirect url="${pageContext.request.contextPath}/admin" />
	</security:authorize>

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
		                    <h6>Đăng nhập</h6>
		                    <c:if test="${not empty param['error']}">
		                    	<p class="msg-error">Sai username hoặc password</p>
		                    </c:if>
							<form id="form-login" action="${pageContext.request.contextPath}/auth/login" method="post">
			                    <div class="form-group">
			                        <label class="text-left pull-left" for="username">Tên đăng nhập</label>
			                        <input id="username" name="username" class="form-control" type="text" placeholder="Username">
			                    </div>
			                    <div class="form-group">
			                        <label class="text-left pull-left" for="password">Mật khẩu</label>
			                        <input id="password" name="password" class="form-control" type="password" placeholder="Password">
			                    </div>
			                    <div class="form-group">
			                    	<div class="col-md-6 p-0">
			                    		<div class="custom-control">
								 			<input name="rememberme" class="custom-control-input" type="checkbox"  />
								            <span class="custom-control-label">Remember Me</span>
			                    		</div>
									</div>
			                    	<div class="col-md-6 text-right">
										<a href="${pageContext.request.contextPath}/auth/forgot">Forgot Password?</a>
									</div>
			                    </div>
			                    <div class="action">
			                    	<button type="submit" class="btn btn-primary signup btn-block">Login</button>
			                    </div>
			                </form>   
		                </div>
		            </div>
		            <div class="already">
		                <p>Don't have an account yet?</p>
		                <a href="${pageContext.request.contextPath}/auth/register">Sign Up</a>
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