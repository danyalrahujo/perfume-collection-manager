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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Perfume other = (Perfume) obj;

		return volume == other.volume && Double.compare(rating, other.rating) == 0
				&& java.util.Objects.equals(id, other.id) && java.util.Objects.equals(name, other.name)
				&& java.util.Objects.equals(brand, other.brand)
				&& java.util.Objects.equals(fragranceFamily, other.fragranceFamily);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id, name, brand, fragranceFamily, volume, rating);
	}
}