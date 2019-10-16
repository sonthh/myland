<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>     
<%@ include file="/WEB-INF/templates/public/commonJs.jsp" %>
<c:if test="${page != 1 }">
	<c:set var="pageTitle" value=" - Page ${page }"></c:set>
</c:if>
<script type="text/javascript">
   $('title').html('${objCat.name }${pageTitle}'); 
   $('html,body').animate({
       scrollTop: $('#content_area').offset().top
   }, 'slow');
</script>
