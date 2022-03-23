package com.example.pizzeria;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class pizza extends Fragment implements View.OnClickListener{

    public Button boutonNapo;
    public Button boutonFromage;
    public Button boutonRoyal;
    public Button boutonMonta;
    public Button boutonRaclette;
    public Button boutonHawa;
    public Button boutonPana;
    public Button boutonTira;
    public Button boutonReset;
    public Button boutonPersonalise;
    public pizza.background b1;


    public pizza() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pizza, container, false);

        boutonReset=v.findViewById(R.id.reset);
        boutonFromage=v.findViewById(R.id.fromage);
        boutonNapo=v.findViewById(R.id.Napo);
        boutonRoyal=v.findViewById(R.id.royal);
        boutonMonta=v.findViewById(R.id.monta);
        boutonRaclette=v.findViewById(R.id.raclette);
        boutonHawa=v.findViewById(R.id.buttonHawai);
        boutonPana=v.findViewById(R.id.buttonPana);
        boutonTira=v.findViewById(R.id.buttonGlace);
        boutonPersonalise=v.findViewById(R.id.buttonPersonalise);
        initPrix();
        resetText();
        boutonNapo.setOnClickListener(this);
        boutonFromage.setOnClickListener(this);
        boutonRoyal.setOnClickListener(this);
        boutonRaclette.setOnClickListener(this);
        boutonHawa.setOnClickListener(this);
        boutonPana.setOnClickListener(this);
        boutonMonta.setOnClickListener(this);
        boutonTira.setOnClickListener(this);
        boutonReset.setOnClickListener(this);
        boutonPersonalise.setOnClickListener(this);

        return v;
    }
