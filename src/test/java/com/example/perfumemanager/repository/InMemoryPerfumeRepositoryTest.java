package com.example.perfumemanager.repository;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNull;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.example.perfumemanager.model.Perfume;

public class InMemoryPerfumeRepositoryTest {

	@Test
	public void testFindAllWhenNoPerfumesArePresent() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		List<Perfume> perfumes = repository.findAll();

		assertTrue(perfumes.isEmpty());
	}

	@Test
	public void testSavePerfumeMakesItAppearInFindAll() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.save(perfume);

		List<Perfume> perfumes = repository.findAll();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testFindByIdReturnsSavedPerfume() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.save(perfume);

		Perfume result = repository.findById("p001");

		assertEquals(perfume, result);
	}

	@Test
	public void testFindByIdReturnsNullWhenPerfumeDoesNotExist() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume result = repository.findById("unknown");

		assertNull(result);
	}

	@Test
	public void testDeleteRemovesPerfume() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		repository.save(perfume);
		repository.delete("p001");

		assertFalse(repository.findAll().contains(perfume));
	}

	@Test
	public void testFindAllReturnsAllSavedPerfumes() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		Perfume second = new Perfume("p002", "Aventus", "Creed", "Fruity", 100, 4.7);

		repository.save(first);
		repository.save(second);

		List<Perfume> perfumes = repository.findAll();

		assertEquals(2, perfumes.size());
		assertTrue(perfumes.contains(first));
		assertTrue(perfumes.contains(second));
	}

	@Test
	public void testUpdateReplacesExistingPerfume() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume original = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		Perfume updated = new Perfume("p001", "Sauvage Elixir", "Dior", "Woody", 60, 4.8);

		repository.save(original);
		repository.update(updated);

		assertEquals(updated, repository.findById("p001"));
	}

	@Test
	public void testUpdateDoesNothingWhenPerfumeDoesNotExist() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume existing = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		Perfume updated = new Perfume("p002", "Aventus", "Creed", "Fruity", 100, 4.7);

		repository.save(existing);
		repository.update(updated);

		assertEquals(1, repository.findAll().size());
		assertEquals(existing, repository.findById("p001"));
		assertNull(repository.findById("p002"));
	}

	@Test
	public void testDeleteRemovesOnlyMatchingPerfume() {
		InMemoryPerfumeRepository repository = new InMemoryPerfumeRepository();

		Perfume perfume1 = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume perfume2 = new Perfume("p002", "Bleu de Chanel", "Chanel", "Fresh", 100, 4.6);

		repository.save(perfume1);
		repository.save(perfume2);

		repository.delete("p001");

		assertNull(repository.findById("p001"));
		assertEquals(perfume2, repository.findById("p002"));
	}
}