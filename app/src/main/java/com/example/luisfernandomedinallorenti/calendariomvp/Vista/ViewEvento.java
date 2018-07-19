package com.example.luisfernandomedinallorenti.calendariomvp.Vista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luisfernandomedinallorenti.calendariomvp.R;
import com.example.luisfernandomedinallorenti.calendariomvp.datos.Data;
import com.example.luisfernandomedinallorenti.calendariomvp.modelos.Evento;

import java.util.ArrayList;

public class ViewEvento extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    //private ArrayList<String> arrayList;
    private Data data;
    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_evento);

        evento=new Evento();
        listView=(ListView)findViewById(R.id.listEventos);
        //arrayList=new ArrayList<String>();
        //arrayList.clear();
        //listView.setOnItemLongClickListener(this);

        Bundle bundle=getIntent().getExtras();
        int dia=bundle.getInt("dayOfMonth");
        int mes=bundle.getInt("month");
        int anio=bundle.getInt("year");

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        String nombre,descripcion,fecha,hora;
        data=new Data(getApplicationContext());
        data.open();
        Cursor cursor=data.getAll();
        if (cursor.moveToFirst()){
            do {
                nombre=cursor.getString(0);
                descripcion=cursor.getString(1);
                fecha=cursor.getString(2);

                hora=cursor.getString(3);
                arrayAdapter.add(nombre+","+descripcion+","+fecha+","+hora);
            }while (cursor.moveToNext());
            listView.setAdapter(arrayAdapter);
        }else {
            Toast.makeText(getApplication(),"No hay datos",Toast.LENGTH_SHORT).show();
            this.finish();
        }
        data.close();

    }
}
