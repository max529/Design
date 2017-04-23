package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import static ch.hevs.design.HomeActivity.db;

public class AddProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);
        this.setTitle(getString(R.string.add_provider));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_provider, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.navBtnValidateAddProvider){
            valideProvider();
        }else if(item.getItemId() == android.R.id.home){
            Log.e("finis","fi");
            this.onBackPressed();
            return true;
        }
        return true;
    }

    private void valideProvider() {
        String name = ((EditText)findViewById(R.id.addNameProvider)).getText().toString();
        String surname = ((EditText)findViewById(R.id.addSurnameProvider)).getText().toString();
        String address = ((EditText)findViewById(R.id.addAdressProvider)).getText().toString();
        String email = ((EditText)findViewById(R.id.addEmailProvider)).getText().toString();

        db.insertProvider(name,surname,address,email);

        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
    }
}
