<%@page import="vn.edu.vinaenter.security.UserDetailsSecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <!-- Logo -->
                <div class="logo">
                    <h1><a href="${pageContext.request.contextPath}/admin">VNE-Admin</a></h1>
                </div>
            </div>
            <div class="col-md-5">
                <div class="row">
                    <div class="col-lg-12">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="navbar navbar-inverse" role="banner">
                    <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
                        <ul class="nav navbar-nav">
                        <security:authorize access="isAuthenticated()">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                	<security:authentication property="principal.username" /> <%-- ${pageContext.request.userPrincipal.name} --%> 
                                	<b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu animated fadeInUp">
                                    <li><a href=""><%=UserDetailsSecurityUtils.getMyUserDetails().getFullname() %></a></li>
                                    <li><a href="${pageContext.request.contextPath}/auth/logout">Logout</a></li>
                                </ul>
                            </li>
                        </security:authorize>    
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>