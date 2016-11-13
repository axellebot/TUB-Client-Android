package xyz.lebot.tub.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

/**
 * Created by axell on 05/11/2016.
 */

public class StopDetailLineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<LineModel> lineModels;

    public StopDetailLineListAdapter(Context context, List<LineModel> lineModels) {
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
        View view = inflater.inflate(R.layout.item_row_line_stop_detail, parent, false);
        return new LineDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LineModel lineModel = lineModels.get(position);
        if (holder instanceof LineDetailHolder) {
            LineDetailHolder stopLineHolder = (LineDetailHolder) holder;
            TextView tvLabel = stopLineHolder.getTvNumber();
            tvLabel.setText(lineModel.getNumber());

            int color = Color.parseColor(lineModel.getColor());
            GradientDrawable bgShape = (GradientDrawable) tvLabel.getBackground();
            bgShape.setColor(color);
        }
    }

    public void swap(List<LineModel> lineModels) {
        this.lineModels.clear();
        this.lineModels.addAll(lineModels);
        notifyDataSetChanged();
    }

    public class LineDetailHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_row_line_stop_detail_number)
        TextView tvNumber;

        public LineDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getTvNumber() {
            return tvNumber;
        }
    }
}
