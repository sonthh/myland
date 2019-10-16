<%@page import="vn.edu.vinaenter.util.StringUtil"%>
<%@page import="vn.edu.vinaenter.util.SlugUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>
<div class="clearfix slider">
	<!-- Start WOWSlider.com BODY section --> <!-- add to the <body> of your page -->
	<div id="wowslider-container1">
		<div class="ws_images">
			<ul>
			<c:forEach items="${listTopOrderSlides}" var="itemSlide" varStatus="i">
				<li><a href="${itemSlide.link }"><img src="${pageContext.request.contextPath}/files/${itemSlide.picture}" alt="css slideshow" title="${itemSlide.title}" id="wows1_${i.index}"/></a></li>
			</c:forEach>
			</ul>
		</div>
		<div class="ws_bullets">
			<div>
				<c:forEach items="${listTopOrderSlides}" var="itemSlide" varStatus="i">
					<a href="#" title="nha2"><span><img width="85px" height="48px" src="${pageContext.request.contextPath}/files/${itemSlide.picture}" alt="nha2"/>${i.count }</span></a>
				</c:forEach>
			</div>
		</div>
		<div class="ws_script" style="position:absolute;left:-99%"><a href="http://wowslider.net">bootstrap slider</a> by WOWSlider.com v8.8</div>
		<div class="ws_shadow"></div>
	</div>	
	<!-- End WOWSlider.com BODY section -->
</div>

 <div class="clearfix content">
     <div class="content_title">
         <h2>Kết quả của ${searchString }</h2>
     </div>
     <c:choose>
     	<c:when test="${not empty listLands }">
			<c:forEach items="${listLands }" var="itemLand">
			     <div class="clearfix single_content">
			         <div class="clearfix post_date floatleft">
			             <div class="date">
			                 <h3><fmt:formatDate value="${itemLand.dateCreate }" pattern="dd" /></h3>
			                 <p>Tháng <fmt:formatDate value="${itemLand.dateCreate }" pattern="MM" /></p>
			             </div>
			         </div>
			         <div class="clearfix post_detail">
			         	 <c:set var="urlDetail" value="${pageContext.request.contextPath}/${SlugUtil.makeSlugs(itemLand.name) }-${itemLand.id }.html"></c:set>
			         	 
			         	 <c:set var="styleSearchString" value="<span style='color: red'>${searchString }</span>"></c:set>
			             <h2><a href="${urlDetail }">${fn:replace(itemLand.name, searchString, styleSearchString)} </a></h2>
			             
			             <div class="clearfix post-meta">
			                 <p><span><i class="fa fa-home"></i> Địa chỉ:${itemLand.address }</span> <span><i class="fa fa-folder"></i> Diện tích: ${itemLand.area }m2</span></p>
			             </div>
			             <div class="clearfix post_excerpt">
			             	<div class="overlay">
			                	<img src="${pageContext.request.contextPath}/files/${itemLand.picture }" alt="" />
			             	</div>
			                <p>${StringUtil.getText(itemLand.description, 265)}</p>
			             </div>
			             <a href="${urlDetail }">Đọc thêm</a>
			         </div>
			     </div>	
		 	</c:forEach>
     	</c:when>
     	<c:otherwise>
     		<div>
     			<div style="display: none" class="msg-notfound alert alert-yellow">Không tìm thấy ${searchString }</div>
     		</div>
     	</c:otherwise>
     </c:choose>
 </div>

 <div class="pagination">
     <nav>
        <ul>
        	<c:if test="${numberOfPages > 0 }">
	        	<c:if test="${page == 1 }">
	        		<li class="disabled"><span> <i class="glyphicon glyphicon-chevron-left"></i> </span></li>
	        	</c:if>
	        	<c:if test="${page != 1 }">
	        		<li><a href="${pageContext.request.contextPath}/search-${searchString }-${page-1}"> <i class="glyphicon glyphicon-chevron-left"></i> </a></li>
	        	</c:if>
	           
	        	<c:if test="${not empty paginations }" >
	        		<c:forEach items="${paginations }" var="itemPagination">
	        			<c:choose>
	        				<c:when test="${itemPagination == -1 }">
	        					<li class="disabled"><span> <i class="glyphicon glyphicon-arrow-left"></i> </span></li>
	        				</c:when>
	        				<c:when test="${itemPagination == -2 }">
	        					<li class="disabled"><span> <i class="glyphicon glyphicon-arrow-right"></i> </span></li>
	        				</c:when>
	        				<c:when test="${itemPagination == page }">
	        					<li class="active"><span> ${itemPagination } </span></li>
	        				</c:when>
       						<c:otherwise>
       							<li><a href="${pageContext.request.contextPath}/search-${searchString }-${itemPagination}"> ${itemPagination } </a></li>
       						</c:otherwise>
	        			</c:choose>
	        		</c:forEach>
	        	</c:if>
	        	
	        	<c:if test="${page == numberOfPages }">
	        		<li class="disabled"><span> <i class="glyphicon glyphicon-chevron-right"></i> </span></li>
	        	</c:if>
	        	<c:if test="${page != numberOfPages }">
	        		<li><a href="${pageContext.request.contextPath}/search-${searchString }-${page + 1}"> <i class="glyphicon glyphicon-chevron-right"></i> </a></li>
	        	</c:if>
        	</c:if>
        </ul>
     </nav>
 </div>