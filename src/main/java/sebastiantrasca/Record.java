package sebastiantrasca;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Record<T> {
    private long id;
    private Type deviceType;
    private T device;
    @JsonCreator
    public Record(@JsonProperty("id") long id, @JsonProperty("deviceType") Type deviceType, T item) {
        if (!deviceType.getClazz().isInstance(device)) {
            throw new IllegalArgumentException("Type does not match the item class.");
        }
        this.id = id;
        this.deviceType = deviceType;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public Type getType() {
        return deviceType;
    }

    public T getDevice() {
        return device;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", type=" + deviceType +
                ", device=" + device +
                '}';
    }
}
