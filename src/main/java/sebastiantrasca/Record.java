package sebastiantrasca;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Record<T> {
    private long id;
    private Type deviceType;
    private String deviceInfo;
    @JsonCreator
    public Record(@JsonProperty("id") long id, @JsonProperty("deviceType") Type deviceType, @JsonProperty("deviceInfo") String deviceInfo) {
        this.id = id;
        this.deviceType = deviceType;
        this.deviceInfo = deviceInfo;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("deviceType")
    public Type getDeviceType() {
        return deviceType;
    }

    @JsonProperty("deviceInfo")
    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", deviceType=" + deviceType +
                ", device=" + deviceInfo +
                '}';
    }
}
