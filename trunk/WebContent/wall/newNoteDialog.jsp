<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal fade" id="newNoteModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">New Note</h4>
      </div>
      <div class="modal-body">
        <form class="new-note-form">
          <div class="form-group" >
            <label for="recipient-name" class="control-label">Note Name:</label>
            <input type="text" class="form-control" name="name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Description:</label>
            <textarea class="form-control" name="description"></textarea>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Estimates:</label>
            <input type="text" class="form-control" name="estimates">
          </div>
          <div class="form-group">
          	<label for="currentState" class="control-label">Current State:</label>
          	<select class="form-control" id="currentState" name="currentState">
          		<option value="TO_DO" selected>To Do</option>
          		<option value="WIP">WIP</option>
          		<option value="DONE">Done</option>
          	</select>
          </div>
          <input type="hidden" name="project" value="<c:out value="${project.id}"/>">
          <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Create</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>