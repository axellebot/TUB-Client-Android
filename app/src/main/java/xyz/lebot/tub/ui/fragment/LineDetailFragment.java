package xyz.lebot.tub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.lebot.tub.R;

/**
 * Created by axellebot on 08/11/2016.
 */

public class LineDetailFragment extends Fragment {

    public LineDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_detail,container,false);
        return view;
    }
}
