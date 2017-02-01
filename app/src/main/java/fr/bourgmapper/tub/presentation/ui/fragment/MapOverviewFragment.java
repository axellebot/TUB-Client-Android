package fr.bourgmapper.tub.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.presenter.MapOverviewFragmentPresenter;

/**
 * Created by axell on 01/02/2017.
 */

public class MapOverviewFragment extends Fragment {


    private LayoutInflater inflater;
    private MapOverviewFragmentPresenter presenter;

    public static MapOverviewFragment newInstance() {
        MapOverviewFragment fragment = new MapOverviewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_map_overview, container, false);
        ButterKnife.bind(this, view);

        presenter = new MapOverviewFragmentPresenter(this);
        presenter.start();
        return view;
    }
}
