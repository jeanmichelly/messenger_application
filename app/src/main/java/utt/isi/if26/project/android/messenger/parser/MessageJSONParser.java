package utt.isi.if26.project.android.messenger.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utt.isi.if26.project.android.messenger.network.WebServices;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 30/12/2014.
 */
public class MessageJSONParser {

    public static boolean error (WebServices r) throws JSONException, InterruptedException, ExecutionException {
        return new JSONObject( r.get() ).getString("error").equals("false") ? false : true;
    }

}
