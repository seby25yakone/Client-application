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
    public Printer(@JsonProperty("type") String type, @JsonProperty("resolution") String resolution,@JsonProperty("color") String color, @JsonProperty("colorSpeed") double colorSpeed,@JsonProperty("monoSpeed") double monoSpeed,@JsonProperty("format") String format, @JsonProperty("connectionType") String connectionType) {
        this.printerType = type;
        this.resolution = resolution;
        this.color = color;
        this.colorSpeed = colorSpeed;
        this.monoSpeed = monoSpeed;
        this.format = format;
        this.connectionType = connectionType;
    }
}
