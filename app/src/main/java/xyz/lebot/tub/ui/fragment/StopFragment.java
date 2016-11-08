package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.StopModel;
import xyz.lebot.tub.ui.adapter.StopListAdapter;
import xyz.lebot.tub.ui.presenter.StopFragmentPresenter;

public class StopFragment extends Fragment {

    @BindView(R.id.fragment_stop_recycler_view)
    RecyclerView recyclerView;

    private StopListAdapter stopListAdapter;
    private StopFragmentPresenter presenter;

    public StopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stop, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        stopListAdapter = new StopListAdapter(this.getContext(), null);
        recyclerView.setAdapter(stopListAdapter);

        presenter = new StopFragmentPresenter(this);
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

    public void initList(List<StopModel> stopModels) {
        stopListAdapter.swap(stopModels);
    }

}
