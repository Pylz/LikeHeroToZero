# Like Hero To Zero - CO2 Emissionsdaten-Visualisierung

Like Hero To Zero ist eine Webanwendung, die entwickelt wurde, um Transparenz über die CO2-Emissionen Ländern zu schaffen. Diese Plattform ermöglicht es Nutzern, Emissionsdaten zu durchsuchen, zu sortieren und zu filtern, was dazu beiträgt, das Bewusstsein für den Klimawandel zu erhöhen und Maßnahmen zur Emissionsreduzierung anzuregen.

## Funktionen

- **Datenvisualisierung**: Stellt CO2-Emissionsdaten in einer interaktiven Tabelle dar.
- **Filteroptionen**: Nutzer können nach Ländern filtern.
- **Responsives Design**: Optimiert für verschiedene Geräte, um eine breite Zugänglichkeit zu gewährleisten.

## Technologie-Stack

- **Frontend**: JSF (JavaServer Faces), PrimeFaces
- **Backend**: JPA (Java Persistence API), EJB (Enterprise JavaBeans)
- **Datenbank**: Apache Derby
- **Server**: Apache TomEE Plume

## Schnellstart

1. Klonen Sie das Repository:

    ```bash
    git clone https://github.com/IhrBenutzername/LikeHeroToZeroEclipse.git
    ```

2. Importieren Sie das Projekt in Ihre IDE (z.B. Eclipse).

3. Stellen Sie sicher, dass Apache TomEE 8.9.16-plume als Server konfiguriert ist.

4. Starten Sie die Anwendung über Ihre IDE. Eventuell ist es nötig den Java Agent als Argument zu spezifizieren (-javaagent:...\apache-tomee-plume-8.0.16\lib\openejb-javaagent.jar).

## Konfiguration

Stellen Sie sicher, dass die `DataSource` und `JDBC`-Verbindungen korrekt in Ihrer `persistence.xml`, `resources.xml` und `tomee.xml` konfiguriert sind. Passen Sie die Pfade und Anmeldedaten entsprechend Ihrer lokalen Umgebung an.

## Autor

- Philip Görts - [Pylz] - (https://github.com/Pylz)
