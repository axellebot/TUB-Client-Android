package com.axel_nicolas.tub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.axel_nicolas.tub.adapters.LigneListAdapter;

import java.util.ArrayList;

public class LigneActivity extends AppCompatActivity {

    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne);


        ArrayList<String> lignes = new ArrayList<String>();
        lignes.add("1");
        lignes.add("2");
        lignes.add("3");
        lignes.add("4");
        lignes.add("5");
        lignes.add("6");


        gv=(GridView) findViewById(R.id.gridview_ligne);
        gv.setAdapter(new LigneListAdapter(this,lignes ));


    }
}
