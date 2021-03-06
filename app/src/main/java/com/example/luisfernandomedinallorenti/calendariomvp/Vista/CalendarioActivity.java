package com.example.luisfernandomedinallorenti.calendariomvp.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.example.luisfernandomedinallorenti.calendariomvp.R;

public class CalendarioActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener{
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        calendarView=(CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, final int year, final int month, final int dayOfMonth) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        CharSequence []items=new CharSequence[3];
        items[0]="Agregar Eventos";
        items[1]="Ver Eventos";
        items[2]="Cancelar";

        builder.setTitle("seleccione una Tarea")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            Intent intent=new Intent(getApplication(),AddEvento.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("dayOfMonth",dayOfMonth);
                            bundle.putInt("month",month+1);
                            bundle.putInt("year",year);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else if (which==1){
                            //Ver Eventos
                            Intent intent=new Intent(getApplication(),ViewEvento.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("dayOfMonth",dayOfMonth);
                            bundle.putInt("month",month+1);
                            bundle.putInt("year",year);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else {
                            return;
                        }
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
