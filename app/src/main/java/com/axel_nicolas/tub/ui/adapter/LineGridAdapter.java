package com.axel_nicolas.tub.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.axel_nicolas.tub.R;
import com.axel_nicolas.tub.data.model.LineModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axell on 05/11/2016.
 */

public class LineGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<LineModel> lineModels;

    public LineGridAdapter(Context context, List<LineModel> lineModels) {
        this.inflater = LayoutInflater.from(context);
        if (lineModels == null) {
            this.lineModels = new ArrayList<>();
        } else {
            this.lineModels = lineModels;
        }
    }

    @Override
    public int getItemCount() {
        return lineModels.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_line_item, parent, false);
        return new LineGridHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LineModel lineModel = lineModels.get(position);
        if (holder instanceof LineGridHolder) {
            LineGridHolder lineGridHolder = (LineGridHolder) holder;
            TextView tvLabel = lineGridHolder.getTvLabel();

            tvLabel.setText(lineModel.getNumber());
            int color = Color.parseColor(lineModel.getColor().toString());
            tvLabel.getBackground().setTint(color);

        }
    }

    public void swap(List<LineModel> lineModels) {
        this.lineModels.clear();
        this.lineModels.addAll(lineModels);
        notifyDataSetChanged();
    }


    public class LineGridHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.grid_line_item_label)
        TextView tvLabel;

        @BindView(R.id.grid_line_item_card)
        CardView cardView;

        public LineGridHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getTvLabel() {
            return tvLabel;
        }

        public CardView getCardView() {
            return cardView;
        }
    }
}


