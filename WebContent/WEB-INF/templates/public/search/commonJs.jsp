<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>     
<%@ include file="/WEB-INF/templates/public/commonJs.jsp" %>

<!-- wowslider libaray -->
<script type="text/javascript" src="${pageContext.request.contextPath}/publicUrl/wowslider/wowslider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/publicUrl/wowslider/script.js"></script>

<c:if test="${page != 1 }">
	<c:set var="pageTitle" value=" - Page ${page }"></c:set>
</c:if>
<script type="text/javascript">
   $('title').html('Trang chủ${pageTitle}');
   /* var check = ${page};
   if (check != 1) {
	   $('html,body').animate({
	       scrollTop: $('#main-content').offset().top
	   }, 'slow');
   } */
   
   $('.msg-notfound').fadeIn( "slow" );
   
   
</script>
