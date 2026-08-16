package com.example.perfumecollection.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.perfumecollection.model.Perfume;

public class InMemoryPerfumeRepository implements PerfumeRepository {
	private final Map<String, Perfume> perfumes = new HashMap<>();

	@Override
	public void create(Perfume perfume) {
		perfumes.put(perfume.getId(), perfume);
	}

	@Override
	public Perfume findById(String id) {
		return perfumes.get(id);
	}

	@Override
	public List<Perfume> findAll() {
		return new ArrayList<>(perfumes.values());
	}

	@Override
	public void update(Perfume perfume) {
		perfumes.put(perfume.getId(), perfume);
	}

	@Override
	public void delete(String id) {
		perfumes.remove(id);
	}

}
