package Sevices;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import data.Current_User;

/**
 * Created by darthvader on 9/12/17.
 */

public class GithubService {
    private CallbackService callbackService;
    String username;
    Exception error;
    Current_User user;
    String names;

    public GithubService(CallbackService callbackService) {
        this.callbackService = callbackService;
    }

    @SuppressLint("StaticFieldLeak")
    public void refreshRepos(final String u) {
        this.username=u;
        names="";
        Log.d("username",username);
        new AsyncTask<String, Void, String>(

        ) {
            @Override
            protected String doInBackground(String... strings) {
                String endpoint = String.format("https://api.github.com/users/%s/repos",strings[0]);
                Log.d("lol",endpoint);
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }
                    Log.d("chutiya",result.toString());
                    return result.toString();

                } catch (IOException e) {
                    error = e;
                    //callbackService.serviceFailure(error);
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null && error!=null)
                {
                    callbackService.serviceFailure(error);
                    return;
                }
                else if(s.equals("[]") )
                {
                    callbackService.repoNotFound(new RepoNotFoundException(""));
                    return;
                }



                try {
                         JSONArray data = new JSONArray(s);
                         for(int i=0;i<data.length();i++)
                         {
                             JSONObject c=data.getJSONObject(i);
                             names+=c.optString("name")+"\n";
                         }
                         Log.d("names",names);

                         Current_User user=new Current_User();
                         user.returnnames(names);
                         callbackService.serviceSuccess(user);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(username);

    }


}
class RepoNotFoundException extends Exception
{

    public RepoNotFoundException(String a) {
        super(a);
    }
}

