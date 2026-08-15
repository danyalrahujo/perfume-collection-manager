package com.example.perfumecollection.model;

public class Perfume {

	private String id;

	private String name;
	private String brand;
	private String fragranceFamily;
	private int volume;
	private double rating;

	public Perfume(String id, String name, String brand, String fragranceFamily, int volume, double rating) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.fragranceFamily = fragranceFamily;
		this.volume = volume;
		this.rating = rating;
	}

	public String getId() {
		return id;
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

}
