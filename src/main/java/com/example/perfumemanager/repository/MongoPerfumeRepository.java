package com.example.perfumemanager.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.example.perfumemanager.model.Perfume;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.eq;

public class MongoPerfumeRepository implements PerfumeRepository {

	private static final String FRAGRANCE_FAMILY_FIELD = "fragranceFamily";
	private static final String BRAND_FIELD = "brand";
	private static final String VOLUME_FIELD = "volume";
	private static final String RATING_FIELD = "rating";

	private final MongoCollection<Document> collection;

	public MongoPerfumeRepository(MongoClient client, String databaseName, String collectionName) {

		collection = client.getDatabase(databaseName).getCollection(collectionName);
	}

	@Override
	public void save(Perfume perfume) {

		Document document = new Document("_id", perfume.getId()).append("name", perfume.getName())
				.append(BRAND_FIELD, perfume.getBrand()).append(FRAGRANCE_FAMILY_FIELD, perfume.getFragranceFamily())
				.append(VOLUME_FIELD, perfume.getVolume()).append(RATING_FIELD, perfume.getRating());

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

		Document document = new Document("name", perfume.getName()).append(BRAND_FIELD, perfume.getBrand())
				.append(FRAGRANCE_FAMILY_FIELD, perfume.getFragranceFamily()).append(VOLUME_FIELD, perfume.getVolume())
				.append(RATING_FIELD, perfume.getRating());

		collection.updateOne(eq("_id", perfume.getId()), new Document("$set", document));
	}

	private Perfume toPerfume(Document document) {

		return new Perfume(document.getString("_id"), document.getString("name"), document.getString(BRAND_FIELD),
				document.getString(FRAGRANCE_FAMILY_FIELD), document.getInteger(VOLUME_FIELD),
				document.getDouble(RATING_FIELD));
	}
}