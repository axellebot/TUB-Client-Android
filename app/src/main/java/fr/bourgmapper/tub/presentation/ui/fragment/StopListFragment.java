package fr.bourgmapper.tub.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.presenter.StopListFragmentPresenter;
import fr.bourgmapper.tub.presentation.ui.adapter.StopListAdapter;

public class StopListFragment extends Fragment {

    @BindView(R.id.fragment_stop_recycler_view)
    RecyclerView recyclerView;

    private StopListAdapter stopListAdapter;
    private StopListFragmentPresenter presenter;

    public static StopListFragment newInstance() {
        StopListFragment fragment = new StopListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stop_list, container, false);
        ButterKnife.bind(this, view);

        //Presenter
        presenter = new StopListFragmentPresenter(this);

        //Adapter
        stopListAdapter = new StopListAdapter(this.getContext(), null);

        //RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(stopListAdapter);

        presenter.start();
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
