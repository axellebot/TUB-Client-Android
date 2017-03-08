package fr.bourgmapper.tub.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.internal.di.HasComponent;
import fr.bourgmapper.tub.presentation.internal.di.components.CoreComponent;
import fr.bourgmapper.tub.presentation.internal.di.components.DaggerCoreComponent;
import fr.bourgmapper.tub.presentation.listener.StopListListener;
import fr.bourgmapper.tub.presentation.model.StopModel;
import fr.bourgmapper.tub.presentation.presenter.StopListFragmentPresenter;
import fr.bourgmapper.tub.presentation.view.StopListView;
import fr.bourgmapper.tub.presentation.view.adapter.SimpleDividerItemDecoration;
import fr.bourgmapper.tub.presentation.view.adapter.StopListAdapter;
import fr.bourgmapper.tub.presentation.view.adapter.StopListLayoutManager;

/**
 * Fragment that shows a list of Stops.
 */
public class StopListFragment extends BaseFragment implements StopListView, HasComponent<CoreComponent> {

    @Inject
    StopListFragmentPresenter stopListFragmentPresenter;

    @Inject
    StopListAdapter stopListAdapter;

    @BindView(R.id.list_stop_recycler_view)
    RecyclerView list_stop;

    @BindView(R.id.list_stop_progress_bar)
    View list_stop_progress_bar;

    @BindView(R.id.list_stop_retry_btn)
    View list_stop_retry_button;

    private CoreComponent coreComponent;

    private StopListListener stopListListener;
    private StopListAdapter.OnItemClickListener onItemClickListener =
            new StopListAdapter.OnItemClickListener() {
                @Override
                public void onStopItemClicked(StopModel stopModel) {
                    if (StopListFragment.this.stopListFragmentPresenter != null && stopModel != null) {
                        StopListFragment.this.stopListFragmentPresenter.onStopClicked(stopModel);
                    }
                }
            };

    public StopListFragment() {
        setRetainInstance(true);
    }

    public static StopListFragment newInstance() {

        Bundle args = new Bundle();

        StopListFragment fragment = new StopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof StopListListener) {
            this.stopListListener = (StopListListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializeInjector();
        this.coreComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_stop_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupStopList();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.stopListFragmentPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadStopList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.stopListFragmentPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.stopListFragmentPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list_stop.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stopListFragmentPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.stopListListener = null;
    }

    @Override
    public void showLoadingStopList() {
        this.list_stop_progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingStopList() {
        this.list_stop_progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void showRetryStopList() {
        this.list_stop_retry_button.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetryStopList() {
        this.list_stop_retry_button.setVisibility(View.GONE);
    }

    @Override
    public void renderStopList(Collection<StopModel> stopModelCollection) {
        if (stopModelCollection != null) {
            this.stopListAdapter.setStopCollection(stopModelCollection);
        }
    }

    @Override
    public void viewStop(StopModel stopModel) {
        if (this.stopListListener != null) {
            this.stopListListener.onStopClicked(stopModel);
        }
    }

    @Override
    public void showErrorStopList(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupStopList() {
        this.stopListAdapter.setOnItemClickListener(onItemClickListener);
        this.list_stop.setLayoutManager(new StopListLayoutManager(context()));
        this.list_stop.setAdapter(stopListAdapter);
        this.list_stop.addItemDecoration(new SimpleDividerItemDecoration(context()));
    }

    /**
     * Loads all stops.
     */
    private void loadStopList() {
        this.stopListFragmentPresenter.initialize();
    }

    @OnClick(R.id.list_stop_retry_btn)
    void onButtonRetryClick() {
        StopListFragment.this.loadStopList();
    }

    private void initializeInjector() {
        this.coreComponent = DaggerCoreComponent.builder()
                .applicationComponent(getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public CoreComponent getComponent() {
        return coreComponent;
    }

}
