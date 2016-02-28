<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="note" data-note-id="<c:out value="${note.id }"></c:out>">
  <button type="button" class="close delete" aria-label="Delete"><span aria-hidden="true">×</span></button>
  <h4><c:out value="${note.name}"></c:out></h4>
  <p class="note-description"><c:out value="${note.description }"></c:out></p>
  <p><a class="view-details" href="/note/noteDetails.jsp?noteId=${note.id}" role="button">View details &raquo;</a></p>
  <p class="estimates">${note.estimates}</p>
</div>