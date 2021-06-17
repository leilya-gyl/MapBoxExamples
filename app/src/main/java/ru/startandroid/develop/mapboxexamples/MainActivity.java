package ru.startandroid.develop.mapboxexamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";

    String polygonFeatureJson = "{" +
            "\"type\": \"Feature\"," +
            "\"properties\": {},"+
            "\"geometry\": {" +
            "  \"type\": \"Polygon\"," +
            "   \"coordinates\": [" +
            "  [" +
            "   [" +
            "       -20.7421875," +
            "       38.8225909761771" +
            "   ], " +
            "   [" +
            "       -22.8515625," +
            "       -36.03133177633187" +
            "   ], " +
            "   [" +
            "       51.328125," +
            "       -36.59789133070204" +
            "   ], " +
            "   [" +
            "       48.515625," +
            "       39.90973623453719" +
            "   ], " +
            "   [" +
            "       -20.7421875," +
            "       38.8225909761771" +
            "   ]" +
            "  ]" +
            " ]" +
            " }" +
            "}";

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.access_token));

        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        /*mapView.getMapAsync(mapboxMap -> mapboxMap.setStyle(Style.MAPBOX_STREETS,
                style -> {

            }));*/
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull  MapboxMap mapboxMap) {
        /*List<Feature> symbolLayerIconFeatureList = new ArrayList<>();
        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(-57.225365, -33.213144)));
        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(-54.14164, -33.981818)));
        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(-56.990533, -30.583266)));

        mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")
                .withImage(ICON_ID, BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.mapbox_marker_icon_default))
                .withSource(new GeoJsonSource(SOURCE_ID, FeatureCollection.fromFeatures(symbolLayerIconFeatureList)))
                .withLayer(new SymbolLayer(LAYER_ID, SOURCE_ID)
                        .withProperties(
                                iconImage(ICON_ID),
                                iconAllowOverlap(true),
                                iconIgnorePlacement(true)
                        )
                ), new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

            }
        });*/
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull  Style style) {
                style.addSource(new GeoJsonSource(SOURCE_ID,
                        (Polygon)(Feature.fromJson(polygonFeatureJson)).geometry()));
                style.addLayerBelow(new FillLayer(LAYER_ID, SOURCE_ID)
                        .withProperties(fillColor(Color.parseColor("#3bb2d0"))),
                        "settlement-label"
                );
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}