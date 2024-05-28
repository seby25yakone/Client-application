package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Device {
    @JsonProperty("classId")
    private int classId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("properties")
    private Map<String, String> fields;

    @JsonCreator
    public Device(@JsonProperty("classId") int classId, @JsonProperty("type") String type, @JsonProperty("properties") Map<String, String> fields){
        this.classId = classId;
        this.type = type;
        this.fields = fields;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
