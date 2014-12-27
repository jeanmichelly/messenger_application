package utt.isi.if26.project.android.messenger.network;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import utt.isi.if26.project.android.messenger.parser.JSONParser;

/**
 * Created by jean-michelly on 26/12/2014.
 */
public class WebServices extends AsyncTask<String, String, String> {

    static private Map<String, String> parameters;

    private static final String TAG_SUCCESS = "success";

    public WebServices () {
        WebServices.parameters = new HashMap<String, String>();
    }

    @Override
    protected String doInBackground(String... uris) {
        JSONParser jsonParser = new JSONParser();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", parameters.get("email")));
        params.add(new BasicNameValuePair("password", parameters.get("password")));

        JSONObject json = jsonParser.makeHttpRequest(uris[0],
                "GET", params);

        return json.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
    }

    public static void addParameter (String key, String value) {
        WebServices.parameters.put(key, value);
    }

}
