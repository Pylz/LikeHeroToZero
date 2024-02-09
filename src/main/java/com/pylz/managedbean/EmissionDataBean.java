package com.pylz.managedbean;

import com.pylz.entities.EmissionData;
import com.pylz.service.EmissionDataService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@ViewScoped
public class EmissionDataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final EmissionDataService emissionDataService;

	private LineChartModel emissionsChartModel;
	private String selectedCountry = "Germany"; // Standardmäßig ausgewähltes Land (weil Staatsangehörigkeit)
	private List<EmissionData> allEmissionData;
	private List<EmissionData> filteredEmissionData;
	private double changePercent1990To2020;
	private EmissionData minEmissionData;
	private EmissionData maxEmissionData;
	private double changePercentLast5Years;
	private List<String> countries; // Liste der verfügbaren Länder

    // No-arg Konstruktor
    public EmissionDataBean() {
        this.emissionDataService = null; // Managed durch CDI
    }
	
	// Konstruktorinjektion
	@Inject
	public EmissionDataBean(EmissionDataService emissionDataService) {
		this.emissionDataService = emissionDataService;
	}

	@PostConstruct
	public void init() {
		allEmissionData = emissionDataService.findAllEmissions();
		countries = emissionDataService.findDistinctCountries();
		updateSelectedCountryData();
	}

	public void onCountryChange() {
		updateSelectedCountryData();
	}

	private void updateSelectedCountryData() {
		// Aktualisiert die angezeigten Daten basierend auf dem ausgewählten Land.
		filteredEmissionData = allEmissionData.stream().filter(data -> data.getCountry().equals(selectedCountry))
				.collect(Collectors.toList());

		createEmissionsChartModel(filteredEmissionData);
		calculateStatistics(filteredEmissionData);
	}

	private void createEmissionsChartModel(List<EmissionData> filteredData) {
		// Erstellt ein Diagrammmodell für die CO2-Emissionen.
		emissionsChartModel = new LineChartModel();
		LineChartSeries series = new LineChartSeries();
		series.setLabel("CO2-Emissionen");

		filteredData.forEach(data -> series.set(data.getYear(), data.getCo2Emissions()));

		emissionsChartModel.addSeries(series);
		emissionsChartModel.setTitle("CO2-Emissionen im Laufe der Zeit");
		emissionsChartModel.setZoom(true);

		Axis yAxis = emissionsChartModel.getAxis(AxisType.Y);
		yAxis.setLabel("Emissionen");

		Axis xAxis = emissionsChartModel.getAxis(AxisType.X);
		xAxis.setLabel("Jahr");
	}

	private void calculateStatistics(List<EmissionData> filteredData) {
		// Berechnet Statistiken wie Änderungsprozentsatz und Min/Max-Emissionen.
		// Änderungsprozentsatz von 1990 bis 2020
		double total1990 = filteredData.stream().filter(d -> d.getYear() == 1990)
				.mapToDouble(EmissionData::getCo2Emissions).sum();
		double total2020 = filteredData.stream().filter(d -> d.getYear() == 2020)
				.mapToDouble(EmissionData::getCo2Emissions).sum();
		if (total1990 > 0) {
			changePercent1990To2020 = ((total2020 - total1990) / total1990) * 100;
			changePercent1990To2020 = Math.round(changePercent1990To2020 * 100.0) / 100.0; // Rundet auf 2 Dezimalstellen
		}

		// Min- und Max-Emissionen
		minEmissionData = filteredData.stream().min(Comparator.comparingDouble(EmissionData::getCo2Emissions))
				.orElse(null);
		maxEmissionData = filteredData.stream().max(Comparator.comparingDouble(EmissionData::getCo2Emissions))
				.orElse(null);

		// Änderungsprozentsatz in den letzten 5 Jahren
		int latestYear = filteredData.stream().max(Comparator.comparingInt(EmissionData::getYear))
				.map(EmissionData::getYear).orElse(0);
		List<EmissionData> last5YearsData = filteredData.stream().filter(d -> d.getYear() >= (latestYear - 4))
				.sorted(Comparator.comparingInt(EmissionData::getYear)).collect(Collectors.toList());
		if (last5YearsData.size() > 1) {
			double emissions5YearsAgo = last5YearsData.get(0).getCo2Emissions();
			double latestEmissions = last5YearsData.get(last5YearsData.size() - 1).getCo2Emissions();
			changePercentLast5Years = ((latestEmissions - emissions5YearsAgo) / emissions5YearsAgo) * 100;
			changePercentLast5Years = Math.round(changePercentLast5Years * 100.0) / 100.0; // Rundet auf 2 Dezimalstellen
		}
	}

	// Getter und Setter

	// Getter für filteredEmissionData
	public List<EmissionData> getFilteredEmissionData() {
		return filteredEmissionData;
	}

	public LineChartModel getEmissionsChartModel() {
		return emissionsChartModel;
	}

	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
		onCountryChange();
	}

	public double getChangePercent1990To2020() {
		return changePercent1990To2020;
	}

	public EmissionData getMinEmissionData() {
		return minEmissionData;
	}

	public EmissionData getMaxEmissionData() {
		return maxEmissionData;
	}

	public double getChangePercentLast5Years() {
		return changePercentLast5Years;
	}

	public List<String> getCountries() {
		return countries;
	}
}
