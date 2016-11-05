package com.axel_nicolas.tub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axel_nicolas.tub.R;
import com.axel_nicolas.tub.data.model.LineModel;
import com.axel_nicolas.tub.ui.adapter.LineGridAdapter;
import com.axel_nicolas.tub.ui.manager.GridAutofitLayoutManager;
import com.axel_nicolas.tub.ui.presenter.LineFragmentPresenter;
import com.axel_nicolas.tub.ui.presenter.MapFragmentPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    @BindView(R.id.fragment_map_map_view)
    MapView mMapView;

    private GoogleMap googleMap;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);

        mMapView.getMapAsync(this);
        mMapView.setBackgroundColor(getResources().getColor(R.color.grey));

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap=googleMap;

        new MapFragmentPresenter(this);
    }
}
