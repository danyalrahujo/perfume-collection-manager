package com.example.perfumecollection.repository;

import java.util.List;

import com.example.perfumecollection.model.Perfume;

public interface PerfumeRepository {

	void create(Perfume perfume);

	Perfume findById(String id);

	List<Perfume> findAll();

	void update(Perfume perfume);

	void delete(String id);
}