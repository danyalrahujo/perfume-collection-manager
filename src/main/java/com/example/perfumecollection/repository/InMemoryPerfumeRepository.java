package com.example.perfumecollection.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.example.perfumecollection.model.Perfume;

public class InMemoryPerfumeRepository {

	private final Map<String, Perfume> perfumes = new HashMap<>();

	public void create(Perfume perfume) {
		perfumes.put(perfume.getId(), perfume);
	}

	public Perfume findById(String id) {
		return perfumes.get(id);
	}

	public List<Perfume> findAll() {
		return new ArrayList<>(perfumes.values());
	}

	public void update(Perfume perfume) {
		perfumes.put(perfume.getId(), perfume);
	}

}
