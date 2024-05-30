package sebastiantrasca;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebastiantrasca.Device;
import com.sebastiantrasca.DeviceRecord;

import java.io.*;
import java.net.*;
import java.time.Instant;
import java.util.*;

public class ComputerClient {
    private Socket clientSocket;
    private BufferedReader in;
    private DataOutputStream out;
    private DeviceRecord clientDevice;

    public ComputerClient(String ip, int port, DeviceRecord device) throws IOException {
        clientDevice = device;
        clientSocket = new Socket(ip, port);
        System.out.println("Connected");
        Instant now = Instant.now();
        Random rand = new Random();
        Long randlong = rand.nextLong() + 1L;
        long id = now.getEpochSecond() / (randlong * 1000) + rand.nextLong() * 3400;
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
        try {
            line = in.readLine();
            while (!line.equals("Over")) {
                out.writeUTF(clientDevice.getId() + " says: " + line);
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket read error!");
        } finally {
            in.close();
            out.close();
            clientSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        List<Device> availableDevices = new ArrayList<Device>();
        ObjectMapper mapper = new ObjectMapper();

        Device selectedDevice = null;
        Map<String,String> fields = null;
        String directoryPath = "src/main/config";
        File directory = new File(directoryPath);
        FileFilter filter = new FileFilter() {

            public boolean accept(File f)
            {
                return f.getName().endsWith(".json");
            }
        };
        File[] files = directory.listFiles(filter);
        try {
            for(File file:files) {
                Device device = mapper.readValue(file, Device.class);
                availableDevices.add(device);
            }
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
            sc.nextLine();
            for(Device device : availableDevices){
                if(device.getClassId() == id){
                    selectedDevice = device;
                    fields = selectedDevice.getFields();
                    ok = true;
                    break;
                }
            }
            if(ok == false)
                System.out.println("Invalid class ID, please try again!");
            else break;
        }

        for(String key : fields.keySet()){
            System.out.println("Enter new value for " + key + ": ");
            String newValue = sc.nextLine();
            fields.put(key, newValue);
        }
        selectedDevice.setFields(fields);
        DeviceRecord clientRecord = new DeviceRecord(0,selectedDevice);
        ComputerClient client = new ComputerClient("127.0.0.1", 8080, clientRecord);
    }
}