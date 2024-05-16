package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Printer {
    private String type;
    private String resolution;
    private int colorSpeed;
    private int monoSpeed;
    private String color;
    private String format;
    private String connectionType;
    @JsonCreator
    public Printer(@JsonProperty("type") String type, @JsonProperty("resolution") String resolution, @JsonProperty("colorSpeed") int colorSpeed,@JsonProperty("monoSpeed") int monoSpeed, @JsonProperty("color") String color,@JsonProperty("format") String format, @JsonProperty("connectionType") String connectionType) {
        this.type = type;
        this.resolution = resolution;
        this.colorSpeed = colorSpeed;
        this.monoSpeed = monoSpeed;
        this.color = color;
        this.format = format;
        this.connectionType = connectionType;
    }
}
