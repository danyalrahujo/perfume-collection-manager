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
}