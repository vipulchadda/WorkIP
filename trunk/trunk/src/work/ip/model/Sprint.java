package work.ip.model;

import java.util.List;

public class Sprint {

	private String name;

	private Timeline timeline;

	private List<Note> toDoNotes;

	private List<Note> wipNotes;

	private List<Note> doneNotes;

	private String id;

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

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
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
