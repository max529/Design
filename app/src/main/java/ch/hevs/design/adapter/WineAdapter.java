package ch.hevs.design.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.hevs.design.R;
import ch.hevs.design.data.Vin;

/**
 * Created by maxim on 17.04.2017.
 */

public class WineAdapter extends ArrayAdapter<Vin> {
    public WineAdapter(Context context, List<Vin> vins) {
        super(context, 0, vins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_wine,parent, false);
        }

        WineViewHolder viewHolder = (WineViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new WineViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameWine);
            viewHolder.annee = (TextView) convertView.findViewById(R.id.anneeWine);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        Vin vin = getItem(position);

        viewHolder.name.setText(vin.getName());
        viewHolder.annee.setText(vin.getAnnee()+"");
        String pathImg = vin.getImg();
        if(pathImg==""){
            viewHolder.avatar.setImageResource(R.drawable.wine_default);
        }else{
            Bitmap bMap = BitmapFactory.decodeFile(vin.getImg());
            viewHolder.avatar.setImageBitmap(bMap);
        }


        return convertView;
    }


    private class WineViewHolder{
        public TextView name;
        public TextView annee;
        public ImageView avatar;
    }
}
