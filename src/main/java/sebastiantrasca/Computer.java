package sebastiantrasca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Computer implements Serializable {
    @Serial
    private static final long serialVersionUID = 25102003L;
    private String username;
    private String computer_name;
    private Byte memory;
    private String connectionTime;
    private List<String> technologies; //list of available technologies on the computer, i.e "java", "python", "php" etc.

    public Computer(){
        username = "unknown";
        computer_name = "unknown";
        memory = 0;
        connectionTime = "N/A";
        technologies = null;
    }
    @JsonCreator
    public Computer(@JsonProperty("username") String username, @JsonProperty("computer_name") String compname, @JsonProperty("memory") Byte memory, @JsonProperty("technologies") List<String> technologies){
        this.username = username;
        this.computer_name = compname;
        this.memory = memory;
        connectionTime = LocalDateTime.now().toString();
        this.technologies = technologies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComputer_name() {
        return computer_name;
    }

    public void setComputer_name(String computer_name) {
        this.computer_name = computer_name;
    }

    public Byte getMemory() {
        return memory;
    }

    public void setMemory(Byte memory) {
        this.memory = memory;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public void addTechnology(String tech){
        technologies.add(tech);
    }

    public void removeTechnology(String tech){
        if(technologies.contains(tech.trim().toLowerCase())){
            technologies.remove(tech.trim().toLowerCase());
        }
    }
    public String toString(){
        return "Username: " + this.username + "\nComputer name: " + this.computer_name + "\nRAM Memory: " +
                this.memory + "\nAvailable technologies: " + this.technologies + "\nTime of connection: " + connectionTime;
    }

}
