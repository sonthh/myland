<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/public/commonJs.jsp" %>
<script type="text/javascript">
   $('title').html('${objLand.name }');
   $('html,body').animate({
       scrollTop: $('#content_area').offset().top
   }, 'slow');
</script>
