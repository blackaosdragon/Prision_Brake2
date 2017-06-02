package com.example.black.gg.mx.prision_brake;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView localizacion, texto, sms;
    LocationManager gps;
    double lat, lon;
    int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        localizacion = (TextView) findViewById(R.id.localizacion);
        texto = (TextView) findViewById(R.id.texto);
        gps = (LocationManager) getSystemService(LOCATION_SERVICE);
        sms = (TextView) findViewById(R.id.sms);

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        }catch (Exception e){

            Log.e("--------Error----", ""+e.getMessage());

        }


    }

    @Override
    public void onLocationChanged(Location location) {


        Location local = new Location("localizacion 1");
        local.setLatitude(19.47368612);
        local.setLongitude(-99.04617997);
        /*Location local2 = new Location("localizacion 2");
        local2.setLatitude(location.getLatitude());
        local2.setLongitude(location.getLongitude());*/
        double distancia = local.distanceTo(location);
        texto.setText("Distancia: "+distancia);
        if (distancia>50.0){
            localizacion.setText("Sobrepasa los 50m");
            i++;
            sms.setText("Mensajes enviados: "+i);

        }
        else{
            localizacion.setText("Esta dentro de la zona segura");
        }


        //lat = location.getLatitude();
        //lon = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

        localizacion.setText("Gracias por encender la aplicacion");

    }

    @Override
    public void onProviderDisabled(String provider) {

        localizacion.setText("Prende tu GPS");

    }
}
