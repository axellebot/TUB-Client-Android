package fr.bourgmapper.tub.domain;

/**
 * Class that represents a user in the domain layer.
 */
public class Line {

    private final String lineId;
    private String number;
    private String label;
    private String color;
    private String kmlPath;

    public Line(String lineId) {
        this.lineId = lineId;
    }

    public String getLineId() {
        return lineId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKmlPath() {
        return kmlPath;
    }

    public void setKmlPath(String kmlPath) {
        this.kmlPath = kmlPath;
    }
}
