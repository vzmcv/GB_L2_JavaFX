package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Controller controller;

    public ChatClient(Controller controller) throws IOException {
        this.controller = controller;
        openConnection();
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        final String message = in.readUTF();
                        if ("/end".equals(message)) {
                            break;
                        }
                        controller.addMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    closeConnection();
                }
            }
        }).start();
    }

    public void closeConnection() {
        if (socket != null){
            try {
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if (in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null){
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void sendMessage(String message) {

        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}