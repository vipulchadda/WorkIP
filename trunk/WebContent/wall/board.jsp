<%@page import="work.ip.service.ProjectService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="project" value='<%=ProjectService.getProject(request.getParameter("project")) %>' scope="request"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Work I P</title>

    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../jquery/jquery-ui.min.css" rel="stylesheet">
    <link href="../css/board.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand"><c:out value="${project.name}"></c:out></div>
        </div>
        <a class="navbar-btn btn btn-primary btn-xs pull-right" href="/">Change Project</a>
      </div>
    </nav>

    <div class="container">
      <div class="row">
        <div class="col-sm-4">
        	<div class="title">
        		<h2>To Do 
        			<span class="new-note">
        				<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#newNoteModal" data-note-type="TO_DO">New Note &nbsp;
        					<span class="glyphicon glyphicon-edit"></span>
        				</button>
        			</span>
        		</h2>
        	</div>
	        <ul class="notes-list to-do-notes list-unstyled" data-note-state="TO_DO">
		        <c:forEach var="noteVar" items="${project.toDoNotes}">
		        	<c:set var="note" scope="request" value="${noteVar}"></c:set>
		        	<li>
				        <c:import url="note.jsp"></c:import>
			        </li>
			        <c:set var="note" scope="request" value="${null}"></c:set>
		        </c:forEach>
	        </ul>
        </div>
        <div class="col-sm-4">
	        <div class="title">
		        <h2>Work IP 
        			<span class="new-note">
        				<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#newNoteModal" data-note-type="WIP">New Note &nbsp;
        					<span class="glyphicon glyphicon-edit"></span>
        				</button>
        			</span>
        		</h2>
	        </div>
	        <ul class="notes-list wip-notes list-unstyled" data-note-state="WIP">
		        <c:forEach var="noteVar" items="${project.wipNotes}">
		        	<c:set var="note" scope="request" value="${noteVar}"></c:set>
		        	<li>
				        <c:import url="note.jsp"></c:import>
			        </li>
			        <c:set var="note" scope="request" value="${null}"></c:set>
		        </c:forEach>
	       </ul>
       </div>
        <div class="col-sm-4">
        	<div class="title">
		        <h2>Done 
        			<span class="new-note">
        				<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#newNoteModal" data-note-type="DONE">New Note &nbsp;
        					<span class="glyphicon glyphicon-edit"></span>
        				</button>
        			</span>
        		</h2>
	        </div>
	        <ul class="notes-list done-notes list-unstyled" data-note-state="DONE">
		        <c:forEach var="noteVar" items="${project.doneNotes}">
		        	<c:set var="note" scope="request" value="${noteVar}"></c:set>
		        	<li>
				        <c:import url="note.jsp"></c:import>
			        </li>
			        <c:set var="note" scope="request" value="${null}"></c:set>
		        </c:forEach>
	        </ul>
        </div>
      </div>

    </div> <!-- /container -->
	<c:import url="newNoteDialog.jsp"></c:import>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../jquery/jquery.js"></script>
    <script src="../jquery/jquery-ui.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/board.js"></script>
</body>
</html>