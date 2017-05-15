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
import ch.hevs.design.backend.mouvementApi.MouvementApi;
import ch.hevs.design.backend.mouvementApi.model.Mouvement;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class MouvementEndPoint extends AsyncTask<Void, Void, List<Mouvement>> {
    private static MouvementApi mouvementApi = null;
    private static final String TAG = MouvementEndPoint.class.getName();
    private Mouvement mouvement;
    private LoadingActivity loadingActivity = null;
    private long idDelete = -1;

    public MouvementEndPoint(){}

    public MouvementEndPoint(long idDelete){
        this.idDelete = idDelete;
    }

    public MouvementEndPoint(Mouvement mouvement,LoadingActivity loadingActivity){
        this.mouvement = mouvement;
        this.loadingActivity = loadingActivity;
    }
    public MouvementEndPoint(Mouvement mouvement){
        this.mouvement = mouvement;
    }

    public MouvementEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Mouvement> doInBackground(Void... params) {

        if(mouvementApi == null){
            // Only do this once
            MouvementApi.Builder builder = new MouvementApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            mouvementApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(mouvement != null){
                mouvementApi.insert(mouvement).execute();
                Log.i(TAG, "insert mouvement");
            }else if(idDelete!=-1){
                mouvementApi.remove(idDelete).execute();
            }
            // and for instance return the list of all employees
            return mouvementApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Mouvement>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Mouvement> result){
        if(result != null) {
            db.CloudToSqlMouvement(result);
        }
        if(loadingActivity!=null){
            loadingActivity.mouvementOk = 1;
            loadingActivity.check();
        }
    }
}
