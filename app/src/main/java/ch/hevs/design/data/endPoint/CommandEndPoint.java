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
import ch.hevs.design.backend.commandApi.CommandApi;
import ch.hevs.design.backend.commandApi.model.Command;

import static ch.hevs.design.HomeActivity.db;

/**
 * Created by maxim on 02.05.2017.
 */

public class CommandEndPoint extends AsyncTask<Void, Void, List<Command>> {
    private static CommandApi commandApi = null;
    private static final String TAG = CommandEndPoint.class.getName();
    private Command command;
    private LoadingActivity loadingActivity = null;
    private long idDelete = -1;

    public CommandEndPoint(){}

    public CommandEndPoint(long idDelete){
        this.idDelete = idDelete;
    }

    public CommandEndPoint(Command command, LoadingActivity loadingActivity){
        this.command = command;
        this.loadingActivity = loadingActivity;
    }
    public CommandEndPoint(Command command){
        this.command = command;
    }

    public CommandEndPoint(LoadingActivity loadingActivity) {
        this.loadingActivity = loadingActivity;
    }

    @Override
    protected List<Command> doInBackground(Void... params) {

        if(commandApi == null){
            // Only do this once
            CommandApi.Builder builder = new CommandApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            commandApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(command != null){
                commandApi.insert(command).execute();
                Log.i(TAG, "insert cepage");
            }else if(idDelete!=-1){
                commandApi.remove(idDelete).execute();
            }
            // and for instance return the list of all employees
            return commandApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Command>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Command> result){
        if(result != null) {
            db.CloudToSqlCommand(result);
        }
        if(loadingActivity!=null){
            loadingActivity.commandOk = 1;
            loadingActivity.check();
        }
    }
}
