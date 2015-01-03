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
import utt.isi.if26.project.android.messenger.Activity.ContactsControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.controller.ContactsController;
import utt.isi.if26.project.android.messenger.controller.DiscussionsController;
import utt.isi.if26.project.android.messenger.model.User;

import java.util.concurrent.ExecutionException;

public class ContactsActivity extends Activity implements ContactsControllerListener {

    ContactsController contactsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsController = new ContactsController(
                (ListView) findViewById(R.id.contacts_lV),
                this);
        contactsController.setListeners();

        try {
            contactsController.initContactsUserRequestOnWebServices();
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
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, User.getUser().getContacts().keySet().toArray());
        listContacts.setAdapter(adapter);
    }

    @Override
    public void showContact(String contact) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(contact);
        alertDialog.setMessage("Nom : "+User.getUser().getContacts().get(contact).getLastName()+"\n"+
                "Prénom : "+User.getUser().getContacts().get(contact).getFirstName()+"\n"+
                "Email : "+User.getUser().getContacts().get(contact).getEmail());

        alertDialog.setButton("Retour", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });
        alertDialog.show();
    }

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
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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
        } else if (id == R.id.menu_add_contact) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
