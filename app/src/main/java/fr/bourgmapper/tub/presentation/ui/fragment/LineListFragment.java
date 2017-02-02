package fr.bourgmapper.tub.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.presenter.LineListFragmentPresenter;
import fr.bourgmapper.tub.presentation.ui.adapter.LineGridAdapter;
import fr.bourgmapper.tub.presentation.ui.manager.GridAutofitLayoutManager;

public class LineListFragment extends Fragment {

    @BindView(R.id.fragment_line_recycler_view)
    RecyclerView recyclerView;

    private LineListFragmentPresenter presenter;
    private LineGridAdapter lineGridAdapter;

    public static LineListFragment newInstance() {
        LineListFragment fragment = new LineListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_list, container, false);
        ButterKnife.bind(this, view);

        //Presenter
        this.presenter = new LineListFragmentPresenter(this);

        //Adapter
        this.lineGridAdapter = new LineGridAdapter(this, presenter, null);

        //RcyclerView
        this.recyclerView.setAdapter(lineGridAdapter);
        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(this.getContext(), (int) getResources().getDimension(R.dimen.item_grid_line_size));
        this.recyclerView.setLayoutManager(layoutManager);

        presenter.start();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.resume();
    }

    public void initList(List<LineModel> lineModels) {
        lineGridAdapter.swap(lineModels);
    }
}
