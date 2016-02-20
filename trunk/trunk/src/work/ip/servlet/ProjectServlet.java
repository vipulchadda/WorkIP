package work.ip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import work.ip.model.Project;
import work.ip.service.ProjectService;

@WebServlet("/service/project.html/*")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String pathInfo = request.getPathInfo();

		String responseStr = "";
		switch (ServletOperation.get(pathInfo)) {
		case ADD:
			responseStr = addProject(request);
			break;
		default:
			break;
		}

		response.getOutputStream().write(responseStr.getBytes());

	}

	private String addProject(HttpServletRequest request) {
		final Project project = new Project();
		project.setName(request.getParameter("name"));

		ProjectService.addProject(project);

		return "";
	}

}
