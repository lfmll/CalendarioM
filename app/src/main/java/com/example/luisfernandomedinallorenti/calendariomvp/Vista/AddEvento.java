package com.example.luisfernandomedinallorenti.calendariomvp.Vista;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.luisfernandomedinallorenti.calendariomvp.R;
import com.example.luisfernandomedinallorenti.calendariomvp.datos.Data;
import com.example.luisfernandomedinallorenti.calendariomvp.modelos.Evento;
import com.example.luisfernandomedinallorenti.calendariomvp.presentador.AlarmReceiver;

public class AddEvento extends AppCompatActivity {
    private EditText nombreEvento,descripcionEvento,fechaEvento;
    private Button guardar;
    private Switch sw;
    private TimePicker tm;
    public Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evento);

        nombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        descripcionEvento = (EditText) findViewById(R.id.editDescripcionEvento);
        fechaEvento = (EditText) findViewById(R.id.editFechaEvento);

        tm=(TimePicker)findViewById(R.id.tmHoraNotificacion);
        tm.setIs24HourView(true);
        sw=(Switch) findViewById(R.id.swNotificacion);

        Bundle bundle = getIntent().getExtras();
        int dia = bundle.getInt("dayOfMonth");
        int mes = bundle.getInt("month");
        int anio = bundle.getInt("year");

        fechaEvento.setText(dia + "-" + mes + "-" + anio);
        servicio();
        guardar = (Button) findViewById(R.id.btnGuardar);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tm.setVisibility(View.VISIBLE);
                }else {
                    tm.setVisibility(View.GONE);
                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (v.getId() == guardar.getId()) {
                    //Guardar datos Eventos
                    String nombre = nombreEvento.getText().toString();
                    String descripcion = descripcionEvento.getText().toString();
                    String fecha = fechaEvento.getText().toString();
                    String horas=tm.getCurrentHour().toString();
                    String minutos=tm.getCurrentMinute().toString();
                    String tiempo=horas+":"+minutos;
                    Evento evento = new Evento(nombre, descripcion, fecha,horas+":"+minutos);
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
    public void servicio(){
        Intent intent=new Intent(getApplicationContext(), AlarmReceiver.class);
        final PendingIntent pIntent=PendingIntent.getBroadcast(this,AlarmReceiver.REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis=System.currentTimeMillis();
        int intervalMillis=1+3+1000;//3 seg
        AlarmManager alarma=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarma.setInexactRepeating(AlarmManager.RTC_WAKEUP,firstMillis,intervalMillis,pIntent);
    }
}
