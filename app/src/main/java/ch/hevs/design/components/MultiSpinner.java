package ch.hevs.design.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.data.Cepage;

/**
 * Created by maxim on 18.04.2017.
 */

public class MultiSpinner extends android.support.v7.widget.AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener {

    private List<String> items;
    private List itemsObject = null;
    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;

    public MultiSpinner(Context context) {
        super(context);
    }

    public MultiSpinner(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public MultiSpinner(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }



    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (isChecked)
            selected[which] = true;
        else
            selected[which] = false;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        StringBuffer spinnerBuffer = new StringBuffer();
        boolean displayDefault = true;
        for (int i = 0; i < items.size(); i++) {
            if (selected[i] == true) {
                spinnerBuffer.append(items.get(i));
                spinnerBuffer.append(", ");
                displayDefault = false;
            }
        }
        String spinnerText;
        if (!displayDefault) {
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
        } else {
            spinnerText = defaultText;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[] { spinnerText });
        setAdapter(adapter);
        listener.onItemsSelected(selected);
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(
                items.toArray(new CharSequence[items.size()]), selected, this);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.setOnCancelListener(this);

        builder.show();
        return true;
    }





    public void setItems(List<String> items, String allText,
                         MultiSpinnerListener listener) {
        this.items = items;
        this.defaultText = allText;
        this.listener = listener;

        // all selected by default
        selected = new boolean[items.size()];
        for (int i = 0; i < selected.length; i++)
            selected[i] = false;

        // all text on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new String[] { allText });
        setAdapter(adapter);
    }
    public void setItems(Object[] items, String allText,
                         MultiSpinnerListener listener) {
        List<String> l = new ArrayList<String>();
        for (Object o : items) {
            l.add(o.toString());
        }
        setItems(l,allText,listener);
    }
    public void setItems(List items, String allText,
                         MultiSpinnerListener listener,boolean a) {
        this.itemsObject = items;
        List<String> l = new ArrayList<String>();
        for (Object o : items) {
            l.add(o.toString());
        }
        setItems(l,allText,listener);
    }
    public void setItemsTrue(List itemsTrue){
        for(Object o : itemsTrue){
            Cepage c = (Cepage)o;
            String temp = c.toString();
            int i = items.indexOf(temp);
            selected[i] = true;
        }

        StringBuffer spinnerBuffer = new StringBuffer();
        boolean displayDefault = true;
        for (int i = 0; i < items.size(); i++) {
            if (selected[i] == true) {
                spinnerBuffer.append(items.get(i));
                spinnerBuffer.append(", ");
                displayDefault = false;
            }
        }
        String spinnerText;
        if (!displayDefault) {
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
        } else {
            spinnerText = defaultText;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[] { spinnerText });
        setAdapter(adapter);
        listener.onItemsSelected(selected);
    }
    public List<Object> getSelectedItems(){
        List<Object> l = new ArrayList<Object>();
        for (int i = 0; i < items.size(); i++) {
            if (selected[i] == true) {
                l.add(itemsObject.get(i));
            }
        }
        return l;
    }


    public interface MultiSpinnerListener {
        public void onItemsSelected(boolean[] selected);
    }
}