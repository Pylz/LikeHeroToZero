# Like Hero To Zero - CO2 Emissionsdaten-Visualisierung

Like Hero To Zero ist eine Webanwendung, die entwickelt wurde, um Transparenz über die CO2-Emissionen Ländern zu schaffen. Diese Plattform ermöglicht es Nutzern, Emissionsdaten zu durchsuchen, zu sortieren und zu filtern, was dazu beiträgt, das Bewusstsein für den Klimawandel zu erhöhen und Maßnahmen zur Emissionsreduzierung anzuregen.

## Funktionen

- **Datenvisualisierung**: Stellt CO2-Emissionsdaten in einer interaktiven Tabelle dar.
- **Filteroptionen**: Nutzer können nach Ländern filtern.
- **Responsives Design**: Optimiert für verschiedene Geräte, um eine breite Zugänglichkeit zu gewährleisten.

## Technologie-Stack

- **Jakarta EE 8**: Als das Rückgrat unserer Anwendung, verwenden wir Jakarta EE 8 API für die Unterstützung von Webanwendungen in einem Enterprise-Umfeld.

- **PrimeFaces 12.0.0**: Für das Frontend nutzen wir PrimeFaces, ein leistungsfähiges UI-Framework, das auf JSF aufbaut und reichhaltige Komponenten für eine interaktive Benutzererfahrung bietet.

- **Apache Derby 10.14.2.0**: Als unsere Datenbanklösung setzen wir Apache Derby ein, eine leichtgewichtige, eingebettete Datenbank, die eine einfache Integration und Wartung ermöglicht.

- **EclipseLink 3.0.4**: Für die Objekt-Relationale Abbildung (ORM) und die Verwaltung der Datenbankinteraktionen verwenden wir EclipseLink, den Referenz-Implementierer der JPA-Spezifikation.

- **Apache Commons CSV 1.10.0**: Zur Verarbeitung von CSV-Dateien und dem Import von Emissionsdaten setzen wir auf Apache Commons CSV, das eine einfache und flexible API für CSV-Operationen bietet.

- **Log4j 2.22.1**: Für umfassendes Logging und Fehlerüberwachung innerhalb unserer Anwendung integrieren wir Log4j, ein zuverlässiges und anpassbares Logging-Framework.

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
