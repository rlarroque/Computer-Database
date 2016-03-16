<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a href=<customLib:link uri="${pageContext.request.contextPath}/dashboard"/> class="navbar-brand"> <spring:message code="title"/> </a>
		
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle white-color" data-toggle="dropdown" role="button" aria-expanded="false">
					<span class="glyphicon glyphicon-user"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/logout">Logout</a></li>
				</ul>
			</li>
		</ul>
		
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle white-color" data-toggle="dropdown" role="button" aria-expanded="false">
					<span class="bfh-languages" data-language="<spring:message code="data.lang"/>" data-flags="true"></span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="?lang=en"><span class="bfh-languages" data-language="en_US" data-flags="true"></span></a></li>
					<li><a href="?lang=fr"><span class="bfh-languages" data-language="fr_FR" data-flags="true"></span></a></li>
				</ul>
			</li>
		</ul>
	</div>
</header>