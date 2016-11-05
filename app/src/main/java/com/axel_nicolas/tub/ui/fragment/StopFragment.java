package com.axel_nicolas.tub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axel_nicolas.tub.R;
import com.axel_nicolas.tub.data.model.StopModel;
import com.axel_nicolas.tub.ui.adapter.StopListAdapter;
import com.axel_nicolas.tub.ui.presenter.StopFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

public class StopFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.fragment_stop_recycler_view)
    RecyclerView recyclerView;

    private StopListAdapter stopListAdapter;

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

        new StopFragmentPresenter(this);

        return view;
    }

    public void initList(List<StopModel> stopModels) {
        stopListAdapter.swap(stopModels);
    }

}
