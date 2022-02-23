package com.example.pizzeria;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button boutonNapo;
private Button boutonFromage;
    private Button boutonRoyal;
    private Button boutonMonta;
    private Button boutonRaclette;
    private Button boutonHawa;
    private Button boutonPana;
    private Button boutonTira;
    private Button boutonReset;
    private background b1;
    public static HashMap<String, Integer> nbCommande = new HashMap<String, Integer>();
    public static HashMap<String, Integer> lesPrix = new HashMap<String, Integer>();
    public final static String CLE_SAUVEGARDE_RESULTAT = "CLE_SAUVEGARDE_RESULTAT";
    private String table;
    public  TextView tableT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonReset=findViewById(R.id.buttonReset);
        boutonFromage=findViewById(R.id.buttonFromage);
        boutonNapo=findViewById(R.id.buttonNapo);
        boutonRoyal=findViewById(R.id.buttonRoyal);
        boutonMonta=findViewById(R.id.buttonMonta);
        boutonRaclette=findViewById(R.id.buttonRaclette);
        boutonHawa=findViewById(R.id.buttonHawai);
        boutonPana=findViewById(R.id.buttonPana);
        boutonTira=findViewById(R.id.buttonGlace);
        tableT=findViewById(R.id.labelTable);

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
        Intent intent = getIntent();



        if (intent!=null || intent.hasExtra("edittext")){ // vérifie qu'une valeur est associée à la clé “edittext”
            this.table=intent.getStringExtra("table");
           tableT.setText(table);

        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("CLE_SAUVEGARDE_RESULTAT")){
                nbCommande = (HashMap<String, Integer>) savedInstanceState.getSerializable("CLE_SAUVEGARDE_RESULTAT");
                if(nbCommande!=null){

                }

            }
            resetData();


        }else{
            initQuantite();
        }



    }
    public void initPrix(){
        lesPrix.put("Fromage",12);
        lesPrix.put("Napo",13);
        lesPrix.put("Royal",11);
        lesPrix.put("Hawa",8);
        lesPrix.put("Monta",66);
        lesPrix.put("Raclette",69);
        lesPrix.put("Tira",2);
        lesPrix.put("Pana",3);

    }

    public void resetData(){
            boutonFromage.setText(lesPrix.get("Fromage")+"euro"+ "  Fromage :"+nbCommande.get("Fromage"));
            boutonNapo.setText(lesPrix.get("Napo")+"euro"+ "  Napo :"+nbCommande.get("Napo"));
            boutonRoyal.setText(lesPrix.get("Royal")+"euro"+"  Royal :"+nbCommande.get("Royal"));
            boutonHawa.setText(lesPrix.get("Hawa")+"euro"+ "  Hawa :"+nbCommande.get("Hawa"));
            boutonMonta.setText(lesPrix.get("Monta")+"euro"+ "  Monta :"+nbCommande.get("Monta"));
            boutonRaclette.setText(lesPrix.get("Raclette")+"euro"+ "  Raclette :"+nbCommande.get("Raclette"));
            boutonTira.setText(lesPrix.get("Tira")+"euro"+ "  Tira :"+nbCommande.get("Tira"));
            boutonPana.setText(lesPrix.get("Pana")+"euro"+ "  Pana :"+nbCommande.get("Pana"));

    }
    public void initQuantite(){
        nbCommande.put("Fromage",0);
        nbCommande.put("Napo",0);
        nbCommande.put("Royal",0);
        nbCommande.put("Hawa",0);
        nbCommande.put("Monta",0);
        nbCommande.put("Raclette",0);
        nbCommande.put("Tira",0);
        nbCommande.put("Pana",0);
    }
    public void resetText(){
        boutonFromage.setText(lesPrix.get("Fromage")+"euro"+ "   Fromage 0" );
        boutonNapo.setText(lesPrix.get("Napo")+"euro"+ "   Napo 0");
        boutonRoyal.setText(lesPrix.get("Royal")+"euro"+ "   Royal 0");
        boutonHawa.setText(lesPrix.get("Hawa")+"euro"+ "   Hawa 0");
        boutonMonta.setText(lesPrix.get("Monta")+"euro"+ "   Monta 0");
        boutonRaclette.setText(lesPrix.get("Raclette")+"euro"+ "   Raclette 0");
        boutonTira.setText(lesPrix.get("Tira")+"euro"+ "   Tira 0");
        boutonPana.setText(lesPrix.get("Pana")+"euro"+"   Pana 0");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CLE_SAUVEGARDE_RESULTAT, nbCommande);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.buttonFromage) {
            nbCommande.put("Fromage",nbCommande.get("Fromage")+1);
            boutonFromage.setText(lesPrix.get("Fromage")+"euro"+ "  Fromage :"+nbCommande.get("Fromage"));
            b1= new background(table+"fromage");
            b1.execute();

        }
        if (v.getId() == R.id.buttonNapo) {
            nbCommande.put("Napo",nbCommande.get("Napo")+1);
            boutonNapo.setText(lesPrix.get("Napo")+"euro"+"  Napo :"+nbCommande.get("Napo"));
        }
        if (v.getId() == R.id.buttonRoyal) {
            nbCommande.put("Royal",nbCommande.get("Royal")+1);
            boutonRoyal.setText(lesPrix.get("Royal")+"euro"+ "  Royal :"+nbCommande.get("Royal"));
        }
        if (v.getId() == R.id.buttonHawai) {
            nbCommande.put("Hawa",nbCommande.get("Hawa")+1);
            boutonHawa.setText(lesPrix.get("Hawa")+"euro"+ "  Hawa :"+nbCommande.get("Hawa"));
        }
        if (v.getId() == R.id.buttonMonta) {
            nbCommande.put("Monta",nbCommande.get("Monta")+1);
            boutonMonta.setText(lesPrix.get("Monta")+"euro"+ "  Monta :"+nbCommande.get("Monta"));
        }
        if (v.getId() == R.id.buttonRaclette) {
            nbCommande.put("Raclette",nbCommande.get("Raclette")+1);
            boutonRaclette.setText(lesPrix.get("Raclette")+"euro"+ "  Raclette :"+nbCommande.get("Raclette"));
        }
        if (v.getId() == R.id.buttonGlace) {
            nbCommande.put("Tira",nbCommande.get("Tira")+1);
            boutonTira.setText(lesPrix.get("Tira")+"euro"+ "  Tira :"+nbCommande.get("Tira"));

        }
        if (v.getId() == R.id.buttonPana) {
            nbCommande.put("Pana",nbCommande.get("Pana")+1);
            boutonPana.setText(lesPrix.get("Pana")+"euro"+ "  Pana :"+nbCommande.get("Pana"));
        }
        if (v.getId() == R.id.buttonReset) {
           initQuantite();
           resetText();



        }




    }
    public class  background extends AsyncTask<String, Void, String> {
        private  Socket clientSocket;
        private String message;
        private  BufferedReader in;
        private  PrintWriter out;

        public background(String message){
        this.message=message;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                clientSocket = new Socket("chadok.info",9874);
                out = new PrintWriter(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println("04montagnarde");
                System.out.println("test");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }}