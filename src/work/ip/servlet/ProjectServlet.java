package work.ip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import work.ip.model.Project;
import work.ip.service.ProjectService;

@WebServlet("/service/project.html/*")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String pathInfo = request.getPathInfo();
		final String projectName =request.getParameter("name");
		
		String responseStr = "";
		switch (ServletOperation.get(pathInfo)) {
		case ADD:
			responseStr = addProject(projectName);
			break;
		case GET:
			responseStr = getProject(projectName);
		default:
			break;
		}

		response.getOutputStream().write(responseStr.getBytes());

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		doPost(request, response);
	}

	private String getProject(final String projectName) {
		final Gson gson = new Gson();
		final Project project = ProjectService.getProject(projectName);
		return gson.toJson(project);
	}

	private String addProject(final String projectName) {
		final Project project = new Project();
		project.setName(projectName);

		ProjectService.addProject(project);

		return "";
	}

}
