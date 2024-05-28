package sebastiantrasca;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private Record clientDevice;
    public ComputerClient(String ip, int port, Record device) throws IOException{
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
        List<Device> availableDevices = new ArrayList<Device>();
        ObjectMapper mapper = new ObjectMapper();
        String directoryPath = "/src/main/config";
        File directory = new File(directoryPath);
        try {
            Files.list(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            Device device = mapper.readValue(path.toFile(), Device.class);
                            availableDevices.add(device);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            boolean ok = false;
            System.out.println("Choose device type:");
            for(Device device : availableDevices){
                System.out.println("For " + device.getType() + " enter " + device.getClassId());
            }
            int id = sc.nextInt();
            for(Device device : availableDevices){
                if(device.getClassId() == id){
                    Device selectedDevice = device;
                    ok = true;
                    break;
                }
            }
            if(ok == false)
                System.out.println("Invalid class ID, please try again!");
        }

    }

}
