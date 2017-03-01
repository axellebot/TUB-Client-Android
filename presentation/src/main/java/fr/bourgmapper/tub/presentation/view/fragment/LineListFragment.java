package fr.bourgmapper.tub.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.internal.di.components.CoreComponent;
import fr.bourgmapper.tub.presentation.listener.LineListListener;
import fr.bourgmapper.tub.presentation.model.LineModel;
import fr.bourgmapper.tub.presentation.presenter.LineListFragmentPresenter;
import fr.bourgmapper.tub.presentation.view.LineListView;
import fr.bourgmapper.tub.presentation.view.adapter.LineListAdapter;
import fr.bourgmapper.tub.presentation.view.adapter.LineListLayoutManager;

/**
 * Fragment that shows a list of Lines.
 */
public class LineListFragment extends BaseFragment implements LineListView {

    @Inject
    LineListFragmentPresenter lineListFragmentPresenter;

    @Inject
    LineListAdapter lineListAdapter;

    @BindView(R.id.list_line_recycler_view)
    RecyclerView rv_lines;

    private LineListListener lineListListener;
    private LineListAdapter.OnItemClickListener onItemClickListener =
            new LineListAdapter.OnItemClickListener() {
                @Override
                public void onLineItemClicked(LineModel lineModel) {
                    if (LineListFragment.this.lineListFragmentPresenter != null && lineModel != null) {
                        LineListFragment.this.lineListFragmentPresenter.onLineClicked(lineModel);
                    }
                }
            };

    public LineListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LineListListener) {
            this.lineListListener = (LineListListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_line_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupLineList();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lineListFragmentPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadLineList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.lineListFragmentPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.lineListFragmentPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_lines.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.lineListFragmentPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.lineListListener = null;
    }

    @Override
    public void showLoadingLineList() {

    }

    @Override
    public void hideLoadingLineList() {

    }

    @Override
    public void showRetryLineList() {

    }

    @Override
    public void hideRetryLineList() {

    }

    @Override
    public void renderLineList(Collection<LineModel> lineModelCollection) {
        if (lineModelCollection != null) {
            this.lineListAdapter.setLineCollection(lineModelCollection);
        }
    }

    @Override
    public void viewLine(LineModel lineModel) {
        if (this.lineListListener != null) {
            this.lineListListener.onLineClicked(lineModel);
        }
    }

    @Override
    public void showErrorLineList(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupLineList() {
        this.lineListAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_lines.setLayoutManager(new LineListLayoutManager(context()));
        this.rv_lines.setAdapter(lineListAdapter);
    }

    /**
     * Loads all lines.
     */
    private void loadLineList() {
        this.lineListFragmentPresenter.initialize();
    }

    //TODO : Bind retry Button
    void onButtonRetryClick() {
        LineListFragment.this.loadLineList();
    }

}