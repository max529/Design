package ch.hevs.design.data.endPoint;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.LoadingActivity;
import ch.hevs.design.backend.vinApi.VinApi;
import ch.hevs.design.backend.vinApi.model.Vin;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class VinEndPoint extends AsyncTask<Void, Void, List<Vin>> {
    private static VinApi vinApi = null;
    private static final String TAG = VinEndPoint.class.getName();
    private Vin vin;
    private LoadingActivity loadingActivity = null;

    public VinEndPoint(){}

    public VinEndPoint(Vin vin,LoadingActivity loadingActivity){
        this.vin = vin;
        this.loadingActivity = loadingActivity;
    }
    public VinEndPoint(Vin vin){
        this.vin = vin;
    }

    public VinEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Vin> doInBackground(Void... params) {

        if(vinApi == null){
            // Only do this once
            VinApi.Builder builder = new VinApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if you deploy on the cloud backend, use your app name
                    // such as https://<your-app-id>.appspot.com
                    .setRootUrl("https://androidendpoint-167114.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            vinApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert


            if(vin != null){
                Log.e("a",vin.toString());
                Log.i(TAG, "insert vin");
                vinApi.insert(vin).execute();

            }
            // and for instance return the list of all employees
            return vinApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, vinApi.getBaseUrl());
            Log.e(TAG, e.toString());
            return new ArrayList<Vin>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Vin> result){
        if(result != null) {
            db.CloudToSqlVin(result);
        }
        if(loadingActivity!=null){
            loadingActivity.vinOk = 1;
            loadingActivity.check();
        }
    }
}
