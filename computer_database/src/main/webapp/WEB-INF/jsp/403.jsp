<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>
<%@ page isErrorPage="true"%>

<c:url value="/../resources/css" var="css" />
<c:url value="/../resources/js" var="js" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="global/head.jsp" />
</head>
<body>

    <jsp:include page="global/navbar.jsp" />
    
    <section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message code="error"/> 403 : <spring:message code="error.403"/>
				<br/>
			</div>
		</div>
	</section>

	<jsp:include page="global/scripts.jsp" />

</body>
</html>