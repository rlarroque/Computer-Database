<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib" %>

<%@ attribute name="page" type="com.excilys.computer_database.dto.PageDTO" required="true" description="Contains the information to build page links" %>
<%@ attribute name="langList" type="java.util.List" required="true" description="List of available languages" %>

<ul class="pagination">

	<c:choose>
		<c:when test="${page.currentPage < '2'}"> <c:set var="class_to_add" value="not-active disabled"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>

	<li class="${class_to_add}">
		<a href=<customLib:link uri="dashboard" current_page="${page}" override_page="1"/> class="${class_to_add}"> <span aria-hidden="true" style="font-weight:bold">  &laquo; </span> </a>
	</li>

	<c:choose>
		<c:when test="${page.currentPage == '1'}"> <c:set var="class_to_add" value="not-active disabled"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>

	<li class="${class_to_add}">
		<a href=<customLib:link uri="dashboard" current_page="${page}" override_page="${page.currentPage - 1}"/> class="${class_to_add}"> <span aria-hidden="true" style="font-weight:bold">  &lsaquo; </span> </a>
	</li>
	
	<c:forEach begin="${page.startPage}" end="${page.endPage}" var="val">
		<li <c:if test="${page.currentPage == val}"> class="active" </c:if> >
			<a href=<customLib:link uri="dashboard" current_page="${page}" override_page="${val}"/> > <span aria-hidden="true"> ${val} </span> </a>
		</li>
	</c:forEach>

	<c:choose>
		<c:when test="${page.currentPage == page.totalPage}"> <c:set var="class_to_add" value="not-active disabled"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>

	<li class="${class_to_add}">
		<a href=<customLib:link uri="dashboard" current_page="${page}" override_page="${page.currentPage + 1}"/> class="${class_to_add}"> <span aria-hidden="true" style="font-weight:bold">  &rsaquo; </span> </a>
	</li>


	<c:choose>
		<c:when test="${page.currentPage == page.totalPage}"> <c:set var="class_to_add" value="not-active disabled"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>

	<li class="${class_to_add}">
		<a href=<customLib:link uri="dashboard" current_page="${page}" override_page="${page.totalPage}"/> class="${class_to_add}"> <span aria-hidden="true" style="font-weight:bold">  &raquo; </span> </a>
	</li>
	
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">

	<c:choose>
		<c:when test="${page.offset == 10}"> <c:set var="class_to_add" value="active"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>	
	<a href=<customLib:link  uri="dashboard" current_page="${page}" override_offset="10" override_page="1"/> class="${class_to_add} btn btn-default"> <span aria-hidden="true"> 10 </span> </a>
	
	<c:choose>
		<c:when test="${page.offset == 50}"> <c:set var="class_to_add" value="active"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>	
	<a href=<customLib:link uri="dashboard" current_page="${page}" override_offset="50" override_page="1"/> class="${class_to_add} btn btn-default"> <span aria-hidden="true">  50 </span> </a>
	
	<c:choose>
		<c:when test="${page.offset == 100}"> <c:set var="class_to_add" value="active"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
	</c:choose>	
	<a href=<customLib:link uri="dashboard" current_page="${page}" override_offset="100" override_page="1"/> class="${class_to_add} btn btn-default"> <span aria-hidden="true"> 100 </span> </a>

</div>