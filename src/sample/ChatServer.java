package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ChatServer {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        Date date = new Date();
        DataInputStream in;
        DataOutputStream out;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Клиент подключился");
            System.out.println("Введите сообщение с сервера:");
            in = new DataInputStream(socket.getInputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Scanner scannerServer = new Scanner(System.in);
                            String messageServer = scannerServer.nextLine();
                            System.out.println("Введите сообщение с сервера:");
                            out.writeUTF("Сообщение с сервера: \n" + messageServer + "\nотправленно: " + date.toString());
                            if ("/end".equals(messageServer)) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                String message = in.readUTF();
                System.out.println("Сообщение клиента: " + message);
                //System.out.println("Введите сообщение с сервера:");
                if (message.equals("/end")) {
                    out.writeUTF("/end");
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
