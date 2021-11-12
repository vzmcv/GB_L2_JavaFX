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

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String message = in.readUTF();
                System.out.println("Сообщение клиента: " + message);
                Scanner scannerServer = new Scanner(System.in);
                System.out.println("Введите сообщение с сервера:");
                out.writeUTF(("\nСообщение с сервера: \n" + scannerServer.nextLine() + "\nотправленно: " + date.toString() + "\n"));
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