@Override
    public void onClick(View v) {

        if (v.getId() == R.id.fromage) {
            System.out.println("je commande une fromage");
            MainActivity.nbCommande.put("Fromage",MainActivity.nbCommande.get("Fromage")+1);
            boutonFromage.setText(MainActivity.lesPrix.get("Fromage")+"euro"+ "  Fromage :"+MainActivity.nbCommande.get("Fromage"));
            commande("frommage");

        }
    if (v.getId() == R.id.royal) {
        System.out.println("je commande une royal");
        MainActivity.nbCommande.put("Royal",MainActivity.nbCommande.get("Royal")+1);
        boutonRoyal.setText(MainActivity.lesPrix.get("Royal")+"euro"+ "  Royal :"+MainActivity.nbCommande.get("Royal"));
        commande("royal");}

        if (v.getId() == R.id.Napo) {
            MainActivity.nbCommande.put("Napo",MainActivity.nbCommande.get("Napo")+1);
            boutonNapo.setText(MainActivity.lesPrix.get("Napo")+"euro"+"  Napo :"+MainActivity.nbCommande.get("Napo"));
            commande("napo");
        }
        if (v.getId() == R.id.jambon) {
            MainActivity. nbCommande.put("Royal",MainActivity.nbCommande.get("Royal")+1);
            boutonRoyal.setText(MainActivity.lesPrix.get("Royal")+"euro"+ "  Royal :"+MainActivity.nbCommande.get("Royal"));
            commande("royal");
        }
        if (v.getId() == R.id.buttonHawai) {
            MainActivity.nbCommande.put("Hawa",MainActivity.nbCommande.get("Hawa")+1);
            boutonHawa.setText(MainActivity.lesPrix.get("Hawa")+"euro"+ "  Hawa :"+MainActivity.nbCommande.get("Hawa"));
            commande("hawai");

        }
        if (v.getId() == R.id.monta) {
            MainActivity.nbCommande.put("Monta",MainActivity.nbCommande.get("Monta")+1);
            boutonMonta.setText(MainActivity.lesPrix.get("Monta")+"euro"+ "  Monta :"+MainActivity.nbCommande.get("Monta"));
            commande("Monta");
        }
        if (v.getId() == R.id.raclette) {
            MainActivity.nbCommande.put("Raclette",MainActivity.nbCommande.get("Raclette")+1);
            boutonRaclette.setText(MainActivity.lesPrix.get("Raclette")+"euro"+ "  Raclette :"+MainActivity.nbCommande.get("Raclette"));
            commande("Monta");
        }
        if (v.getId() == R.id.buttonGlace) {
            MainActivity.nbCommande.put("Tira",MainActivity.nbCommande.get("Tira")+1);
            boutonTira.setText(MainActivity.lesPrix.get("Tira")+"euro"+ "  Tira :"+MainActivity.nbCommande.get("Tira"));
            commande("Tira");

        }
        if (v.getId() == R.id.buttonPana) {
            MainActivity.nbCommande.put("Pana",MainActivity.nbCommande.get("Pana")+1);
            boutonPana.setText(MainActivity.lesPrix.get("Pana")+"euro"+ "  Pana :"+MainActivity.nbCommande.get("Pana"));
            commande("Pana");
        }
        if (v.getId() == R.id.reset) {
            initQuantite();
            resetText();
        }
    if (v.getId() == R.id.buttonPersonalise) {
        System.out.println("personalise");
        MainActivity.fragIngredient = new ingredient();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(MainActivity.fragPizza);
        transaction.add(R.id.fragment,MainActivity.fragIngredient);
        transaction.commit();

    }



    }

    public static void commande(String pizza) {
        Thread recevoir = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new background2(MainActivity.table+pizza);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        recevoir.start();
    }

    public void initPrix(){
        MainActivity.lesPrix.put("Fromage",12);
        MainActivity.lesPrix.put("Napo",13);
        MainActivity.lesPrix.put("Royal",11);
        MainActivity.lesPrix.put("Hawa",8);
        MainActivity.lesPrix.put("Monta",66);
        MainActivity. lesPrix.put("Raclette",69);
        MainActivity.lesPrix.put("Tira",2);
        MainActivity.lesPrix.put("Pana",3);

    }
    public void resetText(){
        System.out.println("resssssssssssssssssssssssssssssseeeeeeeeeeeeeeeeet");
        boutonFromage.setText( MainActivity.lesPrix.get("Fromage")+"euro"+ "   Fromage"+ MainActivity.nbCommande.get("Fromage") );
        boutonNapo.setText( MainActivity.lesPrix.get("Napo")+"euro"+ "   Napo "+ MainActivity.nbCommande.get("Napo") );
        boutonRoyal.setText( MainActivity.lesPrix.get("Royal")+"euro"+ "   Royal "+ MainActivity.nbCommande.get("Royal") );
        boutonHawa.setText( MainActivity.lesPrix.get("Hawa")+"euro"+ "   Hawai "+ MainActivity.nbCommande.get("Hawa") );
        boutonMonta.setText( MainActivity.lesPrix.get("Monta")+"euro"+ "   Monta "+ MainActivity.nbCommande.get("Monta") );
        boutonRaclette.setText( MainActivity.lesPrix.get("Raclette")+"euro"+ "   Raclette "+ MainActivity.nbCommande.get("Raclette") );
        boutonTira.setText( MainActivity.lesPrix.get("Tira")+"euro"+ "   Tira "+ MainActivity.nbCommande.get("Tira") );
        boutonPana.setText( MainActivity.lesPrix.get("Pana")+"euro"+"   Pana "+ MainActivity.nbCommande.get("Pana") );
    }
    public static void initQuantite(){
        MainActivity. nbCommande.put("Fromage",0);
        MainActivity.nbCommande.put("Napo",0);
        MainActivity.nbCommande.put("Royal",0);
        MainActivity. nbCommande.put("Hawa",0);
        MainActivity.nbCommande.put("Monta",0);
        MainActivity.nbCommande.put("Raclette",0);
        MainActivity.nbCommande.put("Tira",0);
        MainActivity.nbCommande.put("Pana",0);
    }
    public static class  background extends AsyncTask<String, Void, String> {
        private Socket clientSocket;
        private String message;
        private BufferedReader in;
        private PrintWriter out;
        private String msg;
        private int reception=0;

        public background(String message){
            this.message=message;

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                System.out.println("démarage de la backtask");
                clientSocket = new Socket("chadok.info",9874);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream());
                System.out.println("envoie du message "+message);
                out.println(message);
                out.flush();
                while (reception!=2)
                {
                    msg=in.readLine();
                    if(msg!=null && !msg.equals("")){
                        System.out.println(msg);
                        reception++;
                    }
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class background2 {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String msg;
        private int reception=0;

        public background2(String donne) throws IOException {
            System.out.println("la donné"+donne);

            System.out.println("démarage de la backtask");
            clientSocket = new Socket("chadok.info",9874);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            System.out.println("envoie du message "+donne);
            out.println(donne);
            out.flush();
            while (reception!=2)
            {
                msg=in.readLine();
                if(msg!=null && !msg.equals("")){
                    System.out.println(msg);
                    if(reception==1)
                    {
                        System.out.println("changementttttttttttttttttttttttttttttttttttttttttttttttttttttttttt ");
                        MainActivity.tableT.setText(msg+"/n");

                    }
                    reception++;
                }
            }
        }

    }
}