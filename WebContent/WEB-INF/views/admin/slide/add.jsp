<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>     
<div class="row">
	<c:if test="${not empty  msg}">
   		<div class="alert alert-success">${msg }</div>
   	</c:if>
	<form action="${pageContext.request.contextPath }/admin/slide/add" method="post" enctype="multipart/form-data">
         <div class="col-md-12 panel-info">
             <div class="content-box-header panel-heading">
                 <div class="panel-title ">Thêm Slide</div>
             </div>
             <div class="content-box-large box-with-header">
                 <div>
                     <div class="row mb-10"></div>

                     <div class="row">
                         <div class="col-sm-6">
                             <div class="form-group">
                                 <label for="title">Title</label>
                                 <input type="text" name="title" value="${objSlide.title }" class="form-control" placeholder="Nhập title slide">
                             </div>
                             <form:errors path="objSlide.title" cssClass="alert alert-success" />
                             
                             <div class="form-group">
                                 <label for="link">Link</label>
                                 <input type="text" name="link" value="${objSlide.link }" class="form-control" placeholder="Nhập link slide">
                             </div>
                             <form:errors path="objSlide.link" cssClass="alert alert-success" />
                             
                             <div class="form-group">
                                 <label>Thêm hình ảnh</label>
                                 <input type="file" name="images" class="btn btn-default" id="exampleInputFile1">
                                 <p class="help-block"><em>Định dạng: jpg, png, jpeg,...</em></p>
                             </div>
                             <form:errors path="objSlide.picture" cssClass="alert alert-success" />
                             
                             <div class="form-group">
                                 <label for="sort">Sort</label>
                                 <input type="number" min="1" name="sort" value="${objSlide.sort }" class="form-control" placeholder="Nhập STT">
                             </div>
                             <form:errors path="objSlide.sort" cssClass="alert alert-success" />
                         </div>
                     </div>
                     <hr>
                     <div class="row">
                         <div class="col-sm-12">
                             <input type="submit" value="Thêm" class="btn btn-success" />
                             <input type="reset" value="Nhập lại" class="btn btn-default" />
                         </div>
                     </div>

                 </div>
             </div>
         </div>
         </form>
     </div>
     <!-- /.row col-size -->