package server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Handler; 
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ClientHandler extends Thread{
    
    static ArrayList<User> users_list = new ArrayList<User>();
    private Socket socket;
    static private String serverName;
    static private ArrayList<ClientHandler> handlerList = new ArrayList<>();
    public ClientHandler(Socket socket, String serverName ,ArrayList<ClientHandler> handlerList){
        this.socket = socket;
        this.handlerList = handlerList;
        this.serverName = serverName;
    }
    
    public Socket getSocket(){
        return this.socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected.");
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferReader = new BufferedReader(streamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            gestore(printWriter, bufferReader);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void gestore(PrintWriter printWriter, BufferedReader bufferedReader)throws Exception{
        accesso(printWriter, bufferedReader);
        invio_lista_utenti(printWriter, bufferedReader);
        while (true) {

        }
    }

    public static void accesso(PrintWriter printWriter, BufferedReader bufferedReader) throws Exception{
        printWriter.println("Ti sei connesso al server "+ serverName +", come ti chiami?");
        String str = bufferedReader.readLine();
        String name = str;
        printWriter.println("Inserire il proprio ip");
        str = bufferedReader.readLine();
        String ip_user = str;
        User user = new User(name, ip_user);
        users_list.add(user);
    }

    public static void invio_lista_utenti(PrintWriter printWriter, BufferedReader bufferedReader){
        for(ClientHandler client : handlerList) {
            JSONArray json = new JSONArray();
            JSONObject jsoner = new JSONObject();
            for (int i = 0; i < users_list.size(); i++) {
                json.add("\" id_address: "+ users_list.get(i).getIp_address() + ", name" + users_list.get(i).getUser_name() + "]");
                jsoner.put("user", json);
            }
            client.printWriter.println("");
        }
    }
}
