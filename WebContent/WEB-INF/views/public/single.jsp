<%@page import="vn.edu.vinaenter.util.SlugUtil"%>
<%@page import="vn.edu.vinaenter.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>  
<div class="clearfix content">
    <h1>${objLand.name } </h1>
    <div class="clearfix post-meta">
        <p>
        	<span><i class="fa fa-home"></i> ${objLand.address}</span> 
        	<span class="text-normal">
        		<i class="fa fa-folder"></i> Diện tích: ${objLand.area} m<sup>2</sup>
        		   <i class="fa fa-eye"></i> Lượt xem: ${objLand.views}
        	</span>
        </p>
    </div>

    <div class="vnecontent">
        <p>${objLand.detail }</p>
    </div>

    <!-- <a class="btn" href="">Bài trước</a>
    <a class="btn" href="">Bài kế</a> -->

</div>

<div class="more_themes">
    <h2>Tin tức liên quan <i class="fa fa-thumbs-o-up"></i></h2>
    <div class="more_themes_container">
    	<c:choose>
    		<c:when test="${not empty listRelatedLands }">
	        	<c:forEach items="${listRelatedLands }" var="itemLand">
			        <div class="single_more_themes floatleft">
			        	<div class="img-overlay">
			            	<img src="${pageContext.request.contextPath}/files/${itemLand.picture }" alt="" />
			            </div>
			            <a href="${pageContext.request.contextPath}/${SlugUtil.makeSlugs(itemLand.name)}-${itemLand.id}.html">
			                <h2>${StringUtil.getText(itemLand.name, 80)}</h2>
			            </a>
			        </div>	
	        	</c:forEach>	
    		</c:when>
    		<c:otherwise>
    			<div>Không có tin liên quan</div>
    		</c:otherwise>
    	</c:choose>
    </div>
</div>