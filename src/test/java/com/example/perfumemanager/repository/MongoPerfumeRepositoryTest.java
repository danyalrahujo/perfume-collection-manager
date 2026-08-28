package com.example.perfumemanager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bson.Document;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.example.perfumemanager.model.Perfume;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;

public class MongoPerfumeRepositoryTest {

	@Test
	public void testSave() {

		MongoClient mongoClient = mock(MongoClient.class);
		MongoDatabase database = mock(MongoDatabase.class);
		MongoCollection<Document> collection = mock(MongoCollection.class);

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);
		when(database.getCollection("perfumes")).thenReturn(collection);

		try (MockedStatic<MongoClients> mockedMongoClients = Mockito.mockStatic(MongoClients.class)) {

			mockedMongoClients.when(() -> MongoClients.create("mongodb://localhost:27017")).thenReturn(mongoClient);

			MongoPerfumeRepository repository = new MongoPerfumeRepository("mongodb://localhost:27017");

			Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

			repository.save(perfume);

			verify(collection).insertOne(any(Document.class));
		}
	}

	@Test
	public void testFindByIdReturnsPerfume() {

		MongoClient mongoClient = mock(MongoClient.class);
		MongoDatabase database = mock(MongoDatabase.class);
		MongoCollection<Document> collection = mock(MongoCollection.class);

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);
		when(database.getCollection("perfumes")).thenReturn(collection);

		Document document = new Document("_id", "p001").append("name", "Sauvage").append("brand", "Dior")
				.append("fragranceFamily", "Woody").append("volume", 100).append("rating", 4.5);

		FindIterable<Document> findIterable = mock(FindIterable.class);

		when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(findIterable);

		when(findIterable.first()).thenReturn(document);

		try (MockedStatic<MongoClients> mockedMongoClients = Mockito.mockStatic(MongoClients.class)) {

			mockedMongoClients.when(() -> MongoClients.create("mongodb://localhost:27017")).thenReturn(mongoClient);

			MongoPerfumeRepository repository = new MongoPerfumeRepository("mongodb://localhost:27017");

			Perfume result = repository.findById("p001");

			assertEquals("p001", result.getId());
			assertEquals("Sauvage", result.getName());
			assertEquals("Dior", result.getBrand());
			assertEquals("Woody", result.getFragranceFamily());
			assertEquals(100, result.getVolume());
			assertEquals(4.5, result.getRating(), 0.001);
		}
	}

	@Test
	public void testFindByIdReturnsNullWhenNotFound() {

		MongoClient mongoClient = mock(MongoClient.class);
		MongoDatabase database = mock(MongoDatabase.class);
		MongoCollection<Document> collection = mock(MongoCollection.class);

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);
		when(database.getCollection("perfumes")).thenReturn(collection);

		FindIterable<Document> findIterable = mock(FindIterable.class);

		when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(findIterable);

		when(findIterable.first()).thenReturn(null);

		try (MockedStatic<MongoClients> mockedMongoClients = Mockito.mockStatic(MongoClients.class)) {

			mockedMongoClients.when(() -> MongoClients.create("mongodb://localhost:27017")).thenReturn(mongoClient);

			MongoPerfumeRepository repository = new MongoPerfumeRepository("mongodb://localhost:27017");

			assertNull(repository.findById("does-not-exist"));
		}
	}

	@Test
	public void testDelete() {

		MongoClient mongoClient = mock(MongoClient.class);
		MongoDatabase database = mock(MongoDatabase.class);
		MongoCollection<Document> collection = mock(MongoCollection.class);

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);
		when(database.getCollection("perfumes")).thenReturn(collection);

		try (MockedStatic<MongoClients> mockedMongoClients = Mockito.mockStatic(MongoClients.class)) {

			mockedMongoClients.when(() -> MongoClients.create("mongodb://localhost:27017")).thenReturn(mongoClient);

			MongoPerfumeRepository repository = new MongoPerfumeRepository("mongodb://localhost:27017");

			repository.delete("p001");

			verify(collection).deleteOne(any(org.bson.conversions.Bson.class));
		}
	}

	@Test
	public void testUpdate() {

		MongoClient mongoClient = mock(MongoClient.class);
		MongoDatabase database = mock(MongoDatabase.class);
		MongoCollection<Document> collection = mock(MongoCollection.class);

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);
		when(database.getCollection("perfumes")).thenReturn(collection);

		try (MockedStatic<MongoClients> mockedMongoClients = Mockito.mockStatic(MongoClients.class)) {

			mockedMongoClients.when(() -> MongoClients.create("mongodb://localhost:27017")).thenReturn(mongoClient);

			MongoPerfumeRepository repository = new MongoPerfumeRepository("mongodb://localhost:27017");

			Perfume perfume = new Perfume("p001", "Sauvage Elixir", "Dior", "Spicy", 60, 4.8);

			repository.update(perfume);

			verify(collection).updateOne(any(org.bson.conversions.Bson.class), any(Document.class));
		}

	}

	@Test
	public void testFindAllReturnsAllPerfumes() {

		MongoClient mongoClient = mock(MongoClient.class);
		MongoDatabase database = mock(MongoDatabase.class);
		MongoCollection<Document> collection = mock(MongoCollection.class);

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);
		when(database.getCollection("perfumes")).thenReturn(collection);

		Document document = new Document("_id", "p001").append("name", "Sauvage").append("brand", "Dior")
				.append("fragranceFamily", "Woody").append("volume", 100).append("rating", 4.5);

		FindIterable<Document> findIterable = mock(FindIterable.class);
		MongoCursor<Document> cursor = mock(MongoCursor.class);

		when(collection.find()).thenReturn(findIterable);
		when(findIterable.iterator()).thenReturn(cursor);

		when(cursor.hasNext()).thenReturn(true, false);
		when(cursor.next()).thenReturn(document);

		try (MockedStatic<MongoClients> mockedMongoClients = Mockito.mockStatic(MongoClients.class)) {

			mockedMongoClients.when(() -> MongoClients.create("mongodb://localhost:27017")).thenReturn(mongoClient);

			MongoPerfumeRepository repository = new MongoPerfumeRepository("mongodb://localhost:27017");

			List<Perfume> perfumes = repository.findAll();

			assertEquals(1, perfumes.size());
			assertEquals("p001", perfumes.get(0).getId());
			assertEquals("Sauvage", perfumes.get(0).getName());
			assertEquals("Dior", perfumes.get(0).getBrand());
		}
	}
}