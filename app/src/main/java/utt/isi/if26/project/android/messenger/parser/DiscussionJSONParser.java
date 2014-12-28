package utt.isi.if26.project.android.messenger.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utt.isi.if26.project.android.messenger.network.WebServices;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionJSONParser {

    public static JSONArray requestToJSONArray;

    public static void initRequestToJSONArray (WebServices request) throws JSONException, InterruptedException, ExecutionException {
        requestToJSONArray = new JSONObject( request.get() ).getJSONArray("messages");
    }

    public static int length () {
        return requestToJSONArray.length();
    }

    public static boolean error (WebServices r) throws JSONException, InterruptedException, ExecutionException {
        return new JSONObject( r.get() ).getString("error").equals("false") ? false : true;
    }

    public static JSONObject getMessage(int i) throws JSONException {
        return new JSONObject(requestToJSONArray.getString(i));
    }

    public static String getContenu (JSONObject messageJSONObject) throws JSONException {
        return messageJSONObject.getString("message");
    }

    public static String getDate (JSONObject messageJSONObject) throws JSONException {
        return messageJSONObject.getString("date");
    }

    public static boolean isSent (JSONObject messageJSONObject) throws JSONException {
        return messageJSONObject.getBoolean("sent");
    }

}
