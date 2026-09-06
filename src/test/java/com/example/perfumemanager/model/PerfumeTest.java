package com.example.perfumemanager.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

	@Test
	public void testPerfumesAreEqualWhenAllFieldsMatch() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertEquals(first, second);
	}

	@Test
	public void testPerfumeIsEqualToItself() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertEquals(perfume, perfume);
	}

	@Test
	public void testPerfumeIsNotEqualToNull() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertNotEquals(perfume, null);
	}

	@Test
	public void testPerfumesAreNotEqualWhenFieldsDiffer() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p002", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertNotEquals(first, second);
	}

	@Test
	public void testEqualPerfumesHaveSameHashCode() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);

		assertEquals(first.hashCode(), second.hashCode());
	}

	@Test
	public void testPerfumesAreNotEqualWhenVolumeDiffers() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Sauvage", "Dior", "Woody", 50, 4.5);

		assertNotEquals(first, second);
	}

	@Test
	public void testPerfumesAreNotEqualWhenRatingDiffers() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.0);

		assertNotEquals(first, second);
	}

	@Test
	public void testPerfumesAreNotEqualWhenNameDiffers() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Bleu", "Dior", "Woody", 100, 4.5);

		assertNotEquals(first, second);
	}

	@Test
	public void testPerfumesAreNotEqualWhenBrandDiffers() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Sauvage", "Chanel", "Woody", 100, 4.5);

		assertNotEquals(first, second);
	}

	@Test
	public void testPerfumesAreNotEqualWhenFragranceFamilyDiffers() {
		Perfume first = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Perfume second = new Perfume("p001", "Sauvage", "Dior", "Fresh", 100, 4.5);

		assertNotEquals(first, second);
	}

	@Test
	public void testPerfumeIsNotEqualToObjectOfDifferentClass() {
		Perfume perfume = new Perfume("p001", "Sauvage", "Dior", "Woody", 100, 4.5);
		Object other = new Object();

		assertNotEquals(perfume, other);
	}

}
