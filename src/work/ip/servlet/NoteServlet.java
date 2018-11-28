package work.ip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import work.ip.model.Note;
import work.ip.model.NoteState;
import work.ip.model.Project;
import work.ip.model.Sprint;
import work.ip.model.Version;
import work.ip.service.NoteService;

@WebServlet("/service/note.html/*")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String pathInfo = request.getPathInfo();

		String responseStr = "";
		switch (ServletOperation.get(pathInfo)) {
		case GET:
			responseStr = getNote(request);
			break;
		case ADD:
			responseStr = addNote(request);
			break;
		case DELETE:
			responseStr = deleteNote(request);
			break;
		case UPDATE:
			responseStr = updateNote(request);
			break;
		default:
			break;
		}

		response.getOutputStream().write(responseStr.getBytes());

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private String getNote(HttpServletRequest request) {
		final String noteId = request.getParameter("id");
		final Gson gson = new Gson();
		final Note note = NoteService.getNoteFromId(noteId);
		return gson.toJson(note);
	}
	
	private String addNote(HttpServletRequest request) {
		final Map<String, String[]> parameters = request.getParameterMap();
		final Note note = new Note();
		note.setName(getRequestString(parameters.get("name")));
		note.setAssignedTo(getRequestString(parameters.get("assignedTo")));
		note.setCreatedBy(getRequestString(parameters.get("createdBy")));
		note.setDescription(getRequestString(parameters.get("description")));
		if (!"".equals(getRequestString(parameters.get("estimates")))) {
			note.setEstimates(Double.valueOf(getRequestString(parameters.get("estimates"))));
		}
		if (!"".equals(getRequestString(parameters.get("currentState")))) {
			note.setCurrentState(NoteState.valueOf(getRequestString(parameters.get("currentState"))));
		} else {
			note.setCurrentState(NoteState.TO_DO);
		}
		note.setCreatedDate(new Date());
		note.setComments(new ArrayList<String>());
		final Version version = new Version();
		version.setId(getRequestString(parameters.get("version")));
		note.setVersion(version);
		final Project project = new Project();
		project.setId(getRequestString(parameters.get("project")));
		note.setProject(project);
		final Sprint sprint = new Sprint();
		sprint.setId(getRequestString(parameters.get("sprint")));
		note.setSprint(sprint);

		NoteService.addNote(note);

		return "";
	}

	private Note populateNoteFromRequest(HttpServletRequest request) {
		final Map<String, String[]> parameters = request.getParameterMap();
		final Note note = new Note();
		if (!"".equals(getRequestString(parameters.get("id")))) {
			note.setId(getRequestString(parameters.get("id")));
		}
		if (!"".equals(getRequestString(parameters.get("name")))) {
			note.setName(getRequestString(parameters.get("name")));
		}
		if (!"".equals(getRequestString(parameters.get("assignedTo")))) {
			note.setAssignedTo(getRequestString(parameters.get("assignedTo")));
		}
		if (!"".equals(getRequestString(parameters.get("createdBy")))) {
			note.setCreatedBy(getRequestString(parameters.get("createdBy")));
		}
		if (!"".equals(getRequestString(parameters.get("description")))) {
			note.setDescription(getRequestString(parameters.get("description")));
		}
		if (!"".equals(getRequestString(parameters.get("estimates")))) {
			note.setEstimates(Double.valueOf(getRequestString(parameters.get("estimates"))));
		}
		if (!"".equals(getRequestString(parameters.get("currentState")))) {
			note.setCurrentState(NoteState.valueOf(getRequestString(parameters.get("currentState"))));
		} else {
			note.setCurrentState(NoteState.TO_DO);
		}
		if (!"".equals(getRequestString(parameters.get("comments")))) {
			note.setComments(Arrays.asList(getRequestString(parameters.get("comments"))));
		}
		if (!"".equals(getRequestString(parameters.get("version")))) {
			final Version version = new Version();
			version.setId(getRequestString(parameters.get("version")));
			note.setVersion(version);
		}
		if (!"".equals(getRequestString(parameters.get("project")))) {
			final Project project = new Project();
			project.setId(getRequestString(parameters.get("project")));
			note.setProject(project);
		}
		if (!"".equals(getRequestString(parameters.get("sprint")))) {
			final Sprint sprint = new Sprint();
			sprint.setId(getRequestString(parameters.get("sprint")));
			note.setSprint(sprint);
		}
		return note;
	}

	private String deleteNote(HttpServletRequest request) {
		final String noteId = request.getParameter("noteId");
		NoteService.deleteNote(noteId);
		return "";
	}

	private String updateNote(HttpServletRequest request) {
		NoteService.updateNote(populateNoteFromRequest(request));
		return "";
	}

	private String getRequestString(final String[] requestArray) {
		if (requestArray != null && requestArray.length > 0) {
			return requestArray[0];
		}
		return "";
	}

}
