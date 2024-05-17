package sebastiantrasca;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        Instant now = Instant.now();
        Random rand = new Random();
        Long randlong = rand.nextLong()+1L;
        long id = now.getEpochSecond()/(randlong*1000) + rand.nextLong()*3400;
        clientDevice.setId(id);
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
        ObjectMapper mapper = new ObjectMapper();
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
                String json = mapper.writeValueAsString(clientComp);
                Record<Computer> device = new Record<>(0, Type.COMPUTER, json);
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
                sc.nextLine();
                System.out.println("Enter primary paper format: ");
                String format = sc.nextLine();
                System.out.println("Enter printer connection type(s): ");
                String connectionType = sc.nextLine();
                Printer clientPrinter = new Printer(printerType,resolution,color,colorSpeed,monoSpeed,format,connectionType);
                String json = mapper.writeValueAsString(clientPrinter);
                Record<Printer> device = new Record<>(0,Type.PRINTER, json);
                ComputerClient client = new ComputerClient("127.0.0.1", 8080, device);
                break;
            }
            else if (line.toLowerCase().equals("r")){
                System.out.println("Enter number of LAN ports: ");
                int lanPorts = sc.nextInt();
                System.out.println("Enter number of WAN ports: ");
                int wanPorts = sc.nextInt();
                System.out.println("Enter Ethernet transfer rate: ");
                int ethernetRate = sc.nextInt();
                System.out.println("Enter Wi-Fi transfer rate: ");
                int wifiRate = sc.nextInt();
                System.out.println("Enter antenna type: ");
                String antennaeType = sc.nextLine();
                System.out.println("Enter frequency: ");
                int frequency = sc.nextInt();
                System.out.println("Does the router have a USB port? (True/false): ");
                boolean usbPort = sc.nextBoolean();
                System.out.println("Enter Wi-Fi standard: ");
                String wifiStandard = sc.nextLine();
                System.out.println("Enter security protocol(s): ");
                String security = sc.nextLine();
                Router clientRouter = new Router(lanPorts,wanPorts,ethernetRate,wifiRate,antennaeType,frequency,usbPort,wifiStandard,security);
                String json = mapper.writeValueAsString(clientRouter);
                Record<Router> device = new Record<Router>(0, Type.ROUTER, json);
                ComputerClient client = new ComputerClient("127.0.0.1", 8080, device);
                break;
            }
            else System.out.println("Not a valid option!");
        }


    }

}
