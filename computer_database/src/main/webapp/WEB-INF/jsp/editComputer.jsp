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
           <customLib:link m_class="navbar-brand" uri="display_computers" page="${page}" text="Application - Computer Database"/>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="edit_computer" method="POST" id="computer_form">
                        <fieldset>
                        	<input type="text" id="computerId" name="computerId" value="${computer.id}" hidden="true" />
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" 
                                	   placeholder="${computer.name}">
                            </div>
                            
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced"
                                       placeholder="${computer.introducedDate}" value="${computer.introducedDate}">
                            </div>
                            
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued"
                                	   placeholder="${computer.discontinuedDate}" value="${computer.discontinuedDate}">
                            </div>
                            
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                    <option value="0">--</option>
                                    
                        			<c:forEach items="${companies}" var="companies">
	                        			
	                        			<!-- Make the computer's company the default choice -->
										<option value="${companies.id}" 
											<c:if test="${companies.id == computer.companyId}"> selected="selected" </c:if> >
											${companies.name}</option>
                        				
									</c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <customLib:link m_class="btn btn-default" uri="display_computers" page="${page}" text="Cancel"/>
                        </div>
                    </form>
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