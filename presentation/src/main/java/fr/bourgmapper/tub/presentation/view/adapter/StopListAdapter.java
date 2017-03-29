package fr.bourgmapper.tub.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.presentation.R;
import fr.bourgmapper.tub.presentation.model.StopModel;

/**
 * Adaptar that manages a collection of {@link StopModel}.
 */
public class StopListAdapter extends RecyclerView.Adapter<StopListAdapter.StopViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<StopModel> stopCollection;
    private OnItemClickListener onItemClickListener;

    @Inject
    StopListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.stopCollection = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.stopCollection != null) ? this.stopCollection.size() : 0;
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_stop, parent, false);
        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StopViewHolder holder, final int position) {
        final StopModel stopModel = this.stopCollection.get(position);
        if (holder instanceof StopViewHolder) {
            StopViewHolder stopLineHolder = (StopViewHolder) holder;
            stopLineHolder.tvLabel.setText(stopModel.getLabel());
            stopLineHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StopListAdapter.this.onItemClickListener != null) {
                        StopListAdapter.this.onItemClickListener.onStopItemClicked(stopModel);
                    }
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setStopCollection(Collection<StopModel> stopCollection) {
        this.validateStopsCollection(stopCollection);
        this.stopCollection = (List<StopModel>) stopCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateStopsCollection(Collection<StopModel> stopCollection) {
        if (stopCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public interface OnItemClickListener {
        void onStopItemClicked(StopModel stopModel);
    }

    static class StopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_row_stop_label)
        TextView tvLabel;

        StopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
