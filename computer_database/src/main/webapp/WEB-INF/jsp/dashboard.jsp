<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customLib"%>

<c:url value="/../resources/css" var="css" />
<c:url value="/../resources/js" var="js" />

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<customLib:link m_class="navbar-brand" 
							uri="display_computers" 
							text="Application - Computer Database"/>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.total_computer} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="search" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="add_computer">Add Computer</a> <a
						class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="delete_computers" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span id="deleteContainer"
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th> <div class="parent">
								<customLib:link m_class="child fa fa-sort-asc" uri="display_computers" current_page="${page}" text="" override_order="name" override_order_type="ASC"/>
							 	<customLib:link m_class="child fa fa-sort-desc" uri="display_computers" current_page="${page}" text="" override_order="name" override_order_type="DESC"/>
						 	</div>
						 	<div class="child_link" >Computer name</div>
						 </th>
						
						<th>
							<div class="parent">
								<customLib:link m_class="child fa fa-sort-asc" uri="display_computers" current_page="${page}" text="" override_order="introduced" override_order_type="ASC"/>
							 	<customLib:link m_class="child fa fa-sort-desc" uri="display_computers" current_page="${page}" text="" override_order="introduced" override_order_type="DESC"/>
						 	</div>
						 	<div class="child_link" >Introduced date</div>
					 	</th>
						
						<th>
						<div class="parent">
								<customLib:link m_class="child fa fa-sort-asc" uri="display_computers" current_page="${page}" text="" override_order="discontinued" override_order_type="ASC"/>
							 	<customLib:link m_class="child fa fa-sort-desc" uri="display_computers" current_page="${page}" text="" override_order="discontinued" override_order_type="DESC"/>
						 	</div>
						 	<div class="child_link" >Discontinued date</div>
						</th>
						
						<th>
							<div class="parent">
								<customLib:link m_class="child fa fa-sort-asc" uri="display_computers" current_page="${page}" text="" override_order="company_id" override_order_type="ASC"/>
							 	<customLib:link m_class="child fa fa-sort-desc" uri="display_computers" current_page="${page}" text="" override_order="company_id" override_order_type="DESC"/>
						 	</div>
						 	<div class="child_link" >Company</div>
						</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->

				<tbody id="results">
					<c:forEach items="${page.computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="edit_computer?computer=${computer.id}" onclick="">${computer.name}</a></td>
							<td>${computer.introducedDate}</td>
							<td>${computer.discontinuedDate}</td>
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
		
			<customLib:page page="${page}"></customLib:page>
			
		</div>
	</footer>
	<script src="<c:url value="${js}/jquery.min.js" />"></script>
	<script src="<c:url value="${js}/bootstrap.min.js" />"></script>
	<script src="<c:url value="${js}/dashboard.js" />"></script>

</body>
</html>