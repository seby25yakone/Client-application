package sebastiantrasca;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerClient {
    private Socket clientSocket;
    private BufferedReader in;
    private DataOutputStream out;
    private Record<?> clientDevice;
    public ComputerClient(String ip, int port, Record<?> device) throws IOException{
        clientDevice = device;
        clientSocket = new Socket(ip, port);
        System.out.println("Connected");
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new DataOutputStream(clientSocket.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(device);
        System.out.println(json);
        out.writeInt(json.length());
        out.write(json.getBytes());
        out.flush();
        String line;
        try{
            line = in.readLine();
            while(!line.equals("Over")){
                out.writeUTF(clientDevice.getId()+" says: "+line);
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
                System.out.println("Enter CPU speed: ");
                double cpuSpeed = sc.nextDouble();
                System.out.println("Enter CPU type: ");
                String cpuType = sc.nextLine();
                System.out.println("Enter disk space: ");
                int diskSpace = sc.nextInt();
                System.out.println("Does your computer have a floppy drive? (True/false");
                boolean floppy = sc.nextBoolean();
                System.out.println("Enter computer form factor: ");
                String formFactor = sc.nextLine();
                System.out.println("Enter operating system: ");
                String operatingSystem = sc.nextLine();
                System.out.println("Enter your operating system type: (32/64-bit)");
                Byte osAddressWidth = sc.nextByte();
                System.out.println("Enter operating system version: ");
                String osVersion = sc.nextLine();
                System.out.println("Enter computer RAM memory (in MB): ");
                int ram = sc.nextInt();
                System.out.println("Is this computer a virtual machine? (True/false): ");
                boolean isVirtual = sc.nextBoolean();
                Computer clientComp = new Computer(cdRom,cdSpeed,chassisType,cpuCoreCount,cpuCoreThread,cpuCount, cpuName, cpuSpeed, cpuType, diskSpace, floppy, formFactor, operatingSystem, osAddressWidth, osVersion, ram, isVirtual);
                Record<Computer> device = new Record<>(0, Type.COMPUTER, clientComp);
                ComputerClient client = new ComputerClient("127.0.0.1", 8080, device);
                break;
            }
            else if(line.toLowerCase().equals("p")){
                System.out.println("Enter printer type: ");
                String printerType = sc.nextLine();
                System.out.println("Enter printer resolution: ");
                String resolution = sc.nextLine();
                System.out.println("Enter printer color type (full-color/b&w): ");
                String color = sc.nextLine();
                System.out.println("Enter colored printing speed (enter 0 if printer is b&w only): ");
                double colorSpeed = sc.nextDouble();
                System.out.println("Enter b&w printing speed: ");
                double monoSpeed = sc.nextDouble();
                System.out.println("Enter primary paper format: ");
                String format = sc.nextLine();
                System.out.println("Enter printer connection type(s): ");
                String connectionType = sc.nextLine();
                Printer clientPrinter = new Printer(printerType,resolution,color,colorSpeed,monoSpeed,format,connectionType);
                Record<Printer> device = new Record<>(0,Type.PRINTER, clientPrinter);
                ComputerClient client = new ComputerClient("127.0.0.1", 8080, device);
                break;
            }
            else if (line.toLowerCase().equals("r")){

            }
            else System.out.println("Not a valid option!");
        }


    }

}
