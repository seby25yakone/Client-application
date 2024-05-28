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

    }

}
