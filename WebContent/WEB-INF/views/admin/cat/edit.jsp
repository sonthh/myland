<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>    
<div class="row">
		<c:if test="${not empty  msg}">
    		<div class="alert alert-success">${msg }</div>
    	</c:if>
		<c:set var="cid" value=""></c:set>
		<c:set var="name" value=""></c:set>
		<c:if test="${objCat != null }">
			<c:set var="id" value="${objCat.id }"></c:set>
			<c:set var="name" value="${objCat.name }"></c:set>
		</c:if>
		<form action="${pageContext.request.contextPath }/admin/category/edit/${objCat.id}-${page}" method="post">
         <div class="col-md-12 panel-info">
             <div class="content-box-header panel-heading">
                 <div class="panel-title ">Sửa danh mục</div>
             </div>
             <div class="content-box-large box-with-header">
                 <div>
                     <div class="row mb-10"></div>

                     <div class="row">
                         <div class="col-sm-6">
                             <div class="form-group">
                                 <label for="name">Sửa danh mục</label>
                                 <input type="text" name="name" value="${name }" class="form-control" placeholder="Nhập tên danh mục">
                             </div>
                           	<form:errors path="objCat.name" cssClass="alert alert-success" />
                         </div>
                     </div>
                     <hr>
                     <div class="row">
                         <div class="col-sm-12">
                             <input type="submit" value="Sửa" class="btn btn-success" />
                             <input type="reset" value="Nhập lại" class="btn btn-default" />
                         </div>
                     </div>
                 </div>
             </div>
         </div>
        </form>
     </div>
     <!-- /.row col-size -->