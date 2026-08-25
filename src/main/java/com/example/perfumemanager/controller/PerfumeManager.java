package com.example.perfumemanager.controller;

import java.util.List;

import com.example.perfumemanager.model.Perfume;
import com.example.perfumemanager.repository.PerfumeRepository;

public class PerfumeManager {

	private PerfumeRepository repository;

	public PerfumeManager(PerfumeRepository repository) {
		this.repository = repository;
	}

	public List<Perfume> listPerfumes() {
		return repository.findAll();
	}

	public void addPerfume(Perfume perfume) {
		repository.save(perfume);
	}

	public void deletePerfume(Perfume perfume) {
		repository.delete(perfume.getId());
	}

}