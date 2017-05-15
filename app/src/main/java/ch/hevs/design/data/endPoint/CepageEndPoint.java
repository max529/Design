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
import ch.hevs.design.backend.cepageApi.CepageApi;
import ch.hevs.design.backend.cepageApi.model.Cepage;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class CepageEndPoint extends AsyncTask<Void, Void, List<Cepage>> {
    private static CepageApi cepageApi = null;
    private static final String TAG = CepageEndPoint.class.getName();
    private Cepage cepage;
    private LoadingActivity loadingActivity = null;

    public CepageEndPoint(){}

    public CepageEndPoint(Cepage cepage, LoadingActivity loadingActivity){
        this.cepage = cepage;
        this.loadingActivity = loadingActivity;
    }
    public CepageEndPoint(Cepage cepage){
        this.cepage = cepage;
    }

    public CepageEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Cepage> doInBackground(Void... params) {

        if(cepageApi == null){
            // Only do this once
            CepageApi.Builder builder = new CepageApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            cepageApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(cepage != null){
                cepageApi.insert(cepage).execute();
                Log.i(TAG, "insert cepage");
            }
            // and for instance return the list of all employees
            return cepageApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Cepage>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Cepage> result){
        if(result != null) {
            db.CloudToSqlCepages(result);
        }
        if(loadingActivity!=null){
            loadingActivity.cepageOk = 1;
            loadingActivity.check();
        }
    }
}
