package work.ip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import work.ip.model.Note;
import work.ip.model.NoteState;

public class NoteService {

	public static List<Note> getNotesForProjectOrSprint(final String type, final String typeId) {
		final List<Note> notes = new ArrayList<Note>();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("notes", BsonDocument.class);
			final Iterator<BsonDocument> notesItr = collection.find(Filters.eq(type, new ObjectId(typeId))).iterator();
			while (notesItr.hasNext()) {
				final Note note = new Note();
				getNoteFromDocument(note, notesItr.next());
				notes.add(note);
			}
		} finally {
			connector.logout();
		}
		return notes;

	}

	public static List<Note> getNotesFromIds(final BsonArray notesBsonArray) {
		final List<Note> notesList = new ArrayList<Note>();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("notes", BsonDocument.class);
			final Iterator<BsonDocument> notesItr = collection.find(Filters.in("_id", notesBsonArray)).iterator();
			while (notesItr.hasNext()) {
				final Note note = new Note();
				getNoteFromDocument(note, notesItr.next());
				notesList.add(note);
			}
		} finally {
			connector.logout();
		}
		return notesList;

	}

	public static Note getNoteFromId(final String noteId) {
		final Note note = new Note();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("notes", BsonDocument.class);
			final BsonDocument noteDocument = collection.find(Filters.eq("_id", new ObjectId(noteId))).first();
			getNoteFromDocument(note, noteDocument);
		} finally {
			connector.logout();
		}
		return note;

	}

	public static void updateNote(final Note note) {
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("notes", BsonDocument.class);
			final BsonDocument noteDocument = new BsonDocument();
			noteDocument.put("modifiedDate", new BsonDateTime(new Date().getTime()));
			populateUpdateDocument(note, noteDocument);
			final BsonDocument updateDocument = new BsonDocument("$set", noteDocument);
			if (note.getComments() != null) {
				final BsonArray commentsArray = new BsonArray();
				for (String comment : note.getComments()) {
					commentsArray.add(new BsonString(comment));
				}
				final BsonDocument commentsDocument = new BsonDocument();
				commentsDocument.append("comments", new BsonDocument("$each", commentsArray));
				updateDocument.append("$push", commentsDocument);
			}
			collection.updateOne(Filters.eq("_id", new ObjectId(note.getId())), updateDocument);
		} finally {
			connector.logout();
		}

	}

	public static void addNote(final Note note) {
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> notesCollection = connector.getCollection("notes", BsonDocument.class);
			final BsonDocument noteDocument = new BsonDocument();
			getDocumentFromNote(note, noteDocument);
			notesCollection.insertOne(noteDocument);
		} finally {
			connector.logout();
		}
	}

	public static void deleteNote(final String noteId) {
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> notesCollection = connector.getCollection("notes", BsonDocument.class);
			notesCollection.deleteOne(Filters.eq("_id", new ObjectId(noteId)));
		} finally {
			connector.logout();
		}
	}

	private static void getNoteFromDocument(final Note note, final BsonDocument noteDocument) {
		note.setId(noteDocument.getObjectId("_id").asObjectId().getValue().toHexString());
		note.setName(noteDocument.getString("name").getValue());
		note.setDescription(noteDocument.getString("description").getValue());
		note.setAssignedTo(noteDocument.getString("assignedTo").getValue());
		note.setCreatedBy(noteDocument.getString("createdBy").getValue());
		if (noteDocument.containsKey("createdDate")) {
			note.setCreatedDate(new Date(noteDocument.getDateTime("createdDate").getValue()));
		}
		if (noteDocument.containsKey("modifiedDate")) {
			note.setModifiedDate(new Date(noteDocument.getDateTime("modifiedDate").getValue()));
		}
		note.setEstimates(noteDocument.getDouble("estimates").getValue());
		note.setCurrentState(NoteState.valueOf(noteDocument.getString("currentState").getValue()));
		if (noteDocument.containsKey("version")) {
			note.setVersion(VersionService.getVersionFromId(noteDocument.getObjectId("version").getValue()));
		}
		if (noteDocument.containsKey("project")) {
			note.setProject(
					ProjectService.getProjectFromId(noteDocument.getObjectId("project").getValue().toHexString()));
		}
		if (noteDocument.containsKey("sprint")) {
			note.setSprint(SprintService.getSprintFromId(noteDocument.getObjectId("sprint").getValue()));
		}
		note.setComments(new ArrayList<String>());
		final Iterator<BsonValue> commentsBsonItr = noteDocument.getArray("comments").iterator();
		while (commentsBsonItr.hasNext()) {
			note.getComments().add(commentsBsonItr.next().asString().getValue());
		}
	}

	private static void populateUpdateDocument(final Note note, final BsonDocument noteDocument) {
		if (note.getName() != null) {
			noteDocument.put("name", new BsonString(note.getName()));
		}
		if (note.getDescription() != null) {
			noteDocument.put("description", new BsonString(note.getDescription()));
		}
		if (note.getAssignedTo() != null) {
			noteDocument.put("assignedTo", new BsonString(note.getAssignedTo()));
		}
		if (note.getCreatedBy() != null) {
			noteDocument.put("createdBy", new BsonString(note.getCreatedBy()));
		}
		if (note.getCreatedDate() != null) {
			noteDocument.put("createdDate", new BsonDateTime(note.getCreatedDate().getTime()));
		}
		if (note.getEstimates() != 0) {
			noteDocument.put("estimates", new BsonDouble(note.getEstimates()));
		}
		if (note.getCurrentState() != null) {
			noteDocument.put("currentState", new BsonString(note.getCurrentState().name()));
		}
		if (note.getVersion() != null && !"".equals(note.getVersion().getId())) {
			noteDocument.put("version", new BsonObjectId(new ObjectId(note.getVersion().getId())));
		}
		if (note.getProject() != null && !"".equals(note.getProject().getId())) {
			noteDocument.put("project", new BsonObjectId(new ObjectId(note.getProject().getId())));
		}
		if (note.getSprint() != null && !"".equals(note.getSprint().getId())) {
			noteDocument.put("sprint", new BsonObjectId(new ObjectId(note.getSprint().getId())));
		}

	}

	private static void getDocumentFromNote(final Note note, final BsonDocument noteDocument) {
		noteDocument.put("name", new BsonString(note.getName()));
		noteDocument.put("description", new BsonString(note.getDescription()));
		noteDocument.put("assignedTo", new BsonString(note.getAssignedTo()));
		noteDocument.put("createdBy", new BsonString(note.getCreatedBy()));
		if (note.getCreatedDate() != null) {
			noteDocument.put("createdDate", new BsonDateTime(note.getCreatedDate().getTime()));
		}
		if (note.getModifiedDate() != null) {
			noteDocument.put("modifiedDate", new BsonDateTime(note.getModifiedDate().getTime()));
		}
		noteDocument.put("estimates", new BsonDouble(note.getEstimates()));
		noteDocument.put("currentState", new BsonString(note.getCurrentState().name()));
		if (note.getVersion() != null && !"".equals(note.getVersion().getId())) {
			noteDocument.put("version", new BsonObjectId(new ObjectId(note.getVersion().getId())));
		}
		if (!"".equals(note.getProject().getId())) {
			noteDocument.put("project", new BsonObjectId(new ObjectId(note.getProject().getId())));
		}
		if (!"".equals(note.getSprint().getId())) {
			noteDocument.put("sprint", new BsonObjectId(new ObjectId(note.getSprint().getId())));
		}

		final BsonArray commentsArray = new BsonArray();
		for (String comment : note.getComments()) {
			commentsArray.add(new BsonString(comment));
		}

		noteDocument.put("comments", commentsArray);
	}

}
