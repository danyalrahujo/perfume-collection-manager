package com.example.perfumemanager.model;

public class Perfume {

	private String name;
	private String brand;
	private String fragranceFamily;
	private int volume;
	private double rating;
	private String id;

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

	public Perfume(String name, String brand, String fragranceFamily, int volume) {
		this.name = name;
		this.brand = brand;
		this.fragranceFamily = fragranceFamily;
		this.volume = volume;
	}

	public Perfume(String name, String brand, String fragranceFamily, int volume, double rating) {
		this.name = name;
		this.brand = brand;
		this.fragranceFamily = fragranceFamily;
		this.volume = volume;
		this.rating = rating;
	}

	public Perfume(String id, String name, String brand, String fragranceFamily, int volume, double rating) {

		this.id = id;
		this.name = name;
		this.brand = brand;
		this.fragranceFamily = fragranceFamily;
		this.volume = volume;
		this.rating = rating;
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

	public int getVolume() {
		return volume;
	}

	public double getRating() {
		return rating;
	}

	public String getId() {
		return id;
	}
}