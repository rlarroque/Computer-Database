<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>
<%@ page isErrorPage="true"%>

<c:url value="/../resources/css" var="css" />
<c:url value="/../resources/js" var="js" />

<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="${css}/bootstrap.min.css" />"
	rel="stylesheet" media="screen">
<link href="<c:url value="${css}/font-awesome.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="${css}/main.css" />" rel="stylesheet"
	media="screen">
</head>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a href=<customLib:link uri="dashboard"/> class="navbar-brand"> <spring:message code="title"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">	
			<div class="alert alert-danger">
				<spring:message code="error"/> 500 : <spring:message code="error.500"/>
				<br/>
			</div>
			
			<!-- stacktrace -->
			<p> <spring:message code="error.reason"/> : ${exception.message}</p>
		</div>
	</section>

	<script src="<c:url value="${js}/jquery.min.js" />"></script>
	<script src="<c:url value="${js}/bootstrap.min.js" />"></script>
	<script src="<c:url value="${js}/dashboard.js" />"></script>

</body>
</html>