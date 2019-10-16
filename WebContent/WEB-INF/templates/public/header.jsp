<%@page import="vn.edu.vinaenter.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<jsp:useBean id="slug" class="vn.edu.vinaenter.util.SlugUtil"/>
 <section id="header_area">
        <div class="wrapper header">
            <div class="clearfix header_top">
                <div class="clearfix logo floatleft">
                    <a href="${pageContext.request.contextPath }/">
                        <h1><span>C</span>Land</h1>
                    </a>
                </div>
                <div class="clearfix search floatright">
                    <form action="${pageContext.request.contextPath}/search" method="get">
                        <input type="text" name="name" value="${searchString }" placeholder="Search"
                        required="required" />
                        <input type="submit" value="" />
                    </form>
                </div>
            </div>
            <div class="header_bottom">
                <nav>
                    <ul id="nav">
                        <li><a href="${pageContext.request.contextPath }/">Trang chủ</a></li>
                        <li id="dropdown"><a style="cursor: context-menu;" href="javascript:void(0)">Danh mục</a>
                            <ul>
                            	<c:if test="${not empty listCategories }">
                            		<c:forEach items="${listCategories }" var="itemCat">
                            			<li><a href="${pageContext.request.contextPath }/category/${slug.makeSlugs(itemCat.name) }-${itemCat.id }">${itemCat.name }</a></li>
                            		</c:forEach>
                            	</c:if>
                            </ul>
                        </li>
                        <li><a href="${pageContext.request.contextPath }/contact">Liên hệ</a></li>
                        <li class="fl-right"><a href="${pageContext.request.contextPath }/auth/login">Login</a></li>
                        <li class="fl-right"><a href="${pageContext.request.contextPath }/auth/register">Sign in</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </section>