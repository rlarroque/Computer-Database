<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="global/head.jsp" />
</head>
<body>

   	<!-- Options used in the navbar.jsp file -->
	<c:set var="attr" value="computer=${computer.id}"></c:set>
	<%@include file="global/navbar.jsp" %>

    
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
								<label for="name"><spring:message code="computer.name"/></label>
                                <c:set var="name_placeholder"><spring:message code="placeholder.computer.name"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="name" 
											name="name" placeholder="${name_placeholder}" value="${computer.name}"
											path="name"/>
								<form:errors path="name" cssClass="error" style="font-weight: bold;"/>
							</div>
							
							<div class="form-group">
								<label for="introducedDate"><spring:message code="computer.introduced"/></label>
                                <c:set var="introduced_placeholder"><spring:message code="placeholder.computer.introduced"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="introducedDate"
									   name="introducedDate" placeholder="${introduced_placeholder}" value="${computer.introducedDate}" path="introducedDate"/>
								<form:errors path="introducedDate" cssClass="error" style="font-weight: bold;"/>
							</div>
							
							<div class="form-group">
								<label for="discontinuedDate"><spring:message code="computer.discontinued"/></label>
                                <c:set var="discontinued_placeholder"><spring:message code="placeholder.computer.discontinued"/></c:set>
								<form:input type="text" class="form-control has-feedback" id="discontinuedDate"
									   		name="discontinuedDate" placeholder="${discontinued_placeholder}" value="${computer.discontinuedDate}" path="discontinuedDate"/>
								<form:errors path="discontinuedDate" class="error" style="font-weight: bold;"/>
							</div>
							
							<div class="form-group">
								<label for="companyId"><spring:message code="computer.company"/></label> 
								<form:select class="form-control has-feedback" id="companyId" name="companyId"
											 path="CompanyId" value="${computer.companyId}">

                                    <c:choose>
                                        <c:when test="${computer.companyId > 0}">
                                            <form:option value="${computer.companyId}">${computer.companyName}</form:option>
                                        </c:when>
                                        <c:otherwise>
                                            <form:option value="0">--</form:option>
                                        </c:otherwise>
                                    </c:choose>
									<c:forEach items="${companies}" var="companies">
                                        <c:if test="${companies.id != computer.companyId}">
                                            <form:option value="${companies.id}">${companies.name}</form:option>
                                        </c:if>
									</c:forEach>

								</form:select>
							</div>
						</fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="button.edit"/>" class="btn btn-primary">
                            <a class="btn btn-default" href=<customLib:link uri="${pageContext.request.contextPath}/dashboard" current_page="${page}"/> class="btn btn-default"><spring:message code="button.cancel"/></a>
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
		localized_strings['data.lang'] = "<spring:message code='data.lang' javaScriptEscape='true' />";
		localized_strings['date.pattern'] = "<spring:message code='date.pattern' javaScriptEscape='true' />";
		localized_strings['validation.name'] = "<spring:message code='validation.name' javaScriptEscape='true' />";
		localized_strings['validation.introduced'] = "<spring:message code='validation.introduced' javaScriptEscape='true' />";
		localized_strings['validation.discontinued'] = "<spring:message code='validation.discontinued' javaScriptEscape='true' />";
		localized_strings['validation.date'] = "<spring:message code='validation.date' javaScriptEscape='true' />";
		localized_strings['validation.company'] = "<spring:message code='validation.company' javaScriptEscape='true' />";
		localized_strings['validation.success'] = "<spring:message code='success.computer.edited' javaScriptEscape='true' />";
		localized_strings['validation.date.before'] = "<spring:message code='validation.date.before' javaScriptEscape='true' />";
	</script>
	
</body>
</html>