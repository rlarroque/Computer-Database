<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="page" type="com.excilys.computer_database.dto.PageDTO" required="true" description="Contains the information to build page links" %>


<ul class="pagination">

	<c:if test="${page.current_page > '2'}">
		<li>
			<a href="/computer_database/displayComputers?page=1&offset=${page.offset}" aria-label="First">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:if>
	
	<c:if test="${page.current_page != '1'}">
		<li>
			<a href="/computer_database/displayComputers?page=${page.current_page - 1}&offset=${page.offset}" aria-label="Previous">
				<span aria-hidden="true">&lsaquo;</span>
			</a>
		</li>
	</c:if>
	
	<c:forEach begin="${page.start_page}" end="${page.end_page - 1}" var="val">
		<li <c:if test="${page.current_page == val}"> class="active" </c:if> >
			<a href="/computer_database/displayComputers?page=${val}&offset=${page.offset}"> ${val} </a>
		</li>
	</c:forEach>
	
	<c:if test="${page.current_page != page.total_page}">
		<li>
			<a href="/computer_database/displayComputers?page=${page.current_page + 1}&offset=${page.offset}" aria-label="Next">
				<span aria-hidden="true">&rsaquo;</span>
			</a>
		</li>
	</c:if>
	
	<c:if test="${page.current_page < (page.total_page -1)}">
		<li>
			<a href="/computer_database/displayComputers?page=${page.total_page}&offset=${page.offset}" aria-label="Last">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</c:if>
	
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">

	<a href="/computer_database/displayComputers?page=${page.current_page}&offset=10" class="btn btn-default 
		<c:if test="${page_constructor.offset} == 10"> active </c:if> "> 10 </a>
	
	<a href="/computer_database/displayComputers?page=${page.current_page}&offset=50" class="btn btn-default 
		<c:if test="${page_constructor.offset} == 50"> active </c:if> "> 50 </a>
	
	<a href="/computer_database/displayComputers?page=${page.current_page}&offset=100" class="btn btn-default 
		<c:if test="${page_constructor.offset} == 100"> active </c:if> "> 100 </a>
	
</div>