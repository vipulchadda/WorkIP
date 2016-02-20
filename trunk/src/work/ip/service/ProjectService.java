package work.ip.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import work.ip.model.Note;
import work.ip.model.Project;

public class ProjectService {

	public static Project getProject(final String projectName) {
		final Project project = new Project();
		project.setName(projectName);
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("projects", BsonDocument.class);
			final BsonDocument projectDoc = collection.find(Filters.eq("name", projectName)).first();
			getProjectFromDocument(project, projectDoc, true);
		} finally {
			connector.logout();
		}
		return project;

	}

	public static Project getProjectFromId(final String projectId) {
		final Project project = new Project();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("projects", BsonDocument.class);
			final BsonDocument projectDoc = collection.find(Filters.eq("_id", new ObjectId(projectId))).first();
			getProjectFromDocument(project, projectDoc, false);
		} finally {
			connector.logout();
		}
		return project;

	}

	public static List<Project> getAllProjects() {
		final List<Project> projects = new ArrayList<Project>();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("projects", BsonDocument.class);
			final Iterator<BsonDocument> projectsItr = collection.find().iterator();
			while (projectsItr.hasNext()) {
				final Project project = new Project();
				getProjectFromDocument(project, projectsItr.next(), true);
				projects.add(project);
			}
		} finally {
			connector.logout();
		}
		return projects;
	}

	public static void addProject(final Project project) {
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> projectsCollection = connector.getCollection("projects",
					BsonDocument.class);
			final BsonDocument projectDocument = new BsonDocument();
			getDocumentFromProject(project, projectDocument);
			projectsCollection.insertOne(projectDocument);
		} finally {
			connector.logout();
		}
	}

	private static void getDocumentFromProject(final Project project, final BsonDocument projectDocument) {
		projectDocument.put("name", new BsonString(project.getName()));
		projectDocument.put("versions", new BsonArray());
		projectDocument.put("sprints", new BsonArray());
	}

	private static void getProjectFromDocument(final Project project, final BsonDocument projectDocument,
			final boolean populateNotes) {
		project.setId(projectDocument.getObjectId("_id").getValue().toHexString());
		project.setName(projectDocument.getString("name").getValue());

		final BsonArray versionsBsonArray = projectDocument.getArray("versions");
		final BsonArray sprintsBsonArray = projectDocument.getArray("sprints");
		project.setVersions(VersionService.getVersionsFromIds(versionsBsonArray));
		project.setSprints(SprintService.getSprintsFromIds(sprintsBsonArray));
		if (populateNotes) {
			if (project.getToDoNotes() == null) {
				project.setToDoNotes(new ArrayList<Note>());
			}
			if (project.getWipNotes() == null) {
				project.setWipNotes(new ArrayList<Note>());
			}
			if (project.getDoneNotes() == null) {
				project.setDoneNotes(new ArrayList<Note>());
			}
			arrangeNotes(project, NoteService.getNotesForProjectOrSprint("project",
					projectDocument.getObjectId("_id").getValue().toHexString()));
		}
	}

	private static void arrangeNotes(Project project, List<Note> allNotes) {
		for (Note note : allNotes) {
			switch (note.getCurrentState()) {
			case TO_DO:
				project.getToDoNotes().add(note);
				break;
			case WIP:
				project.getWipNotes().add(note);
				break;
			case DONE:
				project.getDoneNotes().add(note);
				break;
			}
		}
	}

}
