package com.example.matheus.appempresa;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadActivity extends AppCompatActivity {

    Button btSalvar;
    EditText ctNome, ctTipo, ctData, ctHora;
    Cursor cursor;
    String codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        ctNome = (EditText) findViewById(R.id.ctNome);
        ctTipo=(EditText) findViewById(R.id.ctTipo);
        ctData=(EditText) findViewById(R.id.ctData);
        ctHora=(EditText) findViewById(R.id.ctHora);
        btSalvar = (Button) findViewById(R.id.btSalvar);

        //saber oq fazer
        if (getIntent().getStringExtra("situacao").equals("alterar")){
            Controller controller= new Controller(getBaseContext());
            codigo=getIntent().getStringExtra("codigo");

            cursor= controller.buscaID(Integer.parseInt(codigo));

            ctNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            ctTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
            ctData.setText(cursor.getString(cursor.getColumnIndexOrThrow("data")));
            ctHora.setText(cursor.getString(cursor.getColumnIndexOrThrow("hora")));
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Controller c = new Controller(getBaseContext());
                //uma string resultado q recebe a mensagem que vem do metodo inserir
                String nome=String.valueOf(ctNome.getText());
                String tipo=String.valueOf(ctTipo.getText());
                String data=String.valueOf(ctData.getText());
                String hora=String.valueOf(ctHora.getText());

                String result;
                if (getIntent().getStringExtra("situacao").equals("inserir")){
                //chama o metodo e recebe o retorno
                result = c.inserir(nome,tipo,data,hora);
                }
                else{
                    result= c.alterar(Integer.parseInt(codigo),nome,tipo,data,hora);
                }

                //as variáveis foram feitas no parametro

                Toast.makeText(getBaseContext(),result,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(CadActivity.this, ListaActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
