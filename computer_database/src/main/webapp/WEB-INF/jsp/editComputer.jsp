<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1><spring:message code="computer.edit"/></h1>

                    <form:form modelAttribute="computerToEdit" action="${pageContext.request.contextPath}/computer/edit" method="POST" id="computer_form">
						<fieldset>
						
							<form:input type="hidden" name="id" id="computerId" value="${computer.id}" path="id" />
							
							<form:errors cssClass="error" style="font-weight: bold; margin-bottom:20px;"/>
							<div class="form-group">
								<label for="computerName"><spring:message code="computer.name"/></label>
								<form:input type="text" class="form-control has-feedback" id="computerName" 
											name="computerName" placeholder="${computer.name}" 
											path="name"/>
								<form:errors path="name" cssClass="error" style="font-weight: bold;"/>
							</div>
							
							<div class="form-group">
								<label for="introduced"><spring:message code="computer.introduced"/></label>
								<form:input type="date" class="form-control has-feedback" id="introduced"
									   name="introduced" placeholder="${computer.introducedDate}"
									   value="${computer.introducedDate}" path="introducedDate"/>
								<form:errors path="introducedDate" cssClass="error" style="font-weight: bold;"/>
							</div>
							
							<div class="form-group">
								<label for="discontinued"><spring:message code="computer.discontinued"/></label>
								<form:input type="date" class="form-control has-feedback" id="discontinued"
									   		name="discontinued" placeholder="${computer.discontinuedDate}"
									   		value="${computer.discontinuedDate}" path="discontinuedDate"/>
								<form:errors path="discontinuedDate" class="error" style="font-weight: bold;"/>
							</div>
							
							<div class="form-group">
								<label for="companyId"><spring:message code="computer.company"/></label> 
								<form:select class="form-control has-feedback" id="companyId" name="companyId" path="CompanyId">
									<form:option value="0">--</form:option>

									<c:forEach items="${companies}" var="companies">
										<form:option value="${companies.id}">${companies.name}</form:option>
									</c:forEach>

								</form:select>
							</div>
						</fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="button.edit"/>" class="btn btn-primary">
                            or
                            <a href=<customLib:link uri="${pageContext.request.contextPath}/dashboard" current_page="${page}"/> class="btn btn-default"><spring:message code="button.cancel"/></a>
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