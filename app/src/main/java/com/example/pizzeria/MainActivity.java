package com.example.pizzeria;

import android.app.FragmentTransaction;
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

import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity {
    private Button boutonNapo;
    private Button boutonFromage;
    private Button boutonRoyal;
    private Button boutonMonta;
    private Button boutonRaclette;
    private Button boutonHawa;
    private Button boutonPana;
    private Button boutonTira;
    private Button boutonReset;
    private Button boutonPersonnalise;
    public static pizza fragPizza;
    public static ingredient fragIngredient;
    public static HashMap<String, Integer> nbCommande = new HashMap<String, Integer>();
    public static HashMap<String, Integer> lesPrix = new HashMap<String, Integer>();
    public final static String CLE_SAUVEGARDE_RESULTAT = "CLE_SAUVEGARDE_RESULTAT";
    public static String table;
    public static TextView tableT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pizza frag = new pizza();
        this.fragPizza= frag;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, frag);
        transaction.commit();

        setContentView(R.layout.activity_main);

        tableT=findViewById(R.id.labelTable);
        Intent intent = getIntent();



        if (intent!=null || intent.hasExtra("edittext")){ // vérifie qu'une valeur est associée à la clé “edittext”
            this.table=intent.getStringExtra("table");
            if(parseInt(MainActivity.table)<10 ){
                this.table="0"+this.table;
                System.out.println("la table s'écrit avec un 0 mnt");
            }
           tableT.setText(table);

        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("CLE_SAUVEGARDE_RESULTAT")){
                nbCommande = (HashMap<String, Integer>) savedInstanceState.getSerializable("CLE_SAUVEGARDE_RESULTAT");
                if(nbCommande!=null){

                }

            }
            System.out.println("reset data");
            resetData();


        }else{
            pizza.initQuantite();
        }



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



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CLE_SAUVEGARDE_RESULTAT, nbCommande);
    }

  }

