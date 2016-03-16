<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="${pageContext.request.contextPath}/../../resources/js/" var="js" />

<script src="<c:url value="${js}jquery.min.js" />"></script>
<script src="<c:url value="${js}bootstrap.min.js" />"></script>
<script src="<c:url value="${js}bootstrap-formhelpers.js" />"></script>
<script src="<c:url value="${js}dashboard.js" />"></script>
<script src="<c:url value="${js}login.js" />"></script>