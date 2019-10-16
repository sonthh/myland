<%@page import="vn.edu.vinaenter.model.bean.User"%>
<%@page import="vn.edu.vinaenter.security.UserDetailsSecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>
<%@ page import="vn.edu.vinaenter.constant.MessageEnum" %>
<div class="content-box-large">
<div class="row">
    <div class="panel-heading">
        <div class="panel-title ">Quản Lí Người Dùng</div>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-8">
        <a href="${pageContext.request.contextPath }/admin/user/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
    </div>
    <div class="col-md-4">
	    <form action="${pageContext.request.contextPath }/admin/user/search" method="get">
	        <div class="input-group form">
	        	
	            <input type="text" name="name" class="form-control" placeholder="Search...">
	            <span class="input-group-btn">
	     			<button class="btn btn-primary" type="submit">Search</button>
	   			</span>
	        </div>
	    </form>
    </div>
</div>

<div class="row">
    <div class="panel-body">
    	<c:if test="${not empty  eMsg}">
    		<c:choose>
    			<c:when test="${eMsg eq MessageEnum.MSG_DELETE_SUCCESS }">
    				<div style="display: none;" class="alert alert-danger">${eMsg.status }</div>
    			</c:when>
    			<c:when test="${eMsg eq MessageEnum.MSG_UPDATE_SUCCESS }">
    				<div style="display: none;" class="alert alert-info">${eMsg.status }</div>
    			</c:when>
    			<c:when test="${eMsg eq MessageEnum.MSG_ADD_SUCCESS }">
    				<div style="display: none;" class="alert alert-success">${eMsg.status }</div>
    			</c:when>
    			<c:otherwise>
    				<div style="display: none;" class="alert alert-danger">${eMsg.status }</div>
    			</c:otherwise>
    		</c:choose>
    	</c:if>
    	
        <table class="table table-striped table-bordered" id="example">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Fullname</th>
                    <th>Role</th>
                    <th>Enable</th>
                    <th>Chức năng</th>
                </tr>
            </thead>
            <tbody>
            	<c:if test="${not empty listUsers }">
            		<c:forEach items="${listUsers }" var="itemUser">
            			<c:set var="urlEdit" value="${pageContext.request.contextPath }/admin/user/edit/${itemUser.id }-${page }"></c:set>
            			<c:set var="urlDel" value="${pageContext.request.contextPath }/admin/user/del/${itemUser.id }"></c:set>
            			<c:set var="activeClass" value=""></c:set>
            			<c:if test="${objUser.id eq itemUser.id }">
		                    <c:set var="activeClass" value="tb-active"></c:set>	
		                </c:if>
            			<tr class="odd gradeX">
		                    <td class="${activeClass }">${itemUser.id }</td>
		                    <td>${itemUser.username }</td>
		                    <td>${itemUser.fullname }</td>
		                    <td>${itemUser.role.name }</td>
		                    <td class="enable">
		                    	<c:choose>
		                    		<c:when test="${itemUser.enable == 1 }">
		                    			<span id="enableUser-${itemUser.id }" class="active" onclick="enableUser(${itemUser.id}, ${itemUser.enable })">Active</span>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<span id="enableUser-${itemUser.id }" onclick="enableUser(${itemUser.id}, ${itemUser.enable })">Active</span>
		                    		</c:otherwise>
		                    	</c:choose>
		                    </td>
		                    <td class="center text-center">
		                        <a href="${urlEdit }" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
		                         <c:if test="${itemUser.role.name ne 'ADMIN'}">
		                        	<a href="${urlDel }" title="" class="btn btn-danger" onclick="return confirm('Bạn có muốn xóa?')" ><span class="glyphicon glyphicon-trash"></span> Xóa</a>
		                        </c:if>
		                    </td>
		                </tr>
            		</c:forEach>
            	</c:if>
            </tbody>
        </table>

        <!-- Pagination -->
    <nav class="text-center" aria-label="...">
        <ul class="pagination">
        	<c:if test="${page == 1 }">
        		 <li class="disabled"><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
        	</c:if>
        	<c:if test="${page != 1 }">
        		 <li class=""><a href="${pageContext.request.contextPath}/admin/user/page-${page-1}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
        	</c:if>
           
        	<c:if test="${not empty paginations }" >
        		<c:forEach items="${paginations }" var="itemPagination">
        			<c:choose>
        				<c:when test="${itemPagination == page }">
        					<li class="active"><a href="${pageContext.request.contextPath}/admin/user/page-${itemPagination}">${itemPagination } <span class="sr-only">(current)</span></a></li>
        				</c:when>
        				<c:when test="${itemPagination == -1 }">
        					<li class="disabled"><a href="javascript:void(0)"><i class="glyphicon glyphicon-arrow-left"></i></a></li>
        				</c:when>
        				<c:when test="${itemPagination == -2 }">
        					<li class="disabled"><a href="javascript:void(0)"><i class="glyphicon glyphicon-arrow-right"></i></a></li>
        				</c:when>
        				<c:otherwise>
        					<li><a href="${pageContext.request.contextPath}/admin/user/page-${itemPagination}">${itemPagination }</a></li>
        				</c:otherwise>
        			</c:choose>
        		</c:forEach>
        	</c:if>
        	
        	<c:if test="${page == numberOfPages }">
        		 <li class="disabled"><a href="javascript:void(0)" aria-label="Next"><span aria-hidden="true">»</span></a></li>
        		 
        	</c:if>
        	<c:if test="${page != numberOfPages }">
        		 <li><a href="${pageContext.request.contextPath}/admin/user/page-${page + 1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
        	</c:if>
        </ul>
    </nav>
    <!-- /.pagination -->

    </div>
</div>
<!-- /.row -->
</div>
<!-- /.content-box-large -->