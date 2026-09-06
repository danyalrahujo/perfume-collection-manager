package com.example.perfumemanager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.perfumemanager.model.Perfume;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@RunWith(MockitoJUnitRunner.class)
public class MongoPerfumeRepositoryTest {

	@Mock
	private MongoClient mongoClient;

	@Mock
	private MongoDatabase database;

	@Mock
	private MongoCollection<Document> collection;

	@Mock
	private FindIterable<Document> findIterable;

	@Mock
	private MongoCursor<Document> cursor;

	private MongoPerfumeRepository createRepository() {

		when(mongoClient.getDatabase("perfume_manager")).thenReturn(database);

		when(database.getCollection("perfumes")).thenReturn(collection);

		return new MongoPerfumeRepository(mongoClient, "perfume_manager", "perfumes");
	}

	@Test
	public void testSave() {

		MongoPerfumeRepository repository = createRepository();

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.save(perfume);

		verify(collection).insertOne(any(Document.class));
	}

	@Test
	public void testFindByIdReturnsPerfume() {

		MongoPerfumeRepository repository = createRepository();

		Document document = new Document("_id", "p001").append("name", "Sauvage").append("brand", "Dior")
				.append("fragranceFamily", "Woody").append("volume", 100).append("rating", 4.5);

		when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(findIterable);

		when(findIterable.first()).thenReturn(document);

		Perfume result = repository.findById("p001");

		assertEquals("p001", result.getId());
		assertEquals("Sauvage", result.getName());
		assertEquals("Dior", result.getBrand());
		assertEquals("Woody", result.getFragranceFamily());
		assertEquals(100, result.getVolume());
		assertEquals(4.5, result.getRating(), 0.001);
	}

	@Test
	public void testFindByIdReturnsNullWhenNotFound() {

		MongoPerfumeRepository repository = createRepository();

		when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(findIterable);

		when(findIterable.first()).thenReturn(null);

		assertNull(repository.findById("does-not-exist"));
	}

	@Test
	public void testDelete() {

		MongoPerfumeRepository repository = createRepository();

		repository.delete("p001");

		verify(collection).deleteOne(any(org.bson.conversions.Bson.class));
	}

	@Test
	public void testUpdate() {

		MongoPerfumeRepository repository = createRepository();

		Perfume perfume = new Perfume("p001", "Sauvage Elixir", "Dior", "Spicy", 60, 4.8);

		repository.update(perfume);

		verify(collection).updateOne(any(org.bson.conversions.Bson.class), any(Document.class));
	}

	@Test
	public void testFindAllReturnsAllPerfumes() {

		MongoPerfumeRepository repository = createRepository();

		Document document = new Document("_id", "p001").append("name", "Sauvage").append("brand", "Dior")
				.append("fragranceFamily", "Woody").append("volume", 100).append("rating", 4.5);

		when(collection.find()).thenReturn(findIterable);
		when(findIterable.iterator()).thenReturn(cursor);

		when(cursor.hasNext()).thenReturn(true, false);
		when(cursor.next()).thenReturn(document);

		List<Perfume> perfumes = repository.findAll();

		assertEquals(1, perfumes.size());
		assertEquals("p001", perfumes.get(0).getId());
		assertEquals("Sauvage", perfumes.get(0).getName());
		assertEquals("Dior", perfumes.get(0).getBrand());
	}
}