package ch.hevs.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.hevs.design.R;
import ch.hevs.design.data.Mouvement;

/**
 * Created by maxim on 25.04.2017.
 */

public class MovementAdapter extends ArrayAdapter<Mouvement> {

    public MovementAdapter(Context context, List<Mouvement> mvts) {
        super(context, 0, mvts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_movement,parent, false);
        }

        MovementViewHolder viewHolder = (MovementViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MovementViewHolder();
            viewHolder.inOut = (TextView) convertView.findViewById(R.id.mvtInOut);
            viewHolder.wineName = (TextView) convertView.findViewById(R.id.mvtWineName);
            viewHolder.qte = (TextView) convertView.findViewById(R.id.mvtQte);
            convertView.setTag(viewHolder);
        }

        Mouvement mvt = getItem(position);

        if(mvt.isIn()) {
            viewHolder.inOut.setText("IN");
        }else{
            viewHolder.inOut.setText("OUT");
        }
        viewHolder.wineName.setText(mvt.getVin().toString());
        viewHolder.qte.setText(mvt.getQte()+"");



        return convertView;
    }

    private class MovementViewHolder{
        public TextView inOut;
        public TextView wineName;
        public TextView qte;
    }
}
