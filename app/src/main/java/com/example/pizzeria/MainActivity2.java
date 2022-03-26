package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
         dropdown = findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.table));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(myAdapter);
        dropdown.setOnItemSelectedListener(this);
        Thread t = new Thread() {
            public void run() {
                try {
                    serveur s1 = new serveur(4000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();












}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
if(!dropdown.getSelectedItem().toString().equals("Déroulez moi !")){
            String table=dropdown.getSelectedItem().toString();
            System.out.println(table);
            Intent i1=new Intent(this,MainActivity.class);
            i1.putExtra("table",table);
            startActivity(i1);

    }}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}