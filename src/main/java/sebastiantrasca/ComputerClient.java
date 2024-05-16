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
    private Record<Type> clientDevice;
    public ComputerClient(String ip, int port, String name, String compname, Byte mem, List<String> techs) throws IOException{
        clientDevice = new Record<>();
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
        while(true){
            System.out.println("Enter device type(C for Computer, P for Printer, R for Router): ");
            String line = sc.nextLine();
            if(line.toLowerCase().equals("c")){
                System.out.println("Does your computer have a CDROM? (True/false)");
                boolean cdRom = sc.nextBoolean();
                System.out.println("Enter CD speed (0 if you have no CDROM): ");
                double cdSpeed = sc.nextDouble();
                System.out.println("Enter chassis type: ");
                String chassisType = sc.nextLine();
                System.out.println("Enter CPU Core count: ");
                int cpuCoreCount = sc.nextInt();
                System.out.println("Enter number of CPU threads: ");
                int cpuCoreThread = sc.nextInt();
                System.out.println("Enter number of CPUs: ");
                int cpuCount = sc.nextInt();
                System.out.println("Enter CPU name: ");
                String cpuName = sc.nextLine();
            }
            else if(line.toLowerCase().equals("p")){

            }
            else if (line.toLowerCase().equals("r")){

            }
            else System.out.println("Not a valid option!");
        }


    }

}
