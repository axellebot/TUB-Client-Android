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
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.model.LineModel;

/**
 * Adaptar that manages a collection of {@link LineModel}.
 */
public class LineListAdapter extends RecyclerView.Adapter<LineListAdapter.LineViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<LineModel> lineCollection;
    private OnItemClickListener onItemClickListener;

    @Inject
    LineListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.lineCollection = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.lineCollection != null) ? this.lineCollection.size() : 0;
    }

    @Override
    public LineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_line, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LineViewHolder holder, final int position) {
        final LineModel LineModel = this.lineCollection.get(position);
        if (holder instanceof LineViewHolder) {
            LineViewHolder LineLineHolder = (LineViewHolder) holder;
            LineLineHolder.tvLabel.setText(LineModel.getLabel());
            LineLineHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (LineListAdapter.this.onItemClickListener != null) {
                        LineListAdapter.this.onItemClickListener.onLineItemClicked(LineModel);
                    }
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setLineCollection(Collection<LineModel> LineCollection) {
        this.validateLinesCollection(LineCollection);
        this.lineCollection = (List<LineModel>) LineCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateLinesCollection(Collection<LineModel> LineCollection) {
        if (LineCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public interface OnItemClickListener {
        void onLineItemClicked(LineModel LineModel);
    }

    static class LineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_row_line_label)
        TextView tvLabel;

        LineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
