package com.pylz.util;

import com.pylz.entities.EmissionData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

	private static final Logger logger = LogManager.getLogger(CSVUtils.class);

	private CSVUtils() {
		// Privater Konstruktor, um die Instanziierung zu verhindern
	}

	public static List<EmissionData> parseCSV(InputStream inputStream) {
		List<EmissionData> emissions = new ArrayList<>();
		try (CSVParser parser = new CSVParser(new InputStreamReader(inputStream),
				CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {
			for (CSVRecord csvRecord : parser) {
				EmissionData data = new EmissionData();

				// Daten aus der CSV-Zeile extrahieren und in ein EmissionData-Objekt setzen
				data.setCountry(csvRecord.get("country_name")); // Land
				data.setYear(Integer.parseInt(csvRecord.get("date"))); // Jahr

				// Umgang mit leerem amount_value (Menge Wert)
				String amountValueStr = csvRecord.get("amount_value");
				double amountValue = amountValueStr != null && !amountValueStr.isEmpty()
						? Double.parseDouble(amountValueStr)
						: 0.0;

				// Überprüfen, ob der Wert der CO2-Emissionen nicht null ist
				if (amountValue != 0.0) {
					data.setCo2Emissions(amountValue); // CO2-Emissionen
					emissions.add(data);
				}
			}
		} catch (IOException e) {
			logger.error("Fehler beim Parsen der CSV-Datei", e); // Mit Log4j die Ausnahme protokollieren
		}

		// Die Liste der geparsten Emissionsdaten zurückgeben
		return emissions;
	}
}
