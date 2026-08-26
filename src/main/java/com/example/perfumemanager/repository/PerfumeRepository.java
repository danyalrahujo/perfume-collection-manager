package com.example.perfumemanager.repository;

import java.util.List;

import com.example.perfumemanager.model.Perfume;

public interface PerfumeRepository {

	void save(Perfume perfume);

	void update(Perfume perfume);

	List<Perfume> findAll();

	Perfume findById(String id);

	void delete(String id);
}