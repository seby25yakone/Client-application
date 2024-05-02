package sebastiantrasca;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Computer implements Serializable {
    @Serial
    private static final long serialVersionUID = 25102003L;
    private String username;
    private String computer_name;
    private Byte memory;
    private List<String> technologies; //list of available technologies on the computer, i.e "java", "python", "php" etc.

    public Computer(String user, String cn, Byte mem, List<String> techs){
        username = user;
        computer_name = cn;
        memory = mem;
        technologies = techs;
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

}
