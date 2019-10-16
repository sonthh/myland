<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>     
<div class="row">
	<c:if test="${not empty  msg}">
   		<div class="alert alert-success">${msg }</div>
   	</c:if>
	<form action="${pageContext.request.contextPath }/admin/user/add" method="post">
         <div class="col-md-12 panel-info">
             <div class="content-box-header panel-heading">
                 <div class="panel-title ">Thêm người dùng</div>
             </div>
             <div class="content-box-large box-with-header">
                 <div>
                     <div class="row mb-10"></div>

                     <div class="row">
                         <div class="col-sm-6">
                             <div class="form-group" id="form-group-username">
                                 <label for="username">Username</label>
                                 <input autofocus type="text" id="username" name="username" value="${objUser.username }" class="form-control" placeholder="Nhập username">
                             </div>
                             <form:errors path="objUser.username" cssClass="alert alert-success" />
                             
                             <div class="form-group">
                                 <label for="fullname">Fullname</label>
                                 <input type="text" id="fullname" name="fullname" value="${objUser.fullname }" class="form-control" placeholder="Nhập fullname">
                             </div>
                             <form:errors path="objUser.fullname" cssClass="alert alert-success" />
                             
                             <div class="form-group">
                                 <label for="password">Password</label>
                                 <input type="password" id="password" name="password" value="${objUser.password }" class="form-control" placeholder="Nhập password">
                             </div>
                             <form:errors path="objUser.password" cssClass="alert alert-success" />


                             <div class="form-group">
                             	 <label for="">Role</label>
	                             <select class="form-control" name="roleId">
		                             <c:if test="${not empty listRoles }">
									 	<c:forEach items="${listRoles }" var="itemRole">
											<c:choose>
												<c:when test="${itemRole.id !=  objUser.role.id}">
													<option value="${itemRole.id }">${itemRole.name }</option>
												</c:when>
												<c:otherwise>
													<option value="${itemRole.id }" selected>${itemRole.name }</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									 </c:if>
								 </select>
                             </div>
                         </div>

                         <div class="col-sm-6"></div>
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
