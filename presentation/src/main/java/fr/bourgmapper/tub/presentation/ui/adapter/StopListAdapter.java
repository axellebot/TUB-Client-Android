package fr.bourgmapper.tub.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Created by axell on 05/11/2016.
 */

public class StopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<StopModel> stopModels;

    public StopListAdapter(Context context, List<StopModel> stopModels) {
        this.inflater = LayoutInflater.from(context);
        if (stopModels == null) {
            this.stopModels = new ArrayList<>();
        } else {
            this.stopModels = stopModels;
        }
    }

    @Override
    public int getItemCount() {
        return stopModels.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_stop, parent, false);
        return new StopLineHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StopModel stopModel = stopModels.get(position);
        if (holder instanceof StopLineHolder) {
            StopLineHolder stopLineHolder = (StopLineHolder) holder;
            TextView tvLabel = stopLineHolder.getTvLabel();
            tvLabel.setText(stopModel.getLabel());
        }
    }

    public void swap(List<StopModel> stopModels) {
        this.stopModels.clear();
        this.stopModels.addAll(stopModels);
        notifyDataSetChanged();
    }

    public class StopLineHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_row_stop_label)
        TextView tvLabel;

        @BindView(R.id.item_row_stop)
        View itemContainer;

        public StopLineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getTvLabel() {
            return tvLabel;
        }

    }
}
