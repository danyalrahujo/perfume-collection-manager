package com.example.perfumemanager.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PerfumeTest {

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

	@Test
	public void testPerfumeHasRating() {
		Perfume perfume = new Perfume("Sauvage", "Dior", "Woody", 100, 4.5);

		assertEquals(4.5, perfume.getRating(), 0.0);
	}

	@Test
	public void testPerfumeHasId() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertEquals("p001", perfume.getId());
	}

}
