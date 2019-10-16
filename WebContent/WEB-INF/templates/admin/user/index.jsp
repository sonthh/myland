<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/admin/user/commonJs.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		$('.sidebar ul.nav li.user').addClass('current');
		enableUser = function(id, enable) {
			$.ajax({
				url: '${pageContext.request.contextPath }/admin/user/enableUser',
				type: 'POST',
				cache: false,
				data: {
					aid: id,
					aenable: enable
				},
				success: function(response){
					if (response == 1) {
						$('#enableUser-' + id).toggleClass('active');
						enable = enable == 1 ? 0 : 1;
						$('#enableUser-' + id).attr("onclick","enableUser(" + id + "," + enable + ")");
					}	
				},
				error: function (){
					console.log('lỗi ajax enable user');
				}
			});
		};
	});
</script>