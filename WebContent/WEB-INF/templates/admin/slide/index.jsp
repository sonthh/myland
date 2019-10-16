<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/admin/slide/commonJs.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		enableSlide = function(id, enable) {
			$.ajax({
				url: '${pageContext.request.contextPath }/admin/slide/enableSlide',
				type: 'POST',
				cache: false,
				data: {
					aid: id,
					aenable: enable
				},
				success: function(response){
					if (response == 1) {
						$('#enableSlide-' + id).toggleClass('active');
						enable = enable == 1 ? 0 : 1;
						$('#enableSlide-' + id).attr("onclick","enableSlide(" + id + "," + enable + ")");
					}	
				},
				error: function (){
					console.log('lỗi ajax enable slide');
				}
			});
		};
	});
</script>