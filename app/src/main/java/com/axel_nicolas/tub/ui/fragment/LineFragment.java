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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.fragment_line_recycler_view)
    RecyclerView recyclerView;

    private LineFragmentPresenter presenter;
    private LineGridAdapter lineGridAdapter;

    public LineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line, container, false);
        ButterKnife.bind(this, view);

        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(this.getContext(), (int) getResources().getDimension(R.dimen.item_grid_line_size));
        recyclerView.setLayoutManager(layoutManager);

        lineGridAdapter = new LineGridAdapter(this.getContext(), null);
        recyclerView.setAdapter(lineGridAdapter);

        presenter = new LineFragmentPresenter(this);
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

    public void initList(List<LineModel> lineModels) {
        lineGridAdapter.swap(lineModels);
    }
}
