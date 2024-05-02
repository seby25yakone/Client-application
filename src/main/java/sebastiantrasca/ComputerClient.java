package sebastiantrasca;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerClient {
    private Socket clientSocket;
    private BufferedReader in;
    private DataOutputStream out;
    private ObjectOutputStream compout;
    private Computer clientComputer;
    public ComputerClient(String ip, int port, String name, String compname, Byte mem, List<String> techs) throws IOException{
        clientComputer = new Computer(name, compname, mem, techs);
        clientSocket = new Socket(ip, port);
        System.out.println("Connected");
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new DataOutputStream(clientSocket.getOutputStream());
        compout = new ObjectOutputStream(clientSocket.getOutputStream());
        compout.writeObject(clientComputer);
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
        System.out.println("Enter client computer's available technologies: ");
        List<String> techs = new ArrayList<>();
        techs.add(sc.nextLine());
        while(!sc.nextLine().isEmpty()){
            techs.add(sc.nextLine());
        }
        ComputerClient client = new ComputerClient("127.0.0.1", 8080,name, compname, mem, techs);
    }

}
