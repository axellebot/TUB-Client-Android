package xyz.lebot.tub.ui.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.data.model.LineModel;
import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.presenter.LineFragmentPresenter;

/**
 * Created by axell on 05/11/2016.
 */

public class LineGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private LineFragmentPresenter presenter;
    private List<LineModel> lineModels;

    public LineGridAdapter(LineFragment fragment, LineFragmentPresenter presenter, List<LineModel> lineModels) {
        this.inflater = LayoutInflater.from(fragment.getContext());
        this.presenter = presenter;
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
        View view = inflater.inflate(R.layout.item_grid_line, parent, false);
        return new LineGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LineModel lineModel = lineModels.get(position);
        if (holder instanceof LineGridViewHolder) {
            LineGridViewHolder lineGridViewHolder = (LineGridViewHolder) holder;
            TextView tvLabel = lineGridViewHolder.getTvLabel();

            tvLabel.setText(lineModel.getNumber());
            int color = Color.parseColor(lineModel.getColor());


            GradientDrawable bgShape = (GradientDrawable) tvLabel.getBackground();
            bgShape.setColor(color);

            //   tvLabel.getBackground().setTint(color);

            lineGridViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String lineId = lineModels.get(position).getId();
                    presenter.onLineItemClick(lineId);
                }
            });

        }
    }

    public void swap(List<LineModel> lineModels) {
        this.lineModels.clear();
        this.lineModels.addAll(lineModels);
        notifyDataSetChanged();
    }


    public class LineGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_grid_line_label)
        TextView tvLabel;

        @BindView(R.id.item_grid_line_card)
        CardView cardView;

        public LineGridViewHolder(View itemView) {
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


