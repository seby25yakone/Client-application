package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Router {
    private int lanPorts;
    private int wanPorts;
    private int ethernetRate;
    private int wifiRate;
    private String antennaeType;
    private int frequency;
    private boolean usbPort;
    private String wifiStandard;
    private String security;

    @JsonCreator
    public Router(@JsonProperty("lanPorts") int lanPorts, @JsonProperty("wanPorts") int wanPorts,@JsonProperty("ethernetRate") int ethernetRate,@JsonProperty("wifiRate") int wifiRate, @JsonProperty("antennaeType") String antennaeType,@JsonProperty("frequency") int frequency, @JsonProperty("usbPort") boolean usbPort, @JsonProperty("wifiStandard") String wifiStandard,@JsonProperty("security") String security) {
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

    public int getLanPorts() {
        return lanPorts;
    }

    public int getWanPorts() {
        return wanPorts;
    }

    public int getEthernetRate() {
        return ethernetRate;
    }

    public int getWifiRate() {
        return wifiRate;
    }

    public String getAntennaeType() {
        return antennaeType;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean isUsbPort() {
        return usbPort;
    }

    public String getWifiStandard() {
        return wifiStandard;
    }

    public String getSecurity() {
        return security;
    }

    @Override
    public String toString() {
        return "LAN ports: " + lanPorts +
                "\nWAN ports: " + wanPorts +
                "\nEthernet rate: " + ethernetRate +
                "\nWi-Fi rate: " + wifiRate +
                "\nAntenna type: " + antennaeType +
                "\nFrequency: " + frequency +
                "\nUSB port: " + usbPort +
                "\nWi-Fi standard: " + wifiStandard +
                "\nSecurity: " + security + '\n';
    }
}
