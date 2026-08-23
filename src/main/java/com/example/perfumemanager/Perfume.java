package com.example.perfumemanager;

public class Perfume {

	private String name;
	private String brand;
	private String fragranceFamily;

	public Perfume() {
	}

	public Perfume(String name) {
		this.name = name;
	}

	public Perfume(String name, String brand) {
		this.name = name;
		this.brand = brand;
	}

	public Perfume(String name, String brand, String fragranceFamily) {
		this.name = name;
		this.brand = brand;
		this.fragranceFamily = fragranceFamily;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getFragranceFamily() {
		return fragranceFamily;
	}
}