<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="global/head.jsp" />
</head>
<body>

	<jsp:include page="global/navbar.jsp" />

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="computer.add"/></h1>
					
					<form:form modelAttribute="computerToAdd" action="${pageContext.request.contextPath}/computer/add" method="POST" id="computer_form" name="computer_form">
						<fieldset>
							<form:errors cssClass="error" style="font-weight: bold; margin-bottom:20px;"/>
							<div class="form-group">
								<label for="name"><spring:message code="computer.name"/></label>
								<c:set var="name_placeholder"><spring:message code="placeholder.computer.name"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="name" 
											placeholder="${name_placeholder}" path="name"/>
								<form:errors path="name" cssClass="error" style="font-weight: bold; margin-bottom:20px;"/>
							</div>
							
							<div class="form-group">
								<label for="introducedDate"><spring:message code="computer.introduced"/></label>
								<c:set var="introduced_placeholder"><spring:message code="placeholder.computer.introduced"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="introducedDate"
									   	    placeholder="${introduced_placeholder}" path="introducedDate"/>
								<form:errors path="introducedDate" cssClass="error" style="font-weight: bold; margin-bottom:20px;"/>
							</div>
							
							<div class="form-group">
								<label for="discontinuedDate"><spring:message code="computer.discontinued"/></label>
								<c:set var="discontinued_placeholder"><spring:message code="placeholder.computer.discontinued"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="discontinuedDate"
									   		placeholder="${discontinued_placeholder}" path="discontinuedDate"/>
								<form:errors path="discontinuedDate" cssClass="error" style="font-weight: bold; margin-bottom:20px;"/>
							</div>
							
							<div class="form-group">
								<label for="companyId"><spring:message code="computer.company"/></label> 
								<form:select class="form-control has-feedback" id="companyId" path="companyId">
									<form:option value="0">--</form:option>

									<c:forEach items="${companies}" var="companies">
										<form:option value="${companies.id}">${companies.name}</form:option>
									</c:forEach>

								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="button.add"/>" class="btn btn-primary"/>
							or <a href=<customLib:link uri="${pageContext.request.contextPath}/dashboard" current_page="${page}"/> ><spring:message code="button.cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="global/scripts.jsp" />
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.additional.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/validator.js"></script>
	
	<script type="text/javascript">
		var localized_strings = new Array();
		localized_strings['pattern'] = "<spring:message code='date.pattern.validation' javaScriptEscape='true' />";
		localized_strings['date.pattern'] = "<spring:message code='date.pattern' javaScriptEscape='true' />";
		localized_strings['validation.name'] = "<spring:message code='validation.name' javaScriptEscape='true' />";
		localized_strings['validation.introduced'] = "<spring:message code='validation.introduced' javaScriptEscape='true' />";
		localized_strings['validation.discontinued'] = "<spring:message code='validation.discontinued' javaScriptEscape='true' />";
		localized_strings['validation.date'] = "<spring:message code='validation.date' javaScriptEscape='true' />";
		localized_strings['validation.company'] = "<spring:message code='validation.company' javaScriptEscape='true' />";
		localized_strings['validation.success'] = "<spring:message code='success.computer.created' javaScriptEscape='true' />";
	</script>
	
</body>
</html>