<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>     
<div class="row">
	<c:if test="${not empty  msg}">
   		<div class="alert alert-success">${msg }</div>
   	</c:if>
	<form action="${pageContext.request.contextPath }/admin/land/add" method="post" enctype="multipart/form-data">
         <div class="col-md-12 panel-info">
             <div class="content-box-header panel-heading">
                 <div class="panel-title ">Thêm tin tức</div>
             </div>
             <div class="content-box-large box-with-header">
                 <div>
                     <div class="row">
                         <div class="col-sm-6">
                             <div class="form-group">
                                 <label for="name">Tên Land</label>
                                 <input type="text" name="name" value="${objLand.name }" class="form-control" placeholder="Nhập tên Land">
                             </div>
                             <form:errors path="objLand.name" cssClass="alert alert-success" />
                             
                             <div class="form-group">
                                 <label for="name">Area</label>
                                 <input type="number" min="1" name="area" value="${objLand.area }" class="form-control" placeholder="Nhập diện tích">
                             </div>
                             <form:errors path="objLand.area" cssClass="alert alert-success" />

                             <div class="form-group">
                                 <label for="name">Address</label>
                                 <input type="text" name="address" value="${objLand.address }" class="form-control" placeholder="Nhập địa chỉ">
                             </div>
                             <form:errors path="objLand.address" cssClass="alert alert-success" />

                             <div class="form-group">
                             	 <label for="">Category</label>
                             <select class="form-control" name="categoryId">
	                             <c:if test="${not empty listCategories }">
								 	<c:forEach items="${listCategories }" var="objCat">
										<c:choose>
											<c:when test="${objCat.id !=  objLand.category.id}">
												<option value="${objCat.id }">${objCat.name }</option>
											</c:when>
											<c:otherwise>
												<option value="${objCat.id }" selected>${objCat.name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								 </c:if>
							 </select>
                             </div>

                             <div class="form-group">
                                 <label>Thêm hình ảnh</label>
                                 <input type="file" name="images" class="btn btn-default" id="exampleInputFile1">
                                 <p class="help-block"><em>Định dạng: jpg, png, jpeg,...</em></p>
                             </div>
                             <form:errors path="objLand.picture" cssClass="alert alert-success" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
	                        <div class="form-group">
	                            <label>Mô tả</label>
	                            <textarea name="description" class="form-control" rows="3">${objLand.description }</textarea>
	                        </div>
	                        <form:errors path="objLand.description" cssClass="alert alert-success" />
						</div>
                    </div>
					<div class="row">
                         <div class="col-sm-12">
                             <div class="form-group">
                                 <label>Chi tiết</label>
                                 <textarea id="editor" name="detail" class="form-control" rows="7">${objLand.detail }</textarea>
                             </div>
                             <form:errors path="objLand.detail" cssClass="alert alert-success" />
                         </div>
                   </div>
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