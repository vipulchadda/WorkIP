package work.ip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import work.ip.model.Note;
import work.ip.model.Sprint;
import work.ip.model.Timeline;

public class SprintService {

	public static List<Sprint> getSprintsFromIds(final BsonArray sprintBsonArray) {
		final List<Sprint> sprintList = new ArrayList<Sprint>();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("sprints", BsonDocument.class);
			final Iterator<BsonDocument> sprintItr = collection.find(Filters.in("_id", sprintBsonArray)).iterator();
			while (sprintItr.hasNext()) {
				final Sprint sprint = new Sprint();
				getSprintFromDocument(sprint, sprintItr.next());
				sprintList.add(sprint);
			}
		} finally {
			connector.logout();
		}
		return sprintList;

	}

	public static Sprint getSprintFromId(final ObjectId sprintId) {
		final Sprint sprint = new Sprint();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("sprints", BsonDocument.class);
			final BsonDocument sprintDocument = collection.find(Filters.eq("_id", sprintId)).first();
			getSprintFromDocument(sprint, sprintDocument);
		} finally {
			connector.logout();
		}
		return sprint;

	}

	private static void getSprintFromDocument(final Sprint sprint, final BsonDocument sprintDocument) {
		arrangeNotes(sprint, NoteService.getNotesForProjectOrSprint("sprint",
				sprintDocument.getObjectId("_id").getValue().toHexString()));
		arrangeNotes(sprint, NoteService.getNotesFromIds(sprintDocument.getArray("notes")));
		sprint.setId(sprintDocument.getObjectId("_id").getValue().toHexString());
		sprint.setName(sprintDocument.getString("name").getValue());
		final Timeline timeline = new Timeline();
		timeline.setStartDate(new Date(sprintDocument.getDateTime("startDate").getValue()));
		timeline.setEndDate(new Date(sprintDocument.getDateTime("endDate").getValue()));
		sprint.setTimeline(timeline);
	}

	private static void arrangeNotes(Sprint sprint, List<Note> allNotes) {
		for (Note note : allNotes) {
			switch (note.getCurrentState()) {
			case TO_DO:
				sprint.getToDoNotes().add(note);
			case WIP:
				sprint.getWipNotes().add(note);
			case DONE:
				sprint.getDoneNotes().add(note);
			}
		}
	}

}
