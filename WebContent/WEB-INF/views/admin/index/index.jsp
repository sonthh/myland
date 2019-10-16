<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
 <div class="row">
    <div class="col-md-12 panel-warning">
        <div class="content-box-header panel-heading">
            <div class="panel-title ">Dashboard</div>
        </div>
        <div class="content-box-large box-with-header">
        	<div class="row">
				<security:authorize access="isAuthenticated()">
					<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')">
		                <div class="col-md-4 col-sm-4 col-xs-4">
		                    <div class="panel panel-back noti-box">
		                        <span class="icon-box bg-color-green set-icon">
		        					<span class="glyphicon glyphicon-th-list"></span>
		                        </span>
		                        <div class="text-box">
		                            <p class="main-text"><a class="fs-14" href="${pageContext.request.contextPath }/admin/category" title="">Categories</a></p>
		                            <p class="text-muted">Có ${numberOfCategories } danh mục</p>
		                        </div>
		                    </div>
		                </div>
					</security:authorize>
					
					<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')">
		                <div class="col-md-4 col-sm-4 col-xs-4">
		                    <div class="panel panel-back noti-box">
		                        <span class="icon-box bg-color-blue set-icon">
		       						<span class="glyphicon glyphicon-book"></span>
		                        </span>
		                        <div class="text-box">
		                            <p class="main-text"><a class="fs-14" href="${pageContext.request.contextPath }/admin/land" title="">Lands</a></p>
		                            <p class="text-muted">Có ${numberOfLands } tin tức</p>
		                        </div>
		                    </div>
		                </div>
					</security:authorize>
					
					<security:authorize access="hasRole('ROLE_ADMIN')">
		                <div class="col-md-4 col-sm-4 col-xs-4">
		                    <div class="panel panel-back noti-box">
		                        <span class="icon-box bg-color-red set-icon">
		        					<span class="glyphicon glyphicon-user"></span>
		                        </span>
		                        <div class="text-box">
		                            <p class="main-text"><a class="fs-14" href="${pageContext.request.contextPath }/admin/user" title="">Contacts</a></p>
		                            <p class="text-muted">Có ${numberOfUsers } người dùng</p>
		                        </div>
		                    </div>
		                </div>
					</security:authorize>
					
					<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')">
		                <div class="col-md-4 col-sm-4 col-xs-4">
		                    <div class="panel panel-back noti-box">
		                        <span class="icon-box bg-color-brown set-icon">
		        					<span class="glyphicon glyphicon-phone-alt"></span>
		                        </span>
		                        <div class="text-box">
		                            <p class="main-text"><a class="fs-14" href="${pageContext.request.contextPath }/admin/contact" title="">Contacts</a></p>
		                            <p class="text-muted">Có ${numberOfContacts } liên hệ</p>
		                        </div>
		                    </div>
		                </div>
					</security:authorize>
					
					<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_USER')">
		                <div class="col-md-4 col-sm-4 col-xs-4">
		                    <div class="panel panel-back noti-box">
		                        <span class="icon-box bg-color-yellow set-icon">
		        					<span class="glyphicon glyphicon-picture"></span>
		                        </span>
		                        <div class="text-box">
		                            <p class="main-text"><a class="fs-14" href="${pageContext.request.contextPath }/admin/slide" title="">Slides</a></p>
		                            <p class="text-muted">Có ${numberOfSlides } slide</p>
		                        </div>
		                    </div>
		                </div>
					</security:authorize>
				</security:authorize>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <div class="content-box-large">
            <div class="panel-heading">
                <div class="panel-title">Chào mừng đến với trang quản trị</div>
                <div class="panel-options">
                    <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                    <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                </div>
            </div>
            <div class="panel-body">
            	Cland.pro là một trang mua bán nhà đất hot nhất hiện nay với những tin tức được cập nhật liên tục 24/24. 
            	<br /><br />Bạn sẽ chọn được mảnh đất hay ngôi nhà ưng ý với mức giá phù hợp nhất.
            	<br /><br />Bạn có thể đăng tin mua bán bất động sản tại đây một cách free và có cơ hội cao để ra đi 
            	mảnh đất hay ngôi nhà yêu quý xưa nay của mình
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="row">
            <div class="col-md-12">
                <div class="content-box-header">
                    <div class="panel-title">Hướng dẫn sử dụng</div>
                </div>
                <div class="content-box-large box-with-header">
                	Quản lí các danh mục
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="content-box-header">
                    <div class="panel-title">Nội quy</div>
                </div>
                <div class="content-box-large box-with-header">
                Đừng hack web tội em
                </div>
            </div>
        </div>
    </div>
</div>