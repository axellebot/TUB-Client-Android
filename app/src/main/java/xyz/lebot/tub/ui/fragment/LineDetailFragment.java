package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.ui.navigator.Navigator;
import xyz.lebot.tub.ui.presenter.LineDetailFragmentPresenter;

public class LineDetailFragment extends Fragment {

    private Navigator navigator;
    private LineDetailFragmentPresenter presenter;


    public LineDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void setArguments(Bundle args) {
        this.navigator = (Navigator) args.get("NAVIGATOR");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_detail, container, false);
        ButterKnife.bind(this, view);

        presenter = new LineDetailFragmentPresenter(this, navigator);
        presenter.initialize();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    public void initView(LineModel lineModel) {
        //lineGridAdapter.swap(lineModels);
    }
}

