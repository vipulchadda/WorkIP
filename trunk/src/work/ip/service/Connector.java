package work.ip.service;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connector {

	private MongoClient client;

	private MongoDatabase dbase;

	public Connector() {
		this.client = new MongoClient("localhost", 27017);
		this.dbase = client.getDatabase("workip");
	}

	public MongoCollection<Document> getCollection(final String collection) {
		return dbase.getCollection(collection);
	}

	public <T> MongoCollection<T> getCollection(final String collection, final Class<T> claz) {
		return dbase.getCollection(collection, claz);
	}

	public MongoCollection<Document> getDocument(String query) {
		return null;
	}

	public void logout() {
		if (this.client != null) {
			this.client.close();
		}
	}
}
