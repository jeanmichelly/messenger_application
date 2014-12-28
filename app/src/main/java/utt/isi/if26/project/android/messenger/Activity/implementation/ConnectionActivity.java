package utt.isi.if26.project.android.messenger.Activity.implementation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import android.widget.EditText;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.ConnectionControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.controller.ConnectionController;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.parser.ConnectionJSONParser;


public class ConnectionActivity extends Activity implements ConnectionControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        ConnectionController connectionController = new ConnectionController(
                (EditText) findViewById(R.id.login_eT),
                (EditText) findViewById(R.id.password_eT),
                (Button) findViewById(R.id.sign_in_b),
                this);
        connectionController.setListeners();
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, DiscussionsActivity.class);
        try {
            User.getUser().setToken(ConnectionJSONParser.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        startActivity(intent);
    }

    @Override
    public void onLoginFailed() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Echec de connexion");
        alertDialog.setMessage("Votre email ou mot de passe est incorrect");
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
        getMenuInflater().inflate(R.menu.menu_connection, menu);
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
