package com.example.matheus.appempresa;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {

    ListView lista;
    Cursor cursor;//deve ser global pq vai servir para mts metodos
    String codigo;//para saber o codigo de qm vocÊ vai alterar
    AlertDialog alerta,confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //começar a preencher a lista
        //abrir conexão e é onde tem os metodos
        Controller c= new Controller(getBaseContext());
        //recebe a lista que recebe Cursor
        //
        cursor=c.lista();
        //quais campos virão da lista
        String[] campos=new String[]{"_id","nome","tipo", "data", "hora"};
        //pegar id das colunas 0-1-2,0-1-2...
        //
        int[]idViews=new int[]{R.id.id,R.id.nome,R.id.tipo,R.id.data,R.id.hora};//os do grid colunas
        //adaptar o cursor que é a classe que recebe do banco
        //passar contexto/forma adaptar/cursor é o conteudo mundo do banco de dados
        SimpleCursorAdapter ad=new SimpleCursorAdapter(getBaseContext(),R.layout.layout_lista,cursor,campos,idViews,0);

        lista=(ListView)findViewById(R.id.lwListaDados);
        lista.setAdapter(ad);

        //ativando o click da lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                cursor.moveToPosition(position);
                codigo=cursor.getString(cursor.getColumnIndexOrThrow("_id"));//para saber qual coluna alterar
                AlertDialog.Builder builder=new AlertDialog.Builder(ListaActivity.this);
                final AlertDialog.Builder confirmar=new AlertDialog.Builder(ListaActivity.this);
                confirmar.setMessage("Deseja realmente excluir?");
                builder.setMessage("o que deseja fazer?");
                builder.setPositiveButton("alterar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(ListaActivity.this,CadActivity.class);

                        i.putExtra("situacao","alterar");//manda o que fazer
                        i.putExtra("codigo",codigo);//manda o codigo q pegou no click
                        startActivity(i);
                        finish();
                    }
                });
                builder.setNegativeButton("excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmar.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Controller c = new Controller(getBaseContext());
                                String resultado;

                                resultado=c.apagar(Integer.parseInt(codigo));
                                //as variáveis foram feitas no parametro
                                Toast.makeText(getBaseContext(),resultado,Toast.LENGTH_SHORT).show();
                                //atualizar activity abaixo
                                finish();
                                startActivity(getIntent());

                            }
                        });
                        confirmar.setNegativeButton("não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        confirm=confirmar.create();
                        confirm.show();

                    }
                });
                alerta=builder.create();
                alerta.show();
            }
        });
    }

}
