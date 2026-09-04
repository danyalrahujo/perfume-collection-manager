package com.example.perfumemanager.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.example.perfumemanager.model.Perfume;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.eq;

public class MongoPerfumeRepository implements PerfumeRepository {

	private final MongoCollection<Document> collection;

	public MongoPerfumeRepository(MongoClient client, String databaseName, String collectionName) {

		collection = client.getDatabase(databaseName).getCollection(collectionName);
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

	@Override
	public void update(Perfume perfume) {

		Document document = new Document("name", perfume.getName()).append("brand", perfume.getBrand())
				.append("fragranceFamily", perfume.getFragranceFamily()).append("volume", perfume.getVolume())
				.append("rating", perfume.getRating());

		collection.updateOne(eq("_id", perfume.getId()), new Document("$set", document));
	}

	private Perfume toPerfume(Document document) {

		return new Perfume(document.getString("_id"), document.getString("name"), document.getString("brand"),
				document.getString("fragranceFamily"), document.getInteger("volume"), document.getDouble("rating"));
	}
}