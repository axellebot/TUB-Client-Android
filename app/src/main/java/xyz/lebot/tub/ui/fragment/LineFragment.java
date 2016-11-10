package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.ui.adapter.LineGridAdapter;
import xyz.lebot.tub.ui.manager.GridAutofitLayoutManager;
import xyz.lebot.tub.ui.navigator.Navigator;
import xyz.lebot.tub.ui.presenter.LineFragmentPresenter;

public class LineFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.fragment_line_recycler_view)
    RecyclerView recyclerView;

    private Navigator navigator;
    private LineFragmentPresenter presenter;
    private LineGridAdapter lineGridAdapter;

    public LineFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.navigator = (Navigator) args.get("NAVIGATOR");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line, container, false);
        ButterKnife.bind(this, view);

        //Presenter
        this.presenter = new LineFragmentPresenter(this, navigator);

        //Adapter
        this.lineGridAdapter = new LineGridAdapter(this, presenter, null);

        //RcyclerView
        this.recyclerView.setAdapter(lineGridAdapter);
        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(this.getContext(), (int) getResources().getDimension(R.dimen.item_grid_line_size));
        this.recyclerView.setLayoutManager(layoutManager);

        presenter.initialize();
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
