package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Computer{
    private boolean cdRom;
    private double cdSpeed;
    private String chassisType;
    private int cpuCoreCount;
    private int cpuCoreThread;
    private int cpuCount;
    private String cpuName;
    private double cpuSpeed;
    private String cpuType;
    private int diskSpace;
    private boolean floppy;
    private String formFactor;
    private String operatingSystem;
    private Byte osAddressWidth;
    private String osVersion;
    private int ram;
    private boolean isVirtual;

    @JsonCreator
    public Computer(@JsonProperty("cdRom") boolean cdRom,@JsonProperty("cdSpeed") double cdSpeed, @JsonProperty("chassisType") String chassisType,@JsonProperty("cpuCoreCount") int cpuCoreCount, @JsonProperty("cpuCoreThread") int cpuCoreThread,@JsonProperty("cpuCount") int cpuCount,@JsonProperty("cpuName") String cpuName,@JsonProperty("cpuSpeed") double cpuSpeed,@JsonProperty("cpuType") String cpuType,@JsonProperty("diskSpace") int diskSpace,@JsonProperty("floppy") boolean floppy,@JsonProperty("formFactor") String formFactor, @JsonProperty("operatingSystem") String operatingSystem, @JsonProperty("osAddressWidth") Byte osAddressWidth, @JsonProperty("osVersion") String osVersion, @JsonProperty("ram") int ram, @JsonProperty("isVirtual") boolean isVirtual) {
        this.cdRom = cdRom;
        this.cdSpeed = cdSpeed;
        this.chassisType = chassisType;
        this.cpuCoreCount = cpuCoreCount;
        this.cpuCoreThread = cpuCoreThread;
        this.cpuCount = cpuCount;
        this.cpuName = cpuName;
        this.cpuSpeed = cpuSpeed;
        this.cpuType = cpuType;
        this.diskSpace = diskSpace;
        this.floppy = floppy;
        this.formFactor = formFactor;
        this.operatingSystem = operatingSystem;
        this.osAddressWidth = osAddressWidth;
        this.osVersion = osVersion;
        this.ram = ram;
        this.isVirtual = isVirtual;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cdRom=" + cdRom +
                ", cdSpeed=" + cdSpeed +
                ", chassisType='" + chassisType + '\'' +
                ", cpuCoreCount=" + cpuCoreCount +
                ", cpuCoreThread=" + cpuCoreThread +
                ", cpuCount=" + cpuCount +
                ", cpuName='" + cpuName + '\'' +
                ", cpuSpeed=" + cpuSpeed +
                ", cpuType='" + cpuType + '\'' +
                ", diskSpace=" + diskSpace +
                ", floppy='" + floppy + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", osAddressWidth=" + osAddressWidth +
                ", osVersion='" + osVersion + '\'' +
                ", ram=" + ram +
                ", isVirtual=" + isVirtual +
                '}';
    }

    public boolean isCdRom() {
        return cdRom;
    }

    public double getCdSpeed() {
        return cdSpeed;
    }

    public String getChassisType() {
        return chassisType;
    }

    public int getCpuCoreCount() {
        return cpuCoreCount;
    }

    public int getCpuCoreThread() {
        return cpuCoreThread;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public String getCpuName() {
        return cpuName;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public String getCpuType() {
        return cpuType;
    }

    public int getDiskSpace() {
        return diskSpace;
    }

    public boolean isFloppy() {
        return floppy;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public Byte getOsAddressWidth() {
        return osAddressWidth;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public int getRam() {
        return ram;
    }

    public boolean isVirtual() {
        return isVirtual;
    }
}
