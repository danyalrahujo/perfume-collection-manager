package com.example.perfumemanager.controller;

import java.util.List;

import com.example.perfumemanager.model.Perfume;
import com.example.perfumemanager.repository.PerfumeRepository;
import com.example.perfumemanager.view.PerfumeView;

public class PerfumeManager {
	private PerfumeRepository repository;
	private PerfumeView view;

	public PerfumeManager(PerfumeRepository repository) {
		this.repository = repository;
		this.view = null;
	}

	public PerfumeManager(PerfumeRepository repository, PerfumeView view) {
		this.repository = repository;
		this.view = view;
	}

	public List<Perfume> listPerfumes() {

		List<Perfume> perfumes = repository.findAll();

		if (view != null) {
			view.showAllPerfumes(perfumes);
		}

		return perfumes;
	}

	public void addPerfume(Perfume perfume) {
		repository.save(perfume);

		if (view != null) {
			view.perfumeAdded(perfume);
		}
	}

	public void deletePerfume(Perfume perfume) {
		repository.delete(perfume.getId());

		if (view != null) {
			view.perfumeRemoved(perfume);
		}
	}
}