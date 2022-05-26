package com.osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void sending(PrintWriter out, String output) {
        out.write(output + "\n");
        out.flush();
    }

    public static void display(String input) {
        System.out.println("From Client: " + input);
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println("New connection accepted" + "\n");
            sending(out, "Write your name.");
            final String username = in.readLine();
            display(username);
            sending(out, "Are you man or woman?");
            String input  = in.readLine();
            display(input);
            if (input.equals("Woman")) {
                sending(out, "Ok, " + username + "! Women's party starts at 20.");
            } else {
                sending(out, "Ok, " + username + "! Men's party starts at 22.");
            }
            display(in.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
