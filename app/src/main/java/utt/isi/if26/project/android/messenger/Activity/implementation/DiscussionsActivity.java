package utt.isi.if26.project.android.messenger.Activity.implementation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.DiscussionsControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.controller.DiscussionsController;
import utt.isi.if26.project.android.messenger.model.Contact;
import utt.isi.if26.project.android.messenger.model.Message;
import utt.isi.if26.project.android.messenger.model.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class DiscussionsActivity extends Activity implements DiscussionsControllerListener {

    DiscussionsController discussionsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussions);

        discussionsController = new DiscussionsController(
                (ListView) findViewById(R.id.discussions_lV),
                this);
        discussionsController.setListeners();

        try {
            discussionsController.initContactsUserRequestOnWebServices();
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
    protected void onResume() {
        super.onResume();

        User.getUser().initContacts();
        User.getUser().initConversation();

        try {
            discussionsController.initContactsUserRequestOnWebServices();
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
    public void update(ListView listContacts) {
        ArrayList<String> discussions = new ArrayList<String>();

        for (Map.Entry<String, Contact> entry : User.getUser().getContacts().entrySet()) {
            if (entry.getValue().hasMessage())
                discussions.add(entry.getKey() + "\n\n" + User.getUser().getContacts().get(entry.getKey()).getLastMessage().messageView());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, discussions);
        listContacts.setAdapter(adapter);
    }

    @Override
    public void getConversation(String key) {

        User.getUser().setContactSelectioned(User.getUser().getContacts().get(key.split("\n")[0]));

        Intent listContactsAcvitity = new Intent(DiscussionsActivity.this, DiscussionActivity.class);

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
        getMenuInflater().inflate(R.menu.menu_discussions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent;
        switch (id) {
            case R.id.menu_new_group:
            intent = new Intent(this, NewGroupActivity.class);
            startActivity(intent);
            return true;
            case R.id.menu_contacts:
                intent = new Intent(this, ContactsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_state:
                intent = new Intent(this, StateActivity.class);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
