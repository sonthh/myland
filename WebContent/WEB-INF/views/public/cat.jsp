<%@page import="vn.edu.vinaenter.util.StringUtil"%>
<%@page import="vn.edu.vinaenter.util.SlugUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %> 

<div class="clearfix content">
    <div class="content_title">
        <h2>${objCat.name }</h2>
    </div>
    <div class="clearfix single_work_container">
    	<c:if test="${not empty listLands }">
    		<c:forEach items="${listLands }" var="itemLand">
    			<c:set var="urlDetail" value="${pageContext.request.contextPath}/${SlugUtil.makeSlugs(itemLand.name)}-${itemLand.id}.html"></c:set>
		        <div class="clearfix single_work">
		            <img class="img_top" src="${pageContext.request.contextPath}/files/${itemLand.picture }" alt="" />
		            <img class="img_bottom" src="${pageContext.request.contextPath}/publicUrl/images/work_bg2.png" alt="" />
		            <h2>${StringUtil.getText(itemLand.name, 75)}</h2>
		            <a href="${urlDetail }">
		                <p class="caption">${StringUtil.getText(itemLand.description, 68)}</p>
		            </a>
		        </div>	
    		</c:forEach>
    	</c:if>

        <div class="clearfix work_pagination">
            <nav>
            	<c:if test="${page == 1 }">
                	<a class="newer floatleft disabled" href="javascript:void(0)"> <i class="glyphicon glyphicon-arrow-left"></i> Trang trước</a>
            	</c:if>
            	<c:if test="${page != 1 }">
            		<a class="newer floatleft" href="${pageContext.request.contextPath}/category/${SlugUtil.makeSlugs(objCat.name)}-${objCat.id}/page-${page - 1}"> <i class="glyphicon glyphicon-arrow-left"></i> Trang trước</a>
            	</c:if>
            	
            	<c:if test="${page == numberOfPages }">
                	<a class="older floatright disabled" href="javascript:void(0)">Trang kế <i class="glyphicon glyphicon-arrow-right"></i></a>
            	</c:if>
            	<c:if test="${page != numberOfPages}">
            		<a class="older floatright" href="${pageContext.request.contextPath}/category/${SlugUtil.makeSlugs(objCat.name)}-${objCat.id}/page-${page + 1}"> Trang kế <i class="glyphicon glyphicon-arrow-right"></i></a>
            	</c:if>
                
            </nav>
        </div>
    </div>
</div>