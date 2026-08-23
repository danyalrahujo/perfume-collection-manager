package com.example.perfumemanager.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.perfumemanager.Perfume;

public class InMemoryPerfumeRepository {

	private List<Perfume> perfumes = new ArrayList<>();

	public List<Perfume> findAll() {
		return perfumes;
	}

	public void save(Perfume perfume) {
		perfumes.add(perfume);
	}

	public Perfume findById(String id) {
		for (Perfume perfume : perfumes) {
			if (perfume.getId().equals(id)) {
				return perfume;
			}
		}
		return null;
	}

	public void delete(String id) {
		perfumes.removeIf(perfume -> perfume.getId().equals(id));
	}
}