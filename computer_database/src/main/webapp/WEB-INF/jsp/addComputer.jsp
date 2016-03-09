<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>

<c:url value="/../resources/css" var="css" />
<c:url value="/../resources/js" var="js" />

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="${css}/bootstrap.min.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="${css}/font-awesome.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="${css}/main.css" />" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a href=<customLib:link uri="${pageContext.request.contextPath}/dashboard"/> class="navbar-brand"> <spring:message code="title"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="computer.add"/></h1>
					
					<form:form modelAttribute="computerToAdd" action="${pageContext.request.contextPath}/computer/add" method="POST" id="computer_form">
						<fieldset>
							<form:errors cssClass="error" style="font-weight: bold; margin-bottom:20px;"/>
							<div class="form-group">
								<label for="computerName"><spring:message code="computer.name"/></label>
								<c:set var="name_placeholder"><spring:message code="placeholder.computer.name"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="computerName" 
											name="computerName" placeholder="${name_placeholder}" 
											path="name"/>
							</div>
							
							<div class="form-group">
								<label for="introduced"><spring:message code="computer.introduced"/></label>
								<c:set var="introduced_placeholder"><spring:message code="placeholder.computer.introduced"/></c:set>
								<form:input type="date" class="form-control has-feedback" id="introduced"
									   name="introduced" placeholder="${introduced_placeholder}"
									   path="introducedDate"/>
							</div>
							
							<div class="form-group">
								<label for="discontinued"><spring:message code="computer.discontinued"/></label>  
								<c:set var="discontinued_placeholder"><spring:message code="placeholder.computer.discontinued"/></c:set>
								<form:input type="date" class="form-control has-feedback" id="discontinued"
									   		name="discontinued" placeholder="${discontinued_placeholder}"
									   		path="discontinuedDate"/>
							</div>
							
							<div class="form-group">
								<label for="companyId"><spring:message code="computer.company"/></label> 
								<form:select class="form-control has-feedback" id="companyId" name="companyId" path="companyId">
									<form:option value="0">--</form:option>

									<c:forEach items="${companies}" var="companies">
										<form:option value="${companies.id}">${companies.name}</form:option>
									</c:forEach>

								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="button.add"/>" class="btn btn-primary"/>
							or <a href=<customLib:link uri="${pageContext.request.contextPath}/dashboard" current_page="${page}"/> class="btn btn-default"><spring:message code="button.cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value="${js}/jquery.min.js" />"></script>
	<script src="<c:url value="${js}/jquery.validate.js" />"></script>
	<script src="<c:url value="${js}/jquery.validate.additional.js" />"></script>
	<script src="<c:url value="${js}/validator.js" />"></script>
</body>
</html>