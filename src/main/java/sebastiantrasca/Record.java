package sebastiantrasca;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Record {
    @JsonProperty("id")
    private long id;
    @JsonProperty("device")
    private Device device;

    @JsonCreator
    public Record(@JsonProperty("id") long id, @JsonProperty("device") Device device) {
        this.id = id;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
