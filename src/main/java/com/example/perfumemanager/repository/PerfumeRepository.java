package com.example.perfumemanager.repository;

import com.example.perfumemanager.Perfume;
import java.util.List;

public interface PerfumeRepository {

	void save(Perfume perfume);

	List<Perfume> findAll();

	Perfume findById(String id);

	void delete(String id);
}