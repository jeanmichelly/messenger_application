package utt.isi.if26.project.android.messenger.Activity.implementation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import utt.isi.if26.project.android.messenger.Activity.NewDiscussionControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.controller.NewDiscussionController;
import utt.isi.if26.project.android.messenger.model.Contact;
import utt.isi.if26.project.android.messenger.model.User;

import java.util.ArrayList;
import java.util.Map;

public class NewDiscussionActivity extends Activity implements NewDiscussionControllerListener {

    NewDiscussionController newDiscussionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_discussion);

        newDiscussionController = new NewDiscussionController(
                (ListView) findViewById(R.id.contacts_no_disccussion_lV),
                this);
        newDiscussionController.setListeners();

        newDiscussionController.initContactsNoDiscussion();
    }

    @Override
    public void update(ListView contactsNoDiscussion) {
        ArrayList<String> discussions = new ArrayList<String>();


        for (Map.Entry<String, Contact> entry : User.getUser().getContacts().entrySet()) {
            if (!entry.getValue().hasMessage())
                discussions.add(entry.getKey());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, discussions);
        contactsNoDiscussion.setAdapter(adapter);
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void networkIsUnavailable() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Echec de connexion");
        alertDialog.setMessage("Vérifier votre connexion internet");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });
        alertDialog.show();
    }

    @Override
    public void getConversation(String key) {

        User.getUser().setContactSelectioned(User.getUser().getContacts().get(key.split("\n")[0]));

        Intent listContactsAcvitity = new Intent(NewDiscussionActivity.this, DiscussionActivity.class);

        startActivity(listContactsAcvitity);
    }

    public void wrongRequest() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Requête incorrecte");
        alertDialog.setMessage("Veuillez vérifier ales paramètres ou leurs valeurs");
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
        getMenuInflater().inflate(R.menu.menu_new_discussion, menu);
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
