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

import ch.hevs.design.HomeActivity;
import ch.hevs.design.LoadingActivity;
import ch.hevs.design.backend.couleurApi.CouleurApi;
import ch.hevs.design.backend.couleurApi.model.Couleur;
import ch.hevs.design.data.converter.CouleurConverter;

/**
 * Created by maxim on 02.05.2017.
 */

public class CouleurEndPoint extends AsyncTask<Void, Void, List<Couleur>> {
    private static CouleurApi couleurApi = null;
    private static final String TAG = CouleurEndPoint.class.getName();
    private Couleur couleur;
    private LoadingActivity loadingActivity = null;

    public CouleurEndPoint(){}

    public CouleurEndPoint(Couleur couleur,LoadingActivity loadingActivity){
        this.couleur = couleur;
        this.loadingActivity = loadingActivity;
    }
    public CouleurEndPoint(Couleur couleur){
        this.couleur = couleur;
    }

    public CouleurEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Couleur> doInBackground(Void... params) {

        if(couleurApi == null){
            // Only do this once
            CouleurApi.Builder builder = new CouleurApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            couleurApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(couleur != null){
                couleurApi.insert(couleur).execute();
                Log.i(TAG, "insert cepage");
            }
            // and for instance return the list of all employees
            return couleurApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Couleur>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Couleur> result){
        if(result != null) {
            HomeActivity.colors.clear();
            List<ch.hevs.design.data.Couleur> res = new ArrayList<>();
            for (ch.hevs.design.backend.couleurApi.model.Couleur t: result) {
                res.add(CouleurConverter.CloudToLocal(t));
            }
            HomeActivity.colors = res;
        }
        if(loadingActivity!=null){
            loadingActivity.couleurOk = 1;
            loadingActivity.check();
        }
    }
}
