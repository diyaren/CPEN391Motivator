package com.firebase.uidemo.auth;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.firebase.uidemo.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * This shows how to change the camera position for the map.
 */
public class MapsActivity extends AppCompatActivity implements
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener,
        OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getName();

    /**
     * The amount by which to scroll the camera. Note that this amount is in raw pixels, not dp
     * (density-independent pixels).
     */
    private static final int SCROLL_BY_PX = 100;


    public static final CameraPosition VANCOUVER =
            new CameraPosition.Builder().target(new LatLng(49, -123))
                    .zoom(15.5f)
                    .bearing(0)
                    .tilt(25)
                    .build();

    private GoogleMap mMap;

    private CompoundButton mAnimateToggle;
    private CompoundButton mCustomDurationToggle;
    private SeekBar mCustomDurationBar;
    private PolylineOptions currPolylineOptions;
    private boolean isCanceled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mAnimateToggle = (CompoundButton) findViewById(R.id.animate);
        mCustomDurationToggle = (CompoundButton) findViewById(R.id.duration_toggle);
        mCustomDurationBar = (SeekBar) findViewById(R.id.duration_bar);

        updateEnabledState();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateEnabledState();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        mMap.setOnCameraIdleListener(this);
        mMap.setOnCameraMoveStartedListener(this);
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraMoveCanceledListener(this);

        // We will provide our own zoom controls.
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(49, -123), 10));
    }

    /**
     * When the map is not ready the CameraUpdateFactory cannot be used. This should be called on
     * all entry points that call methods on the Google Maps API.
     */
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.vancouver_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Called when the Go To Bondi button is clicked.
     */
    public void Snapshot(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.newCameraPosition(VANCOUVER));
    }




    public void SetMyLocation(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(49.26,-123.25),15));


        final LatLng PERTH = new LatLng(49.262217, -123.250080);
        mMap.addMarker(new MarkerOptions()
                               .position(PERTH)
                               .draggable(true));


    }

    /**
     * Called when the stop button is clicked.
     */
    public void onStopAnimation(View view) {
        if (!checkReady()) {
            return;
        }

        mMap.stopAnimation();
    }

    /**
     * Called when the zoom in button (the one with the +) is clicked.
     */
    public void onZoomIn(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.zoomIn());
    }

    /**
     * Called when the zoom out button (the one with the -) is clicked.
     */
    public void onZoomOut(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.zoomOut());
    }

    /**
     * Called when the tilt more button (the one with the /) is clicked.
     */
    public void onTiltMore(View view) {
        if (!checkReady()) {
            return;
        }

        CameraPosition currentCameraPosition = mMap.getCameraPosition();
        float currentTilt = currentCameraPosition.tilt;
        float newTilt = currentTilt + 10;

        newTilt = (newTilt > 90) ? 90 : newTilt;

        CameraPosition cameraPosition = new CameraPosition.Builder(currentCameraPosition)
                .tilt(newTilt).build();

        changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    /**
     * Called when the tilt less button (the one with the \) is clicked.
     */
    public void onTiltLess(View view) {
        if (!checkReady()) {
            return;
        }

        CameraPosition currentCameraPosition = mMap.getCameraPosition();

        float currentTilt = currentCameraPosition.tilt;

        float newTilt = currentTilt - 10;
        newTilt = (newTilt > 0) ? newTilt : 0;

        CameraPosition cameraPosition = new CameraPosition.Builder(currentCameraPosition)
                .tilt(newTilt).build();

        changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    /**
     * Called when the left arrow button is clicked. This causes the camera to move to the left
     */
    public void onScrollLeft(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.scrollBy(-SCROLL_BY_PX, 0));
    }

    /**
     * Called when the right arrow button is clicked. This causes the camera to move to the right.
     */
    public void onScrollRight(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.scrollBy(SCROLL_BY_PX, 0));
    }

    /**
     * Called when the up arrow button is clicked. The causes the camera to move up.
     */
    public void onScrollUp(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.scrollBy(0, -SCROLL_BY_PX));
    }

    /**
     * Called when the down arrow button is clicked. This causes the camera to move down.
     */
    public void onScrollDown(View view) {
        if (!checkReady()) {
            return;
        }

        changeCamera(CameraUpdateFactory.scrollBy(0, SCROLL_BY_PX));
    }

    public void NormalButton(View view) {
        if (!checkReady()) {
            return;
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void HybridButton(View view) {
        if (!checkReady()) {
            return;
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }


    public void SatelliteButton(View view) {
        if (!checkReady()) {
            return;
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }


    public void TerrainButton(View view) {
        if (!checkReady()) {
            return;
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }





    /**
     * Called when the animate button is toggled
     */
    public void onToggleAnimate(View view) {
        updateEnabledState();
    }

    /**
     * Called when the custom duration checkbox is toggled
     */
    public void onToggleCustomDuration(View view) {
        updateEnabledState();
    }

    /**
     * Update the enabled state of the custom duration controls.
     */
    private void updateEnabledState() {
        mCustomDurationToggle.setEnabled(mAnimateToggle.isChecked());
        mCustomDurationBar
                .setEnabled(mAnimateToggle.isChecked() && mCustomDurationToggle.isChecked());
    }

    private void changeCamera(CameraUpdate update) {
        changeCamera(update, null);
    }

    /**
     * Change the camera position by moving or animating the camera depending on the state of the
     * animate toggle button.
     */
    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {
        if (mAnimateToggle.isChecked()) {
            if (mCustomDurationToggle.isChecked()) {
                int duration = mCustomDurationBar.getProgress();
                // The duration must be strictly positive so we make it at least 1.
                mMap.animateCamera(update, Math.max(duration, 1), callback);
            } else {
                mMap.animateCamera(update, callback);
            }
        } else {
            mMap.moveCamera(update);
        }
    }

    @Override
    public void onCameraMoveStarted(int reason) {
        if (!isCanceled) {
            mMap.clear();
        }

        String reasonText = "UNKNOWN_REASON";
        currPolylineOptions = new PolylineOptions().width(5);
        switch (reason) {
            case GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE:
                currPolylineOptions.color(Color.BLUE);
                reasonText = "GESTURE";
                break;
            case GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION:
                currPolylineOptions.color(Color.RED);
                reasonText = "API_ANIMATION";
                break;
            case GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION:
                currPolylineOptions.color(Color.GREEN);
                reasonText = "DEVELOPER_ANIMATION";
                break;
        }
        Log.i(TAG, "onCameraMoveStarted(" + reasonText + ")");
        addCameraTargetToPath();
    }

    @Override
    public void onCameraMove() {
        // When the camera is moving, add its target to the current path we'll draw on the map.
        if (currPolylineOptions != null) {
            addCameraTargetToPath();
        }
        Log.i(TAG, "onCameraMove");
    }

    @Override
    public void onCameraMoveCanceled() {
        // When the camera stops moving, add its target to the current path, and draw it on the map.
        if (currPolylineOptions != null) {
            addCameraTargetToPath();
            mMap.addPolyline(currPolylineOptions);
        }
        isCanceled = true;  // Set to clear the map when dragging starts again.
        currPolylineOptions = null;
        Log.i(TAG, "onCameraMoveCancelled");
    }

    @Override
    public void onCameraIdle() {
        if (currPolylineOptions != null) {
            addCameraTargetToPath();
            mMap.addPolyline(currPolylineOptions);
        }
        currPolylineOptions = null;
        isCanceled = false;  // Set to *not* clear the map when dragging starts again.
        Log.i(TAG, "onCameraIdle");
    }

    private void addCameraTargetToPath() {
        LatLng target = mMap.getCameraPosition().target;
        // currPolylineOptions.add(target);
    }
}