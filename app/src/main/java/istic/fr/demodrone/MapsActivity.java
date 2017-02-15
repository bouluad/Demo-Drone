package istic.fr.demodrone;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private LatLng yaounde = new LatLng(48.1224855, -1.6285135);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        getRetrofitObject();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(48.1224855, -1.6285135);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mode d'affichage de la carte
        mMap.setTrafficEnabled(true);
        //on autorise l'api à afficher le bouton pour accéder à notre position courante
        // mMap.setMyLocationEnabled(true);

        //définition du marqueur qui va se positionner sur le point qu'on désire afficher
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("YAOUNDE");
        markerOptions.visible(true);
        markerOptions.position(yaounde);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        //ajout du marqueur sur la carte
        mMap.addMarker(markerOptions);
        //zoom de la caméra sur la position qu'on désire afficher
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yaounde, 15));
        //animation le zoom toute les 2000ms
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    void getRetrofitObject() {

        WebServerIntf service = RetrofitBuilder.getSimpleClient();

        Call<PositionDrone> call = (Call<PositionDrone>) service.getLastPosition();

        call.enqueue(new Callback<PositionDrone>() {
            @Override
            public void onResponse(Call<PositionDrone> call, Response<PositionDrone> response) {

                try {

                   LatLng sydney = new LatLng(response.body().getPosition().get(0), response.body().getPosition().get(1));

                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(sydney).title("fsdlfgls"));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PositionDrone> call, Throwable t) {
                Log.d("onFailure", t.toString());

            }

        });
    }

}
