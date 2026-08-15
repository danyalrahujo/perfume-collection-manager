package com.example.perfumecollection.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.perfumecollection.model.Perfume;

public class InMemoryPerfumeRepositoryTest {

	@Test
	public void testCreateAndFindById() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.create(perfume);

		Perfume result = repository.findById("p001");

		assertEquals(perfume, result);
	}

	@Test
	public void testFindAllReturnsAllPerfumes() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume1 = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume perfume2 = new Perfume("p002", "Chanel No. 5", "Chanel", "Floral", 50, 4.7);

		repository.create(perfume1);
		repository.create(perfume2);

		assertEquals(2, repository.findAll().size());
	}

	@Test
	public void testUpdatePerfume() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.create(perfume);

		Perfume updatedPerfume = new Perfume("p001", "Sauvage Elixir", "Dior", "Woody", 60, 4.8);

		repository.update(updatedPerfume);

		Perfume result = repository.findById("p001");

		assertEquals("Sauvage Elixir", result.getName());
		assertEquals(60, result.getVolume());
		assertEquals(4.8, result.getRating(), 0.01);
	}
}