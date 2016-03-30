<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-fixed">
    <nav>
        <div class="nav-wrapper blue darken-4">
            <a href="#" class="brand-logo">Computer Database</a>
            <ul class="right hide-on-med-and-down">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><i class="material-icons right">mood</i>${pageContext.request.userPrincipal.name}</li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>
    </nav>
</div>
