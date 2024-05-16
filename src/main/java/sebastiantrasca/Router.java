package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Router {
    private int lanPorts;
    private int wanPorts;
    private int ethernetRate;
    private int wifiRate;
    private int antennaeType;
    private int frequency;
    private boolean usbPort;
    private String wifiStandard;
    private String security;

    @JsonCreator
    public Router(@JsonProperty("lanPorts") int lanPorts, @JsonProperty("wanPorts") int wanPorts,@JsonProperty("ethernetRate") int ethernetRate,@JsonProperty("wifiRate") int wifiRate, @JsonProperty("antennaeType") int antennaeType,@JsonProperty("frequency") int frequency, @JsonProperty("usbPort") boolean usbPort, @JsonProperty("wifiStandard") String wifiStandard,@JsonProperty("security") String security) {
        this.lanPorts = lanPorts;
        this.wanPorts = wanPorts;
        this.ethernetRate = ethernetRate;
        this.wifiRate = wifiRate;
        this.antennaeType = antennaeType;
        this.frequency = frequency;
        this.usbPort = usbPort;
        this.wifiStandard = wifiStandard;
        this.security = security;
    }
}
