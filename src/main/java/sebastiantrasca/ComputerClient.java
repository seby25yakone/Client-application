package sebastiantrasca;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerClient {
    private Socket clientSocket;
    private BufferedReader in;
    private DataOutputStream out;
    private Computer clientComputer;
    public ComputerClient(String ip, int port, String name, String compname, Byte mem, List<String> techs) throws IOException{
        clientComputer = new Computer(name, compname, mem, techs);
        clientSocket = new Socket(ip, port);
        System.out.println("Connected");
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new DataOutputStream(clientSocket.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(clientComputer);
        System.out.println(json);
        out.writeInt(json.length());
        out.write(json.getBytes());
        out.flush();
        String line;
        try{
            line = in.readLine();
            while(!line.equals("Over")){
                out.writeUTF(name+" says: "+line);
                line = in.readLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Socket read error!");
        }

        finally{
            in.close();
            out.close();
            clientSocket.close();
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter client username: ");
        String name = sc.nextLine();
        System.out.println("Enter client computer name: ");
        String compname = sc.nextLine();
        System.out.println("Enter client computer memory: ");
        Byte mem = sc.nextByte();
        sc.nextLine();
        System.out.println("Enter client computer's available technologies: ");
        List<String> techs = new ArrayList<>();
        String tech;
        while (!(tech = sc.nextLine()).isEmpty()) {
            techs.add(tech);
        }
        ComputerClient client = new ComputerClient("127.0.0.1", 8080,name, compname, mem, techs);
    }

}
