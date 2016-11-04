package com.axel_nicolas.tub.ui.adapters;

import com.axel_nicolas.tub.ui.activity.LigneActivity;
import com.axel_nicolas.tub.R;
/**
 * Created by iem on 02/11/2016.
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LigneListAdapter extends BaseAdapter{

    ArrayList<String> lignes;
    Context context;

    private static LayoutInflater inflater=null;


    public LigneListAdapter(LigneActivity ligneActivity, ArrayList<String> lignes) {
        // TODO Auto-generated constructor stub

        context=ligneActivity;

        this.lignes=lignes;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lignes.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView numero;
        TextView depart;
        TextView arrive;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_ligne_item, null);
        holder.numero=(TextView) rowView.findViewById(R.id.numero_ligne);
        holder.depart=(TextView) rowView.findViewById(R.id.depart_textView);
        holder.depart=(TextView) rowView.findViewById(R.id.arrivve_textView);

        holder.numero.setText(lignes.get(position));

        GradientDrawable bgShape;
        switch (lignes.get(position)){
            case "1":
                bgShape = (GradientDrawable) holder.numero.getBackground();
                bgShape.setColor(context.getResources().getColor(R.color.ligne1_color));

                break;
            case "2":
                bgShape = (GradientDrawable) holder.numero.getBackground();
                bgShape.setColor(context.getResources().getColor(R.color.ligne2_color));
                break;
            case "3":
                bgShape = (GradientDrawable) holder.numero.getBackground();
                bgShape.setColor(context.getResources().getColor(R.color.ligne3_color));

                break;
            case "4":
                bgShape = (GradientDrawable) holder.numero.getBackground();
                bgShape.setColor(context.getResources().getColor(R.color.ligne4_color));

                break;
            case "5":
                bgShape = (GradientDrawable) holder.numero.getBackground();
                bgShape.setColor(context.getResources().getColor(R.color.ligne5_color));

                break;

        }
        //holder.numero.setText(result[position]);
        //holder.numero.setText(result[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+position, Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}