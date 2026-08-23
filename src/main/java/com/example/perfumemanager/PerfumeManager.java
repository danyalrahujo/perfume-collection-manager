package com.example.perfumemanager;

import java.util.ArrayList;
import java.util.List;

public class PerfumeManager {

	private List<Perfume> perfumes = new ArrayList<>();

	public List<Perfume> listPerfumes() {
		return perfumes;
	}

	public void addPerfume(Perfume perfume) {
		perfumes.add(perfume);
	}
}