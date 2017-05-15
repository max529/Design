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
import ch.hevs.design.backend.paysApi.model.Pays;
import ch.hevs.design.backend.paysApi.PaysApi;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class PaysEndPoint extends AsyncTask<Void, Void, List<Pays>> {
    private static PaysApi paysApi = null;
    private static final String TAG = PaysEndPoint.class.getName();
    private Pays pays;
    private LoadingActivity loadingActivity = null;

    public PaysEndPoint(){}

    public PaysEndPoint(Pays pays,LoadingActivity loadingActivity){
        this.pays = pays;
        this.loadingActivity = loadingActivity;
    }
    public PaysEndPoint(Pays pays){
        this.pays = pays;
    }

    public PaysEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Pays> doInBackground(Void... params) {

        if(paysApi == null){
            // Only do this once
            PaysApi.Builder builder = new PaysApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            paysApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(pays != null){
                paysApi.insert(pays).execute();
                Log.i(TAG, "insert pays");
            }
            // and for instance return the list of all employees
            return paysApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Pays>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Pays> result){
        if(result != null) {
            db.CloudToSqlCountry(result);
        }
        if(loadingActivity!=null){
            loadingActivity.paysOk = 1;
            loadingActivity.check();
        }
    }
}
