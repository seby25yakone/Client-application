package sebastiantrasca;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Record {
    private long id;
    private Device device;

    @JsonCreator
    public Record(@JsonProperty("id") long id, @JsonProperty("device") Device device) {
        this.id = id;
        this.device = device;
    }
}
