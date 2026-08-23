package com.example.perfumemanager;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PerfumeManagerTest {

	@Test
	public void testListPerfumesWhenNoPerfumesArePresent() {
		PerfumeManager perfumeManager = new PerfumeManager();

		List<Perfume> perfumes = perfumeManager.listPerfumes();

		assertTrue(perfumes.isEmpty());
	}
}