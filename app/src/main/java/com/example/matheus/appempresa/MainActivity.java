package com.example.matheus.appempresa;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btCadastro,btListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCadastro=(Button)findViewById(R.id.btCad);
        btListar=(Button) findViewById(R.id.btListar);

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListaActivity.class);

                startActivity(intent);
            }
        });
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CadActivity.class);
                intent.putExtra("situacao","inserir");//diz o que fazer se não é um alterar
                startActivity(intent);
            }
        });
    }
}
