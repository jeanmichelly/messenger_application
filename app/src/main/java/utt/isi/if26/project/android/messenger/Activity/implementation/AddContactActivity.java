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
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.AddContactControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.controller.AddContactController;
import utt.isi.if26.project.android.messenger.controller.ConnectionController;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.parser.ConnectionJSONParser;

public class AddContactActivity extends Activity implements AddContactControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        AddContactController addContactController = new AddContactController(
                (EditText) findViewById(R.id.contact_eT),
                (Button) findViewById(R.id.add_contact_B),
                this);
        addContactController.setListeners();
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
    public void insertSuccess() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ajout de contact");
        alertDialog.setMessage("Le contact a bien été ajouté");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AddContactActivity.this, DiscussionsActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.show();
    }

    @Override
    public void insertFailed() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ajout de contact");
        alertDialog.setMessage("Le contact n'a pas été ajouté, l'adresse email est incorrect ou le contact est déjà existant");
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
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
