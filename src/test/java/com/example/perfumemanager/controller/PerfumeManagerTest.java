package com.example.perfumemanager.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.example.perfumemanager.model.Perfume;
import com.example.perfumemanager.repository.InMemoryPerfumeRepository;
import com.example.perfumemanager.repository.PerfumeRepository;

public class PerfumeManagerTest {

	@Test
	public void testListPerfumesWhenNoPerfumesArePresent() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeManager perfumeManager = new PerfumeManager(repository);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.isEmpty());
	}

	@Test
	public void testAddPerfumeMakesItAppearInList() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeManager perfumeManager = new PerfumeManager(repository);

		Perfume perfume = new Perfume();

		perfumeManager.addPerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testDeletePerfumeRemovesItFromList() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeManager perfumeManager = new PerfumeManager(repository);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);
		perfumeManager.deletePerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertFalse(perfumes.contains(perfume));
	}

	@Test
	public void testListPerfumesUsesRepository() {
		PerfumeRepository repository = mock(PerfumeRepository.class);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		when(repository.findAll()).thenReturn(List.of(perfume));

		PerfumeManager perfumeManager = new PerfumeManager(repository);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testAddPerfumeSavesItInRepository() {
		PerfumeRepository repository = mock(PerfumeRepository.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);

		verify(repository).save(perfume);
	}

}