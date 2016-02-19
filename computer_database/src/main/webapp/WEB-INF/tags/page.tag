<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customLib" %>

<%@ attribute name="page" type="com.excilys.computer_database.dto.PageDTO" required="true" description="Contains the information to build page links" %>

<ul class="pagination">
	
	<li>
		<c:choose>
			<c:when test="${page.current_page < '2'}"> <c:set var="class_to_add" value="not-active"/> </c:when>
			<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
		</c:choose>	
		<customLib:link m_class="${class_to_add}" uri="display_computers" page="${page}" text="&laquo;" override_page="1" span="true"/>
	</li>

	<li>
		<c:choose>
			<c:when test="${page.current_page == '1'}"> <c:set var="class_to_add" value="not-active"/> </c:when>
			<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
		</c:choose>	
		<customLib:link m_class="${class_to_add}" uri="display_computers" page="${page}" text="&lsaquo;" override_page="${page.current_page - 1}" span="true"/>
	</li>
	
	<c:forEach begin="${page.start_page}" end="${page.end_page}" var="val">
		<li <c:if test="${page.current_page == val}"> class="active" </c:if> >
			<customLib:link m_class="" uri="display_computers" page="${page}" text="${val}" override_page="${val}" span="true"/>
		</li>
	</c:forEach>
	
	<li>
		<c:choose>
			<c:when test="${page.current_page == page.total_page}"> <c:set var="class_to_add" value="not-active"/> </c:when>
			<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
		</c:choose>	
		<customLib:link m_class="${class_to_add}" uri="display_computers" page="${page}" text="&rsaquo;" override_page="${page.current_page + 1}" span="true"/>
	</li>

	<li>
		<c:choose>
			<c:when test="${page.current_page == page.total_page}"> <c:set var="class_to_add" value="not-active"/> </c:when>
			<c:otherwise> <c:set var="class_to_add" value=""/>  </c:otherwise>
		</c:choose>	
		<customLib:link m_class="${class_to_add}" uri="display_computers" page="${page}" text="&raquo;" override_page="${page.total_page}" span="true"/>
	</li>
	
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">

	<c:choose>
		<c:when test="${page.offset == 10}"> <c:set var="class_to_add" value="not-active"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value="active"/>  </c:otherwise>
	</c:choose>	
	<customLib:link m_class="${class_to_add} btn btn-default" uri="display_computers" page="${page}" text="10" override_offset="10" span="true"/>
	
	<c:choose>
		<c:when test="${page.offset == 50}"> <c:set var="class_to_add" value="not-active"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value="active"/>  </c:otherwise>
	</c:choose>	
	<customLib:link m_class="${class_to_add} btn btn-default" uri="display_computers" page="${page}" text="50" override_offset="50" span="true"/>
	
	<c:choose>
		<c:when test="${page.offset == 100}"> <c:set var="class_to_add" value="not-active"/> </c:when>
		<c:otherwise> <c:set var="class_to_add" value="active"/>  </c:otherwise>
	</c:choose>	
	<customLib:link m_class="${class_to_add} btn btn-default" uri="display_computers" page="${page}" text="100" override_offset="100" span="true"/>

</div>