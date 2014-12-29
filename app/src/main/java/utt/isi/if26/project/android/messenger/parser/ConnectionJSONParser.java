package utt.isi.if26.project.android.messenger.parser;

import utt.isi.if26.project.android.messenger.network.WebServices;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 26/12/2014.
 */
public class ConnectionJSONParser {

    public static JSONObject requestToJSON;

    public static void initRequestToJSONArray (WebServices request) throws JSONException, InterruptedException, ExecutionException {
        ConnectionJSONParser.requestToJSON = new JSONObject( request.get() );
    }

    public static boolean error (WebServices r) throws JSONException, InterruptedException, ExecutionException {
        return new JSONObject( r.get() ).getString("error").equals("false") ? false : true;
    }

    public static int getId () throws JSONException {
        return Integer.valueOf(requestToJSON.getString("id"));
    }

    public static String getToken () throws JSONException {
        return requestToJSON.getString("token");
    }

}
