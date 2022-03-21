package com.example.activity_firma_digital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.activity_firma_digital.Clases.Adaptador;
import com.example.activity_firma_digital.Clases.ConexionSQLite;
import com.example.activity_firma_digital.Clases.Firmas;

import java.util.ArrayList;
import java.util.List;

public class List_Firmas extends AppCompatActivity {
    List<Firmas> listaItem= new ArrayList<>();
    RecyclerView recyclerViewItem;
    ConexionSQLite sql;
    Adaptador adaptador;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_firmas);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Lista de firmas digitales");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sql=new ConexionSQLite(this);
        setContentView(R.layout.activity_list_firmas);
        listaItem=sql.getData();
        recyclerViewItem = findViewById(R.id.recycler);
        recyclerViewItem.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewItem.setLayoutManager(layoutManager);
        adaptador= new Adaptador(this, listaItem, recyclerViewItem);
        recyclerViewItem.setAdapter(adaptador);
    }
}