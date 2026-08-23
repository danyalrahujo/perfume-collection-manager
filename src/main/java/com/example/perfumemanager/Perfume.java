package com.example.perfumemanager;

public class Perfume {

	private String name;
	private String brand;

	public Perfume() {
	}

	public Perfume(String name) {
		this.name = name;
	}

	public Perfume(String name, String brand) {
		this.name = name;
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}
}