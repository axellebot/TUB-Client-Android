package fr.bourgmapper.tub.presentation.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.presenter.LineListPresenter;
import fr.bourgmapper.tub.presentation.view.adapter.LineGridAdapter;
import fr.bourgmapper.tub.presentation.view.manager.GridAutofitLayoutManager;

/**
 * Fragment that shows a list of Lines.
 */
public class LineListFragment extends BaseFragment {

    @BindView(R.id.list_bus_recycler_view)
    RecyclerView recyclerView;

    private LineListPresenter presenter;
    private LineGridAdapter lineGridAdapter;

    public static LineListFragment newInstance() {
        LineListFragment fragment = new LineListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bus_list, container, false);
        ButterKnife.bind(this, view);

        //Presenter
        this.presenter = new LineListPresenter(this);

        //Adapter
        this.lineGridAdapter = new LineGridAdapter(this, presenter, null);

        //RecyclerView
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
