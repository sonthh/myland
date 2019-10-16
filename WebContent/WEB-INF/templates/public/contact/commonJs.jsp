<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/public/commonJs.jsp" %>
<script src="${pageContext.request.contextPath}/publicUrl/js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	$('#frmContact').validate({
    		rules:{
    			"fullname":{
    				required: true,
    			},
    			"email":{
    				required: true,
    				email: true
    			}, 
    			"subject":{
    				required: true,
    			},
    			"content":{
    				required: true,
    			}
    		},
    		messages:{
    			"fullname":{
    				required: 'Vui lòng nhập fullname',
    			},
    			"email":{
    				required: 'Vui lòng nhập email',
    				email: 'Vui lòng nhập đúng định dạng email'
    			}, 
    			"subject":{
    				required: 'Vui lòng nhập nội dung subject',
    			},
    			"content":{
    				required: 'Vui lòng nhập nội dung liên hệ',
    			}
    		},
    		submitHandler: function(form) {
    			$.ajax({
        			url: '${pageContext.request.contextPath }/contact',
        			type: 'POST',
        			cache: false,
        			data: $('#frmContact').serialize(),
        			success: function(response){
        				var msg = '';
        				if (response == 1)
        					msg = 'Gởi liên hệ thành công';
        				else 
        					msg = 'Gởi liên hệ thất bại';
        				$('.msg-success').html(msg).fadeIn( "slow" );
        				$('#frmContact')[0].reset();
        			},
        			error: function (){
        				console.log('lỗi ajax send contact');
        			}
        		});
    		}
    	});
    });
</script>
