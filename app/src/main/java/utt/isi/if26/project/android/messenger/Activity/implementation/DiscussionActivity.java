package utt.isi.if26.project.android.messenger.Activity.implementation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.DiscussionControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.view.DiscussionArrayAdapter;
import utt.isi.if26.project.android.messenger.controller.DiscussionController;
import utt.isi.if26.project.android.messenger.model.Message;

import java.util.concurrent.ExecutionException;

public class DiscussionActivity extends Activity implements DiscussionControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        DiscussionArrayAdapter discussionArrayAdapter = new DiscussionArrayAdapter(getApplicationContext(), R.layout.activity_discussion_singlemessage);

        DiscussionController discussionController = new DiscussionController(
                (ListView) findViewById(R.id.discussion_lV),
                discussionArrayAdapter,
                (EditText) findViewById(R.id.send_message_eT),
                (Button) findViewById(R.id.send_message_b),
                this);
        discussionController.setListeners();
        try {
            discussionController.initDiscussionContactRequestOnWebServices();
        }

        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void wrongRequest() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Requête incorrecte");
        alertDialog.setMessage("Veuillez vérifier les paramètres ou leurs valeurs");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discussion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
