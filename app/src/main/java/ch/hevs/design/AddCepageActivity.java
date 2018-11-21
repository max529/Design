package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import ch.hevs.design.data.Cepage;

import static ch.hevs.design.HomeActivity.db;

public class AddCepageActivity extends AppCompatActivity {

    /**
     * this is a comment to test project redmine*/

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
        }else if(item.getItemId() == android.R.id.home){
            Log.e("finis","fi");
            this.onBackPressed();
            return true;
        }
        return true;
    }

    private void valideCepage(){
        String name = ((EditText)findViewById(R.id.addNameCepage)).getText().toString();
        if(name.length()==0){
            Toast.makeText(AddCepageActivity.this, R.string.error_cepage_name,Toast.LENGTH_SHORT).show();
            return;
        }
        db.insertCepage(name);
        Intent i = new Intent();
        i.putExtra("res",new Cepage(name));
        setResult(RESULT_OK,i);
        finish();
    }
}
