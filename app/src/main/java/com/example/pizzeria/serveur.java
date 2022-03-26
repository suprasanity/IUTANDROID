package com.example.pizzeria;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {
    private final ServerSocket ss;
    private boolean acceptation = true;

    public serveur (int port )throws IOException {
        this.ss = new ServerSocket(port);
        acceuil();
    }

    private void acceuil() throws IOException {
        while (this.acceptation){
            Socket sock = this.ss.accept();
            ClientHandler client = new ClientHandler(sock);
            Thread tClient = new Thread (client);
            tClient.start();
        }
    }
}
