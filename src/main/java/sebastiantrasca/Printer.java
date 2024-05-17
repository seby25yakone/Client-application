package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Printer {
    private String printerType;
    private String resolution;
    private String color;
    private double colorSpeed;
    private double monoSpeed;

    private String format;
    private String connectionType;
    @JsonCreator
    public Printer(@JsonProperty("printerType") String type, @JsonProperty("resolution") String resolution,@JsonProperty("color") String color, @JsonProperty("colorSpeed") double colorSpeed,@JsonProperty("monoSpeed") double monoSpeed,@JsonProperty("format") String format, @JsonProperty("connectionType") String connectionType) {
        this.printerType = type;
        this.resolution = resolution;
        this.color = color;
        this.colorSpeed = colorSpeed;
        this.monoSpeed = monoSpeed;
        this.format = format;
        this.connectionType = connectionType;
    }

    public String getPrinterType() {
        return printerType;
    }

    public String getResolution() {
        return resolution;
    }

    public String getColor() {
        return color;
    }

    public double getColorSpeed() {
        return colorSpeed;
    }

    public double getMonoSpeed() {
        return monoSpeed;
    }

    public String getFormat() {
        return format;
    }

    public String getConnectionType() {
        return connectionType;
    }

    @Override
    public String toString() {
        return "Printer{" +
                "printerType='" + printerType + '\'' +
                ", resolution='" + resolution + '\'' +
                ", color='" + color + '\'' +
                ", colorSpeed=" + colorSpeed +
                ", monoSpeed=" + monoSpeed +
                ", format='" + format + '\'' +
                ", connectionType='" + connectionType + '\'' +
                '}';
    }
}
