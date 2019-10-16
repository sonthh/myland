<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>
<%@ page import="vn.edu.vinaenter.constant.MessageEnum" %>
<div class="content-box-large">
<div class="row">
    <div class="panel-heading">
        <div class="panel-title ">Quản Lí Danh Mục</div>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-8">
        <a href="${pageContext.request.contextPath }/admin/category/add" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
    </div>
    <div class="col-md-4">
	    <form action="${pageContext.request.contextPath }/admin/category/search" method="get">
	        <div class="input-group form">
	            <input type="text" name="name" value="${searchString }" class="form-control" placeholder="Search...">
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
    				<div class="alert alert-danger">${eMsg.status }</div>
    			</c:when>
    			<c:when test="${eMsg eq MessageEnum.MSG_UPDATE_SUCCESS }">
    				<div class="alert alert-info">${eMsg.status }</div>
    			</c:when>
    			<c:when test="${eMsg eq MessageEnum.MSG_ADD_SUCCESS }">
    				<div class="alert alert-success">${eMsg.status }</div>
    			</c:when>
    			<c:otherwise>
    				<div class="alert alert-danger">${eMsg.status }</div>
    			</c:otherwise>
    		</c:choose>
    	</c:if>
    	
        <table class="table table-striped table-bordered" id="example">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th style="width: 30%">Function</th>
                </tr>
            </thead>
            <tbody>
            	<c:choose>
            		<c:when test="${not empty listCategories }">
            			<c:forEach items="${listCategories }" var="itemCat">
	            			<c:set var="urlEdit" value="${pageContext.request.contextPath }/admin/category/edit/${itemCat.id }-${page }"></c:set>
	            			<c:set var="urlDel" value="${pageContext.request.contextPath }/admin/category/del/${itemCat.id }"></c:set>
	            			<c:set var="activeClass" value=""></c:set>
	            			<c:if test="${objCat.id eq itemCat.id }">
			                    <c:set var="activeClass" value="tb-active"></c:set>	
			                </c:if>
	            			<tr class="odd gradeX">
			                    <td class="${activeClass }">${itemCat.id }</td>
			                    <c:set var="styleSearchString" value="<span style='color: red'>${searchString }</span>"></c:set>
			                    <td>${fn:replace(itemCat.name, searchString, styleSearchString)}</td>
			                    
			                    <td class="center text-center">
			                        <a href="${urlEdit }" title="" class="btn btn-primary"><span class="glyphicon glyphicon-pencil "></span> Sửa</a>
			                        <a href="${urlDel }" title="" class="btn btn-danger" onclick="return confirm('Bạn có muốn xóa?')" ><span class="glyphicon glyphicon-trash"></span> Xóa</a>
			                    </td>
			                </tr>
	            		</c:forEach>
            		</c:when>
            		<c:otherwise>
            			<tr><td colspan="3"><div class="text-center"><strong>Không tìm thấy <span class="text-warning">${searchString }</span></strong></div></td></tr>
            		</c:otherwise>
            	</c:choose>
            </tbody>
        </table>

        <!-- Pagination -->
    <nav class="text-center" aria-label="...">
        <ul class="pagination">
        	<c:if test="${numberOfPages > 0 }">
	        	<c:if test="${page == 1 }">
	        		 <li class="disabled"><a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
	        	</c:if>
	        	<c:if test="${page != 1 }">
	        		 <li class=""><a href="${pageContext.request.contextPath}/admin/category/search-${searchString }-${page-1}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
	        	</c:if>
	           
	        	<c:if test="${not empty paginations }" >
	        		<c:forEach items="${paginations }" var="itemPagination">
	        			<c:choose>
	        				<c:when test="${itemPagination == page }">
	        					<li class="active"><a href="${pageContext.request.contextPath}/admin/category/search-${searchString}-${itemPagination}">${itemPagination } <span class="sr-only">(current)</span></a></li>
	        				</c:when>
	        				<c:when test="${itemPagination == -1 }">
	        					<li class="disabled"><a href="javascript:void(0)"><i class="glyphicon glyphicon-arrow-left"></i></a></li>
	        				</c:when>
	        				<c:when test="${itemPagination == -2 }">
	        					<li class="disabled"><a href="javascript:void(0)"><i class="glyphicon glyphicon-arrow-right"></i></a></li>
	        				</c:when>
	        				<c:otherwise>
	        					<li><a href="${pageContext.request.contextPath}/admin/category/search-${searchString }-${itemPagination}">${itemPagination }</a></li>
	        				</c:otherwise>
	        			</c:choose>
	        		</c:forEach>
	        	</c:if>
	        	
	        	<c:if test="${page == numberOfPages }">
	        		 <li class="disabled"><a href="javascript:void(0)" aria-label="Next"><span aria-hidden="true">»</span></a></li>
	        		 
	        	</c:if>
	        	<c:if test="${page != numberOfPages }">
	        		 <li><a href="${pageContext.request.contextPath}/admin/category/search-${searchString }-${page + 1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
	        	</c:if>
        	</c:if>
        </ul>
    </nav>
    <!-- /.pagination -->
    </div>
</div>
<!-- /.row -->
</div>
<!-- /.content-box-large -->