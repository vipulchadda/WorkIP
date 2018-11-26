<%@page import="work.ip.service.NoteService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="note" value='<%=NoteService.getNoteFromId(request.getParameter("noteId")) %>' scope="request"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><c:out value="${note.project.name}"></c:out> | Work I P</title>

    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="/wall/board.jsp?project=${note.project.name}"><c:out value="${note.project.name}"></c:out></a>
        </div>
      </div>
    </nav>

    <div class="container">
      <div class="row">
        <div class="col-sm-12">
        	<div class="well">
        		<h3><c:out value="${note.name}"></c:out> 
       				<button class="btn btn-primary btn-sm pull-right"  data-toggle="modal" data-target="#editNoteModal">Edit &nbsp;
       					<span class="glyphicon glyphicon-edit"></span>
       				</button>
        		</h3>
        		<hr>
        		<dl class="dl-horizontal">
				  <dt>Description</dt><dd>${note.description }</dd>
				  <dt>Estimates</dt><dd>${note.estimates }</dd>
				  <dt>Version</dt><dd><c:if test="${not empty note.version}">${note.version.name}</c:if> </dd>
				  <dt>Assigned To</dt><dd>${note.assignedTo }</dd>
				  <dt>Created By</dt><dd>${note.createdBy }</dd>
				  <dt>Created Date</dt><dd>${note.createdDate }</dd>
				  <dt>Modified Date</dt><dd>${note.modifiedDate }</dd>
				  <dt>Current State</dt><dd>${note.currentState }</dd>
				  <dt>Sprint</dt><dd><c:if test="${not empty note.sprint}">${note.sprint.name}</c:if></dd>
				</dl>
        	</div>
	        
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12">
        		<div class="list-group">
        			<div class="list-group-item"><h4>Comments</h4></div>
        			<c:forEach var="comment" items="${note.comments}">
        			<div class="list-group-item"><c:out value="${comment}"></c:out></div>
        			</c:forEach>
        			<div class="list-group-item">
        				<form class="add-comment-form">
        					<div class="form-group">
	        				<textarea class="form-control" name="comments"></textarea>
	        				<input type="hidden" name="id" value="<c:out value="${note.id}"/>">
	        				</div>
	        				<button type="submit" class="btn btn-primary">Comment</button>
        				</form>
        			</div>
        		</div>
	        
        </div>
      </div>
    </div> <!-- /container -->
	<c:import url="editNoteDialog.jsp"></c:import>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../jquery/jquery.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/note.js"></script>
</body>
</html>
