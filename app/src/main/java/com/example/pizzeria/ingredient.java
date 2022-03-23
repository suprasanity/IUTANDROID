package com.example.pizzeria;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ingredient extends Fragment implements View.OnClickListener{
    public Button boutonJambon;
    public Button boutonCapres;
    public Button boutonOlive;
    public Button boutonAnchois;
    public Button boutonArtichaud;
    public Button boutonChorizo;
    public Button boutonChampi;
    public Button boutonMoza;
    public Button boutonConfirmer;
    public ArrayList<String> ingredients = new ArrayList<>() ;



    public ingredient() {
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
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false);

        boutonJambon=v.findViewById(R.id.jambon);
        boutonCapres=v.findViewById(R.id.buttoncapres);
        boutonOlive=v.findViewById(R.id.olives);
        boutonAnchois=v.findViewById(R.id.anchois);
        boutonChorizo=v.findViewById(R.id.buttonchorizo);
        boutonArtichaud=v.findViewById(R.id.artichaut);
        boutonChampi=v.findViewById(R.id.buttonchampi);
        boutonMoza=v.findViewById(R.id.Mozarella);
        boutonConfirmer=v.findViewById(R.id.reset);


        boutonJambon.setOnClickListener(this);
        boutonCapres.setOnClickListener(this);
        boutonOlive.setOnClickListener(this);
        boutonAnchois.setOnClickListener(this);
        boutonChorizo.setOnClickListener(this);
        boutonArtichaud.setOnClickListener(this);
        boutonChampi.setOnClickListener(this);
        boutonMoza.setOnClickListener(this);
        boutonConfirmer.setOnClickListener(this);
        boutonConfirmer.setEnabled(false);

        return v;
    }
    @Override
    public void onClick(View v) {
        boutonConfirmer.setEnabled(true);
        if (v.getId() == R.id.olives) {
            System.out.println("je choisie un suplement une olive");
            ingredients.add("olive");

        }

        if (v.getId() == R.id.jambon) {
            System.out.println("je choisie un suplement une Jambon");
            ingredients.add("jambon");

        }
        if (v.getId() == R.id.buttonchorizo) {
            System.out.println("je choisie un suplement chorizo ");
            ingredients.add("chorizo");

        }
        if (v.getId() == R.id.anchois) {
            System.out.println("je choisie un suplement une anchois");
            ingredients.add("anchois");
        }
        if (v.getId() == R.id.artichaut) {
            System.out.println("je choisie un suplement une artichaut");
            ingredients.add("artichaut");

        }
        if (v.getId() == R.id.buttonchampi) {
            System.out.println("je choisie un suplement une champi");
            ingredients.add("champi");
        }
        if (v.getId() == R.id.Mozarella) {
            System.out.println("je choisie un suplement Mozza");
            ingredients.add("Mozarella");
        }
        if (v.getId() == R.id.reset) {
            pizza.commande(getIngredient());
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.remove(MainActivity.fragIngredient);
            transaction.add(R.id.fragmentPersonnalisation,MainActivity.fragPizza);
            transaction.commit();

        }
    }

    private String getIngredient() {
        String s =" ";
        for (int i = 0 ; i<ingredients.size();i++){
          if (i==0){
              s+= ingredients.get(i);
          }
          else{
              s+="+"+ingredients.get(i);
          }
        }
        return s;
    }
}