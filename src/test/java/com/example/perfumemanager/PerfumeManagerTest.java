package com.example.perfumemanager;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class PerfumeManagerTest {

	@Test
	public void testListPerfumesWhenNoPerfumesArePresent() {
		PerfumeManager perfumeManager = new PerfumeManager();

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.isEmpty());
	}

	@Test
	public void testAddPerfumeMakesItAppearInList() {
		PerfumeManager perfumeManager = new PerfumeManager();
		Perfume perfume = new Perfume();

		perfumeManager.addPerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.contains(perfume));
	}

	@Test
	public void testDeletePerfumeRemovesItFromList() {
		PerfumeManager perfumeManager = new PerfumeManager();
		Perfume perfume = new Perfume();

		perfumeManager.addPerfume(perfume);
		perfumeManager.deletePerfume(perfume);

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertFalse(perfumes.contains(perfume));
	}

	@Test
	public void testPerfumeHasName() {
		Perfume perfume = new Perfume("Sauvage");

		assertEquals("Sauvage", perfume.getName());
	}

	@Test
	public void testPerfumeHasBrand() {
		Perfume perfume = new Perfume("Sauvage", "Dior");

		assertEquals("Dior", perfume.getBrand());
	}

	@Test
	public void testPerfumeHasFragranceFamily() {
		Perfume perfume = new Perfume("Sauvage", "Dior", "Woody");

		assertEquals("Woody", perfume.getFragranceFamily());
	}

	@Test
	public void testPerfumeHasVolume() {
		Perfume perfume = new Perfume("Sauvage", "Dior", "Woody", 100);

		assertEquals(100, perfume.getVolume());
	}
}