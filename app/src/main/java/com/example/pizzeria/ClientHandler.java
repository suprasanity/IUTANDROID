package com.example.pizzeria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements  Runnable{
    private final Socket sock;
    private final InputStream communication;
    final PrintWriter out;
    public boolean ban = false;


    ClientHandler(Socket sock)throws IOException{
        this.sock=sock;
        this.communication=sock.getInputStream();
        this.out= new PrintWriter(sock.getOutputStream());

    }
    @Override
    public void run(){
        System.out.println("je suis 1 client");
        BufferedReader reader = new BufferedReader(new InputStreamReader(communication));
        while ((!this.ban)){
            String msg;
            try {
                msg = reader.readLine();
                out.println("La table "+ msg);
                System.out.println("envoie du msg de commande");
                out.flush();
                Thread.sleep(4000);
                out.println(msg +"c'est pret");
                System.out.println("envoie du msg de la fin de commande");
                out.flush();

            }catch (Exception e){

                this.ban = true;
            System.out.println(e.toString());}
        }
    }
}
