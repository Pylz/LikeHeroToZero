package com.pylz.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emissions_data")
public class EmissionData implements Serializable {

	private static final long serialVersionUID = 1L; // Serializable Version UID

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "COUNTRY")
	private String countryName; // Landname

	@Column(name = "EMISSION_YEAR")
	private Integer date; // Emissionsjahr

	@Column(name = "CO2EMISSIONS")
	private double amountValue; // CO2-Emissionen

	// Standard-Konstruktor ohne Argumente
	public EmissionData() {
		// Standard-Konstruktor für JPA-Provider
	}

	// Getter-Methoden
	public Long getId() {
		return id;
	}

	public String getCountry() {
		return countryName;
	}

	public Integer getYear() {
		return date;
	}

	public double getCo2Emissions() {
		return amountValue;
	}

	// Setter-Methoden
	public void setId(Long id) {
		this.id = id;
	}

	public void setCountry(String countryName) {
		this.countryName = countryName;
	}

	public void setYear(Integer date) {
		this.date = date;
	}

	public void setCo2Emissions(double amountValue) {
		this.amountValue = amountValue;
	}

	// toString-Methode für Debugging-Zwecke
	@Override
	public String toString() {
		return "EmissionData{" + "id=" + id + ", countryName='" + countryName + '\'' + ", date=" + date
				+ ", amountValue=" + amountValue + ", countryCode='" + '\'' + '}';
	}
}
