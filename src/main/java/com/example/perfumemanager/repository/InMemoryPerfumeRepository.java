package com.example.perfumemanager.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.perfumemanager.Perfume;

public class InMemoryPerfumeRepository {

	public List<Perfume> findAll() {
		return new ArrayList<>();
	}
}