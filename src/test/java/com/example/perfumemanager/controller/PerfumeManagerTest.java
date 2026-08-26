package com.example.perfumemanager.controller;

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
import com.example.perfumemanager.view.PerfumeView;

public class PerfumeManagerTest {

	@Test
	public void testListPerfumesWhenNoPerfumesArePresent() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeView view = mock(PerfumeView.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.isEmpty());
	}

	@Test
	public void testAddPerfumeMakesItAppearInList() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeView view = mock(PerfumeView.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		Perfume perfume = new Perfume();

		perfumeManager.addPerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testDeletePerfumeRemovesItFromList() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeView view = mock(PerfumeView.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);
		perfumeManager.deletePerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertFalse(perfumes.contains(perfume));
	}

	@Test
	public void testListPerfumesUsesRepository() {
		PerfumeRepository repository = mock(PerfumeRepository.class);
		PerfumeView view = mock(PerfumeView.class);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		when(repository.findAll()).thenReturn(List.of(perfume));

		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testAddPerfumeSavesItInRepository() {
		PerfumeRepository repository = mock(PerfumeRepository.class);
		PerfumeView view = mock(PerfumeView.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);

		verify(repository).save(perfume);
	}

	@Test
	public void testListPerfumesShowsThemInView() {
		PerfumeRepository repository = mock(PerfumeRepository.class);
		PerfumeView view = mock(PerfumeView.class);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		when(repository.findAll()).thenReturn(List.of(perfume));

		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		perfumeManager.listPerfumes();

		verify(view).showAllPerfumes(List.of(perfume));
	}

	@Test
	public void testAddPerfumeNotifiesView() {
		PerfumeRepository repository = mock(PerfumeRepository.class);
		PerfumeView view = mock(PerfumeView.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository, view);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);

		verify(view).perfumeAdded(perfume);
	}

	@Test
	public void testDeletePerfumeNotifiesView() {
		PerfumeRepository repository = mock(PerfumeRepository.class);
		PerfumeView perfumeView = mock(PerfumeView.class);
		PerfumeManager perfumeManager = new PerfumeManager(repository, perfumeView);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.deletePerfume(perfume);

		verify(perfumeView).perfumeRemoved(perfume);
	}

}