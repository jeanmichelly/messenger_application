package utt.isi.if26.project.android.messenger.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utt.isi.if26.project.android.messenger.network.WebServices;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionsJSONParser {

    public static JSONArray requestToJSONArray;

    public static void initRequestToJSONArray (WebServices request) throws JSONException, InterruptedException, ExecutionException {
        requestToJSONArray = new JSONObject( request.get() ).getJSONArray("contacts");
    }

    public static int length () {
        return requestToJSONArray.length();
    }

    public static boolean error (WebServices r) throws JSONException, InterruptedException, ExecutionException {
        return new JSONObject( r.get() ).getString("error").equals("false") ? false : true;
    }

    public static JSONObject getContact (int i) throws JSONException {
        return new JSONObject( new JSONObject( requestToJSONArray.getString(i) ).getString("contact") );
    }

    public static String getEmail (JSONObject responseJSONObject) throws JSONException {
        return (String)responseJSONObject.get("email");
    }

    public static String getFirstName (JSONObject responseJSONObject) throws JSONException {
        return (String)responseJSONObject.get("first_name");
    }

    public static String getLastName (JSONObject responseJSONObject) throws JSONException {
        return (String)responseJSONObject.get("last_name");
    }

    public static int getIdFromContact (int i) throws JSONException {
        return new JSONObject(requestToJSONArray.getString(i)).getInt("id");
    }

    public static JSONObject getMessageFromContact(int i) throws JSONException {
        return new JSONObject(new JSONObject(requestToJSONArray.getString(i)).getString("message"));
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
