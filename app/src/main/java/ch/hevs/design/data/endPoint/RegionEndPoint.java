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
import ch.hevs.design.backend.regionApi.RegionApi;
import ch.hevs.design.backend.regionApi.model.Region;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class RegionEndPoint extends AsyncTask<Void, Void, List<Region>> {
    private static RegionApi regionApi = null;
    private static final String TAG = RegionEndPoint.class.getName();
    private Region region;
    private LoadingActivity loadingActivity = null;

    public RegionEndPoint(){}

    public RegionEndPoint(Region region, LoadingActivity loadingActivity){
        this.region = region;
        this.loadingActivity = loadingActivity;
    }
    public RegionEndPoint(Region region){
        this.region = region;
    }

    public RegionEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Region> doInBackground(Void... params) {

        if(regionApi == null){
            // Only do this once
            RegionApi.Builder builder = new RegionApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            regionApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(region != null){
                regionApi.insert(region).execute();
                Log.i(TAG, "insert pays");
            }
            // and for instance return the list of all employees
            return regionApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Region>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Region> result){
        if(result != null) {
            db.CloudToSqlRegion(result);
        }
        if(loadingActivity!=null){
            loadingActivity.regionOk = 1;
            loadingActivity.check();
        }
    }
}
