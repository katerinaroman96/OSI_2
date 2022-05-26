package com.osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void sending(PrintWriter out, String output) {
        out.write(output + "\n");
        out.flush();
    }

    public static void display(String input) {
        System.out.println("From Server: " + input);
    }

    public static void main(String[] args) {
        String host = "netology.homework";
        try (
            Socket clientSocket = new Socket(host, 8080);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            display(in.readLine());
            sending(out, "Kate");         
            display(in.readLine());
            sending(out, "Woman");
            display(in.readLine());
            sending(out, "Ok, thanks!");
        } catch (IOException ex){
            ex.printStackTrace();
        }


    }
}
