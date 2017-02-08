package fr.bourgmapper.tub.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.presenter.InfoFragmentPresenter;
import fr.bourgmapper.tub.presentation.ui.activity.MainActivity;

/**
 * Created by axell on 01/02/2017.
 */

public class InfoFragment extends Fragment {


    private LayoutInflater inflater;
    private InfoFragmentPresenter presenter;

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        presenter = new InfoFragmentPresenter(this, (MainActivity) getActivity());
        presenter.start();
        return view;
    }

    @OnClick({R.id.fragment_info_top_bar_bus, R.id.fragment_info_top_bar_stops})
    public void onTopBarItemSelected(Button button) {
        switch (button.getId()) {
            case R.id.fragment_info_top_bar_bus:
                presenter.topBarBusClicked();
                break;
            case R.id.fragment_info_top_bar_stops:
                presenter.topBarStopsClicked();
                break;
        }
    }
}
