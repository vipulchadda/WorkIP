<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal fade" id="editNoteModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">Edit Note</h4>
      </div>
      <div class="modal-body">
        <form class="edit-note-form">
          <div class="form-group" >
            <label for="name" class="control-label">Note Name:</label>
            <input type="text" class="form-control" name="name" value="${note.name}">
          </div>
          <div class="form-group">
            <label for="description" class="control-label">Description:</label>
            <textarea class="form-control" name="description"><c:out value="${note.description}"></c:out></textarea>
          </div>
          <div class="form-group">
            <label for="estimates" class="control-label">Estimates:</label>
            <input type="text" class="form-control" name="estimates" value="${note.estimates}">
          </div>
          <div class="form-group">
          	<label for="currentState" class="control-label">Current State:</label>
          	<select class="form-control" id="currentState" name="currentState">
          		<option value="TO_DO" ${note.currentState == "TO_DO" ? 'selected' : '' }>To Do</option>
          		<option value="WIP" ${note.currentState == "WIP" ? 'selected' : '' }>WIP</option>
          		<option value="DONE" ${note.currentState == "DONE" ? 'selected' : '' }>Done</option>
          	</select>
          </div>
          <input type="hidden" name="project" value="<c:out value="${note.project.id}"/>">
          <input type="hidden" name="id" value="<c:out value="${note.id}"/>">
          <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Update</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>