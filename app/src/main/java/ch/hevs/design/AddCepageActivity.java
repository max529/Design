package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import ch.hevs.design.data.Cepage;

public class AddCepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cepage);
        this.setTitle(R.string.add_cepage);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cepage, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.navBtnValidateAddCepage){
            valideCepage();
        }
        return true;
    }

    private void valideCepage(){
        String name = ((EditText)findViewById(R.id.addNameCepage)).getText().toString();
        Log.e("ImplementDB","Implementation de l'ajout de cepage");
        Intent i = new Intent();
        i.putExtra("res",new Cepage(name));
        setResult(RESULT_OK,i);
        finish();
    }
}
