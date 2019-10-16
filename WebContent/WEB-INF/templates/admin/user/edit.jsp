<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/admin/user/commonJs.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		$('#username').change(function() {
			$.ajax({
				url: '<%=request.getContextPath() %>/admin/user/ajaxHasUser',
				type: 'POST',
				cache: false,
				data: { ausername: $(this).val() },
				success: function(data){
					if (data == 1) {
						var $msgDiv = $('<div class="msg-hasUser alert alert-success">User tồn tại.</div>');
						if ($('.msg-hasUser').length == 0) {
							$('#form-group-username').after($msgDiv);
						}
					} else {
						$('.msg-hasUser').remove();
					}
					
				},
				error: function (){
					console.log('ajax error');
				}
			});
		});
	});
</script>