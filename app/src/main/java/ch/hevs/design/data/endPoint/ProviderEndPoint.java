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
import ch.hevs.design.backend.providerApi.ProviderApi;
import ch.hevs.design.backend.providerApi.model.Provider;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class ProviderEndPoint extends AsyncTask<Void, Void, List<Provider>> {
    private static ProviderApi providerApi = null;
    private static final String TAG = ProviderEndPoint.class.getName();
    private Provider provider;
    private LoadingActivity loadingActivity = null;


    public ProviderEndPoint(){}

    public ProviderEndPoint(Provider provider,LoadingActivity loadingActivity){
        this.provider = provider;
        this.loadingActivity = loadingActivity;
    }

    public ProviderEndPoint(Provider provider){
        this.provider = provider;
    }

    public ProviderEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Provider> doInBackground(Void... params) {

        if(providerApi == null){
            // Only do this once
            ProviderApi.Builder builder = new ProviderApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            providerApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(provider != null){
                providerApi.insert(provider).execute();
                Log.i(TAG, "insert pays");
            }
            // and for instance return the list of all employees

            return providerApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Provider>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Provider> result){
        if(result != null) {
            db.CloudToSqlProvider(result);
        }
        if(loadingActivity!=null){
            loadingActivity.providerOk = 1;
            loadingActivity.check();
        }
    }
}
