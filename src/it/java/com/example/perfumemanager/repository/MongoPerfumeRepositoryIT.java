package com.example.perfumemanager.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.testcontainers.mongodb.MongoDBContainer;

import com.example.perfumemanager.Perfume;

public class MongoPerfumeRepositoryIT {

	private static final MongoDBContainer mongoDB = new MongoDBContainer("mongo:7.0");

	@BeforeClass
	public static void setUp() {
		mongoDB.start();
	}

	@AfterClass
	public static void tearDown() {
		mongoDB.stop();
	}

	@Test
	public void testSaveAndFindAll() {

		MongoPerfumeRepository repository = new MongoPerfumeRepository(mongoDB.getConnectionString());

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.save(perfume);

		List<Perfume> perfumes = repository.findAll();

		assertEquals(1, perfumes.size());
		assertEquals("Sauvage", perfumes.get(0).getName());
	}
}
