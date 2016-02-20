<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal fade" id="newProjectModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">New Project</h4>
      </div>
      <div class="modal-body">
        <form class="new-project-form">
          <div class="form-group" >
            <label for="name" class="control-label">Project Name:</label>
            <input type="text" class="form-control" name="name">
          </div>
          <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Create</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>