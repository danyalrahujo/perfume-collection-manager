package com.example.perfumemanager.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.example.perfumemanager.Perfume;

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
}