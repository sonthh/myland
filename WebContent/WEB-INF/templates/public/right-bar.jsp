<%@page import="vn.edu.vinaenter.model.dao.CategoryDAO"%>
<%@page import="vn.edu.vinaenter.util.SlugUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %> 
<div class="clearfix sidebar">
    <div class="clearfix single_sidebar category_items">
        <h2>Danh mục bất động sản</h2>
        <ul>
           	<c:if test="${not empty listCategories }">
           		<c:forEach items="${listCategories }" var="itemCat">
           			<li class="cat-item"><a class="hvr-wobble-skew" href="${pageContext.request.contextPath }/category/${SlugUtil.makeSlugs(itemCat.name) }-${itemCat.id }">${itemCat.name }</a>(${landDAO.countItemsByCategoryId(itemCat.id) })</li>
           		</c:forEach>
           	</c:if>
        </ul>
    </div>

    <div class="clearfix single_sidebar">
        <div class="popular_post">
            <div class="sidebar_title">
                <h2>Mới nhất</h2>
            </div>
            <ul>
            	<c:if test="${not empty listTopLands }">
	        		<c:forEach items="${listTopLands }" var="itemLand">
	        			<c:set var="urlDetail" value="${pageContext.request.contextPath}/${SlugUtil.makeSlugs(itemLand.name) }-${itemLand.id }.html"></c:set>
	        			<li class="hvr-grow"><a href="${urlDetail }">${itemLand.name }</a></li>
	        		</c:forEach>
        		</c:if>
            </ul>
        </div>
    </div>
    
    <div class="clearfix single_sidebar">
        <div class="popular_post">
            <div class="sidebar_title">
                <h2>Xem nhiều</h2>
            </div>
            <ul>
            	<c:if test="${not empty listTopViewsLands }">
	        		<c:forEach items="${listTopViewsLands }" var="itemLand">
	        			<c:set var="urlDetail" value="${pageContext.request.contextPath}/${SlugUtil.makeSlugs(itemLand.name) }-${itemLand.id }.html"></c:set>
	        			<li class="hvr-grow"><a href="${urlDetail }">${itemLand.name }</a></li>
	        		</c:forEach>
        		</c:if>
            </ul>
        </div>
    </div>

    <div class="clearfix single_sidebar">
        <h2>Danh mục hot</h2>
        <ul>
        	<c:if test="${not empty listTopCategories }">
        		<c:forEach items="${listTopCategories }" var="itemCat">
        			<li><a href="${pageContext.request.contextPath }/category/${SlugUtil.makeSlugs(itemCat.name) }-${itemCat.id }">${itemCat.name } <span>(${landDAO.countItemsByCategoryId(itemCat.id) })</span></a></li>
        		</c:forEach>
        	</c:if>
        </ul>
    </div>
</div>
