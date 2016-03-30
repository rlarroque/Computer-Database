<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="global/head-md.jsp"/>
</head>
<body>

<jsp:include page="global/navbar-md.jsp"/>

<form id="login-form" action="${pageContext.request.contextPath}/login" method="post" role="form"
      style="display: block;">
    <div class="row">
        <div class="col s12 m6">
            <div class="card blue darken-4">
                <div class="card-content white-text">
                    <span class="card-title">Login</span>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="text" name="username_login" id="username_login" tabindex="1"
                                   class="form-control">
                            <label for="username_login" class="white-text"><spring:message
                                    code="placeholder.login.username"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="password" name="password_login" id="password_login" tabindex="2"
                                   class="form-control">
                            <label for="password_login" class="white-text"><spring:message
                                    code="placeholder.login.password"/></label>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="card-action">
                    <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                           class="form-control btn btn-login" value="<spring:message code="button.login"/>"></div>
            </div>
        </div>
    </div>
</form>

<c:if test="${param.error != null}">
    <div id="loginError" class="alert alert-danger text-center" role="alert">
        <spring:message code="error.authentification"/>
    </div>
</c:if>

<c:if test="${param.logout != null}">
    <div id="logout" class="alert alert-success text-center" role="alert">
        <spring:message code="success.logout"/>
    </div>
</c:if>
</div>
</div>
</div>

<jsp:include page="global/scripts-md.jsp"/>

<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.additional.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/validator.js"></script>

<script type="text/javascript">
    var localized_strings = new Array();
    localized_strings['password.forgot'] = "<spring:message code='error.password.forgot' javaScriptEscape='true' />";
    localized_strings['validation.username'] = "<spring:message code='validation.username' javaScriptEscape='true' />";
    localized_strings['validation.password'] = "<spring:message code='validation.password' javaScriptEscape='true' />";
    localized_strings['validation.password.confirm'] = "<spring:message code='validation.password.confirm' javaScriptEscape='true' />";
</script>

</body>
</html>
