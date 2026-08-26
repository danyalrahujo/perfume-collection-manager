package com.example.perfumemanager.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.example.perfumemanager.model.Perfume;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

public class MongoPerfumeRepository implements PerfumeRepository {

	private final MongoClient mongoClient;
	private final MongoCollection<Document> collection;

	public MongoPerfumeRepository(String connectionString) {
		mongoClient = MongoClients.create(connectionString);

		MongoDatabase database = mongoClient.getDatabase("perfume_manager");

		collection = database.getCollection("perfumes");
	}

	@Override
	public void save(Perfume perfume) {
		Document document = new Document("_id", perfume.getId()).append("name", perfume.getName())
				.append("brand", perfume.getBrand()).append("fragranceFamily", perfume.getFragranceFamily())
				.append("volume", perfume.getVolume()).append("rating", perfume.getRating());

		collection.insertOne(document);
	}

	@Override
	public List<Perfume> findAll() {
		List<Perfume> perfumes = new ArrayList<>();

		for (Document document : collection.find()) {
			perfumes.add(toPerfume(document));
		}

		return perfumes;
	}

	@Override
	public Perfume findById(String id) {
		Document document = collection.find(eq("_id", id)).first();

		if (document == null) {
			return null;
		}

		return toPerfume(document);
	}

	@Override
	public void delete(String id) {
		collection.deleteOne(eq("_id", id));
	}

	private Perfume toPerfume(Document document) {
		return new Perfume(document.getString("_id"), document.getString("name"), document.getString("brand"),
				document.getString("fragranceFamily"), document.getInteger("volume"), document.getDouble("rating"));
	}

	@Override
	public void update(Perfume perfume) {
		// TODO Auto-generated method stub

	}
}
