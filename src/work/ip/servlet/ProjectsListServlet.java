package work.ip.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import work.ip.model.Project;
import work.ip.service.ProjectService;

@WebServlet("/service/getprojects.html")
public class ProjectsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Project> allProjects = ProjectService.getAllProjects();
		Gson gson = new Gson();
		response.getOutputStream().write(gson.toJson(allProjects).getBytes());

	}


}
