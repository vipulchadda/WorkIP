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

import work.ip.model.Timeline;
import work.ip.model.Version;

public class VersionService {

	public static List<Version> getVersionsFromIds(final BsonArray versionBsonArray) {
		final List<Version> versionList = new ArrayList<Version>();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("versions", BsonDocument.class);
			final Iterator<BsonDocument> versionItr = collection.find(Filters.in("_id", versionBsonArray)).iterator();
			while (versionItr.hasNext()) {
				final Version version = new Version();
				getVersionFromDocument(version, versionItr.next());
				versionList.add(version);
			}
		} finally {
			connector.logout();
		}
		return versionList;

	}

	public static Version getVersionFromId(final ObjectId versionId) {
		final Version version = new Version();
		final Connector connector = new Connector();
		try {
			final MongoCollection<BsonDocument> collection = connector.getCollection("versions", BsonDocument.class);
			final BsonDocument versionDocument = collection.find(Filters.eq("_id", versionId)).first();
			getVersionFromDocument(version, versionDocument);
		} finally {
			connector.logout();
		}
		return version;

	}

	private static void getVersionFromDocument(final Version version, final BsonDocument versionDocument) {
		version.setId(versionDocument.getObjectId("_id").getValue().toHexString());
		version.setName(versionDocument.getString("name").getValue());
		final Timeline timeline = new Timeline();
		timeline.setStartDate(new Date(versionDocument.getDateTime("startDate").getValue()));
		timeline.setEndDate(new Date(versionDocument.getDateTime("endDate").getValue()));
		version.setTimeline(timeline);
	}

}
