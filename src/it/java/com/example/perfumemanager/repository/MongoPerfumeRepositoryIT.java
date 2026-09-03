package com.example.perfumemanager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.bson.Document;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.perfumemanager.model.Perfume;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.testcontainers.mongodb.MongoDBContainer;

public class MongoPerfumeRepositoryIT {

	private static final MongoDBContainer mongoDB = new MongoDBContainer("mongo:7.0");

	private MongoClient mongoClient;

	@BeforeClass
	public static void setUp() {
		mongoDB.start();
	}

	@AfterClass
	public static void tearDown() {
		mongoDB.stop();
	}

	@Before
	public void cleanDatabase() {

		mongoClient = MongoClients.create(mongoDB.getConnectionString());

		mongoClient.getDatabase("perfume_manager").getCollection("perfumes").deleteMany(new Document());
	}

	@Test
	public void testSaveAndFindAll() {

		MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoClient, "perfume_manager", "perfumes");

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.save(perfume);

		List<Perfume> perfumes = repository.findAll();

		assertEquals(1, perfumes.size());
		assertEquals("Sauvage", perfumes.get(0).getName());
	}

	@Test
	public void testFindByIdReturnsSavedPerfume() {

		MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoClient, "perfume_manager", "perfumes");

		Perfume perfume = new Perfume("p002", "Aventus", "Creed", "Fruity", 100, 4.7);

		repository.save(perfume);

		Perfume result = repository.findById("p002");

		assertEquals(perfume.getId(), result.getId());
		assertEquals(perfume.getName(), result.getName());
		assertEquals(perfume.getBrand(), result.getBrand());
	}

	@Test
	public void testFindByIdReturnsNullWhenPerfumeDoesNotExist() {

		MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoClient, "perfume_manager", "perfumes");

		Perfume result = repository.findById("does-not-exist");

		assertNull(result);
	}

	@Test
	public void testDeleteRemovesPerfume() {

		MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoClient, "perfume_manager", "perfumes");

		Perfume perfume = new Perfume("p003", "Bleu de Chanel", "Chanel", "Woody", 100, 4.6);

		repository.save(perfume);

		repository.delete("p003");

		assertNull(repository.findById("p003"));
	}

	@Test
	public void testUpdateChangesExistingPerfume() {

		MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoClient, "perfume_manager", "perfumes");

		Perfume original = new Perfume("p004", "Sauvage", "Dior", "Woody", 100, 4.5);

		Perfume updated = new Perfume("p004", "Sauvage Elixir", "Dior", "Woody", 60, 4.8);

		repository.save(original);

		repository.update(updated);

		Perfume result = repository.findById("p004");

		assertEquals("Sauvage Elixir", result.getName());
		assertEquals(60, result.getVolume());
		assertEquals(4.8, result.getRating(), 0.001);
	}
}