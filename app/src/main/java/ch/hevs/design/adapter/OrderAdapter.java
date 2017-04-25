package ch.hevs.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ch.hevs.design.R;
import ch.hevs.design.data.Command;
import ch.hevs.design.fragments.OrderFragment;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 24.04.2017.
 */

public class OrderAdapter extends ArrayAdapter<Command> {
    private OrderFragment of = null;
    public OrderAdapter(Context context, List<Command> commands, OrderFragment of) {
        super(context, 0, commands);
        this.of = of;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_order,parent, false);
        }

        OrderViewHolder viewHolder = (OrderViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new OrderViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.orderWineName);
            viewHolder.qte = (TextView) convertView.findViewById(R.id.orderWineQte);
            convertView.setTag(viewHolder);
        }

        final Command command = getItem(position);

        viewHolder.name.setText(command.getName());
        viewHolder.qte.setText(command.getQte()+"");

        Button b1 = (Button)convertView.findViewById(R.id.orderWineRecBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.receivedCommand(command.get_id());
                of.getHomeActivity().commands = db.getCommands();
                of.getHomeActivity().vins = db.getWines();
                of.updateList();
                of.getHomeActivity().bottomNavBar.updateFragment(0);
                //Toast.makeText(getContext(),"Command received",Toast.LENGTH_SHORT);
            }
        });

        Button b2 = (Button)convertView.findViewById(R.id.orderWineCancelBtn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteCommand(command.get_id());
                of.getHomeActivity().commands = db.getCommands();
                of.updateList();
            }
        });




        return convertView;
    }


    private class OrderViewHolder{
        public TextView name;
        public TextView qte;
        public Button recBtn;
    }
}
