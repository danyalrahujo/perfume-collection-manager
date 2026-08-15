package com.example.perfumecollection.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PerfumeTest {

	@Test
	public void testPerfumeStoresDetails() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertEquals("p001", perfume.getId());
		assertEquals("Sauvage", perfume.getName());
		assertEquals("Dior", perfume.getBrand());
		assertEquals("Woody", perfume.getFragranceFamily());
		assertEquals(100, perfume.getVolume());
		assertEquals(4.5, perfume.getRating(), 0.01);
	}
}