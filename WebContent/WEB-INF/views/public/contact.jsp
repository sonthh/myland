<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>      
<div class="clearfix content">
    <div class="contact_us">
        <h1>Liên hệ với chúng tôi</h1>
        <p>
            TRUNG TÂM ĐÀO TẠO LẬP TRÌNH VINAENTER EDU<br /> Trụ sở: 154 Phạm Như Xương, Liên Chiểu, Đà Nẵng<br /> Web: <a href="http://vinaenter.edu.vn" title="">www.vinaenter.edu.vn</a>
        </p>
        <form id="frmContact" action="javascript:void(0)" method="post">
        
        	<div style="display: none" class="msg-success alert alert-success"></div>
        	
            <p><input type="text" name="fullname" class="wpcf7-text" placeholder="Họ tên *" /></p>
            <p><input type="text" name="email" class="wpcf7-email" placeholder="Email *" /></p>
            <p><input type="text" name="subject" class="wpcf7-text" placeholder="Chủ đề *" /></p>
            <p style="position: relative;"><textarea name="content" class="wpcf7-textarea" placeholder="Nội dung *"></textarea></p>
            <p><input type="Submit" class="wpcf7-submit" value="Gửi liên hệ" /></p>
        </form>
    </div>
</div>