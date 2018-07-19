package com.example.luisfernandomedinallorenti.calendariomvp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisfernandomedinallorenti.calendariomvp.R;
import com.example.luisfernandomedinallorenti.calendariomvp.datos.Data;
import com.example.luisfernandomedinallorenti.calendariomvp.modelos.Evento;

public class AddEvento extends AppCompatActivity {
    private EditText nombreEvento,descripcionEvento,fechaEvento;
    private Button guardar;
    public Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evento);

        nombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        descripcionEvento = (EditText) findViewById(R.id.editDescripcionEvento);
        fechaEvento = (EditText) findViewById(R.id.editFechaEvento);

        Bundle bundle = getIntent().getExtras();
        int dia = bundle.getInt("dayOfMonth");
        int mes = bundle.getInt("month");
        int anio = bundle.getInt("year");

        fechaEvento.setText(dia + "-" + mes + "-" + anio);

        guardar = (Button) findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick (View v){
                if (v.getId() == guardar.getId()) {
                    //Guardar datos Eventos
                    String nombre = nombreEvento.getText().toString();
                    String descripcion = descripcionEvento.getText().toString();
                    String fecha = fechaEvento.getText().toString();

                    Evento evento = new Evento(nombre, descripcion, fecha);
                    data = new Data(getApplicationContext());
                    data.open();
                    data.insertarEvento(evento);
                    Toast.makeText(getApplicationContext(), "Evento Agregado", Toast.LENGTH_LONG).show();
                    data.close();
                } else {
                    return;
                }
            }
        });
    }
}