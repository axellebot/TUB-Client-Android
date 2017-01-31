package fr.bourgmapper.tub.presentation.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.TubApp;
import fr.bourgmapper.tub.presentation.model.LineModel;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.ui.view.StopMapClusterItem;

/**
 * Created by axell on 06/11/2016.
 */

public class StopMapClusterItemInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private final View view;
    private final Context context;
    private final LayoutInflater layoutInflater;
    private boolean ready;
    @BindView(R.id.window_map_stop_info_title)
    TextView windowTitle;

    @BindView(R.id.window_map_stop_info_content)
    LinearLayoutCompat contentView;

    private StopMapClusterItem currentClusterItem;

    public StopMapClusterItemInfoWindowAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.view = this.layoutInflater.inflate(R.layout.window_map_stop_info, null);
        ButterKnife.bind(this, view);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        this.windowTitle.setText(currentClusterItem.getTitle());
        ready = false;
        TubApp.getInstance().getDataRepository().getLinesFromStop(this.currentClusterItem.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LineModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<LineModel> lineModels) {
                        addLineDetail(lineModels);
                    }
                });

        return view;
    }

    public void setCurrentClusterItem(StopMapClusterItem currentClusterItem) {
        this.currentClusterItem = currentClusterItem;
    }

    private void addLineDetail(List<LineModel> lineModels) {
        for (LineModel lineModel : lineModels) {
            View itemView = this.layoutInflater.inflate(R.layout.item_row_line_stop_detail, null);
            TextView tv = (TextView) itemView.findViewById(R.id.item_row_line_stop_detail_number);
            tv.setText(lineModel.getNumber());

            int color = Color.parseColor(lineModel.getColor());

            GradientDrawable bgShape = (GradientDrawable) tv.getBackground();
            bgShape.setColor(color);
            this.contentView.addView(itemView);
        }
    }
}