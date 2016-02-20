<%@page import="work.ip.service.ProjectService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="projects" value='<%=ProjectService.getAllProjects() %>'></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Project Selection</title>

    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/project.css" rel="stylesheet">

</head>
<body>

    <div class="container container-table">
	    <div class="row vertical-center-row">
		    <div class="col-lg-4 col-lg-offset-4 well">
		    	<p>Select a Project:
		    	<button class="btn btn-primary btn-xs pull-right" data-toggle="modal" data-target="#newProjectModal">New Project &nbsp;
   					<span class="glyphicon glyphicon-edit"></span>
   				</button></p>
		    	<div class="input-group">
				    <select class="form-control" id="project">
			    	<c:forEach var="project" items="${projects}">
			    		<option value="${project.name}"><c:out value="${project.name}"></c:out></option>
			    	</c:forEach>
				    </select>
				    <span class="input-group-btn">
				    	<button class="btn btn-primary" id="open-project">Go &raquo;</button>
				    </span>
			    </div>
			</div>
		</div>
    </div> <!-- /container -->
    
    <c:import url="newProjectDialog.jsp"></c:import>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../jquery/jquery.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/project.js"></script>
</body>
</html>