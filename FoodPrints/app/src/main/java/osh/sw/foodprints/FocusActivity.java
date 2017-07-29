package osh.sw.foodprints;

import android.app.Activity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FocusActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    LatLng Dunsan = new LatLng(36.3555510, 127.3899450);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focus_view);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.addMarker(new MarkerOptions().position(Dunsan).title("ss"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dunsan, 17.0f));

    }
}
