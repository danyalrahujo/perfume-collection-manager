package com.example.perfumemanager.view;

import java.util.List;

import com.example.perfumemanager.model.Perfume;

public interface PerfumeView {

	void showAllPerfumes(List<Perfume> perfumes);

	void showError(String message, Perfume perfume);

	void perfumeAdded(Perfume perfume);

	void perfumeRemoved(Perfume perfume);

	void perfumeUpdated(Perfume perfume);
}