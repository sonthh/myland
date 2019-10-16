<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!-- s:navigation-bar -->
<ul class="nav">
     <!-- Main menu -->
	<security:authorize access="isAuthenticated()">
		<li class="dashboard"><a href="${pageContext.request.contextPath }/admin"><i class="glyphicon glyphicon-home"></i> Dashboard</a></li>
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')">
			<li class="category"><a href="${pageContext.request.contextPath }/admin/category"><i class="glyphicon glyphicon-list"></i> Categories</a></li>
		</security:authorize>
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')">
			<li class="land"><a href="${pageContext.request.contextPath }/admin/land"><i class="glyphicon glyphicon-book"></i> Lands</a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<li class="user"><a href="${pageContext.request.contextPath }/admin/user"><i class="glyphicon glyphicon-user"></i> User</a></li>
		</security:authorize>
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')">
			<li class="contact"><a href="${pageContext.request.contextPath }/admin/contact"><i class="glyphicon glyphicon-phone-alt"></i> Contacts</a></li>
		</security:authorize>
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_USER')">
			<li class="slide"><a href="${pageContext.request.contextPath }/admin/slide"><i class="glyphicon glyphicon-picture"></i>Slides</a></li>
		</security:authorize>
	</security:authorize>
	
	<!-- <li class="submenu">
		<a href="#">
			<i class="glyphicon glyphicon-list"></i> Pages
 			<span class="caret pull-right"></span>
		</a>
        Sub menu
	    <ul>
	        <li><a href="login.php">Login</a></li>
	        <li><a href="javascript:void(0)">Signup</a></li>
	    </ul>
	</li> -->
 </ul>
<!-- e:navigation-bar -->