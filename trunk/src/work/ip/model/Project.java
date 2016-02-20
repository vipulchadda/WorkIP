package work.ip.model;

import java.util.List;

public class Project {

	private String id;

	private String name;

	private List<Version> versions;

	private List<Sprint> sprints;

	private List<Note> toDoNotes;

	private List<Note> wipNotes;

	private List<Note> doneNotes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public List<Note> getToDoNotes() {
		return toDoNotes;
	}

	public void setToDoNotes(List<Note> toDoNotes) {
		this.toDoNotes = toDoNotes;
	}

	public List<Note> getWipNotes() {
		return wipNotes;
	}

	public void setWipNotes(List<Note> wipNotes) {
		this.wipNotes = wipNotes;
	}

	public List<Note> getDoneNotes() {
		return doneNotes;
	}

	public void setDoneNotes(List<Note> doneNotes) {
		this.doneNotes = doneNotes;
	}

}
