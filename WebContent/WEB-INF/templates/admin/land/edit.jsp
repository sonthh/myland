<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/admin/land/commonJs.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/ckfinder/ckfinder.js"></script>
<script>
	
	var editor = CKEDITOR.replace('editor');
	CKFinder.setupCKEditor(editor, '<%=request.getContextPath()%>/libraries/ckfinder/');
</script>