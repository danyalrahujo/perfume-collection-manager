package com.example.perfumemanager.controller;

import java.util.List;
import com.example.perfumemanager.view.PerfumeView;

import com.example.perfumemanager.model.Perfume;
import com.example.perfumemanager.repository.PerfumeRepository;

public class PerfumeManager {

	private PerfumeRepository repository;

	private PerfumeView view;

	public PerfumeManager(PerfumeRepository repository, PerfumeView view) {

		this.repository = repository;

		this.view = view;

	}

	public List<Perfume> listPerfumes() {

		List<Perfume> perfumes = repository.findAll();

		view.showAllPerfumes(perfumes);

		return perfumes;

	}

	public void addPerfume(Perfume perfume) {
		repository.save(perfume);
	}

	public void deletePerfume(Perfume perfume) {
		repository.delete(perfume.getId());
	}

}