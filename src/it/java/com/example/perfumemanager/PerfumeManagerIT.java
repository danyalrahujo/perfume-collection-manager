package com.example.perfumemanager;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.example.perfumemanager.repository.InMemoryPerfumeRepository;
import com.example.perfumemanager.repository.PerfumeRepository;

public class PerfumeManagerIT {

	@Test
	public void testAddPerfumeAndListPerfumesUsingRealRepository() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeManager perfumeManager = new PerfumeManager(repository);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testDeletePerfumeRemovesItUsingRealRepository() {
		PerfumeRepository repository = new InMemoryPerfumeRepository();
		PerfumeManager perfumeManager = new PerfumeManager(repository);

		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		perfumeManager.addPerfume(perfume);
		perfumeManager.deletePerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.isEmpty());
	}
}