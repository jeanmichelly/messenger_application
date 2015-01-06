package utt.isi.if26.project.android.messenger.Activity.implementation;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.DiscussionControllerListener;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.view.DiscussionArrayAdapter;
import utt.isi.if26.project.android.messenger.controller.DiscussionController;
import utt.isi.if26.project.android.messenger.model.Message;

import java.util.concurrent.ExecutionException;

public class DiscussionActivity extends Activity implements DiscussionControllerListener {

    private static NotificationManager notificationManager;
    private static int notifyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        getActionBar().setTitle(User.getUser().getContactSelectioned().getFirstName()+" "+User.getUser().getContactSelectioned().getLastName());
        DiscussionArrayAdapter discussionArrayAdapter = new DiscussionArrayAdapter(getApplicationContext(), R.layout.activity_discussion_singlemessage);
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notifyId = 1;

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
    public void notifyMessageSended(Message m) {
        Notification notification = new Notification(R.drawable.ic_launcher, "Message envoyé", System.currentTimeMillis());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DiscussionActivity.class), 0);

        String notificationTitle = "Message envoyé";
        String notificationContenu = m.getContenu()+"\n"+m.getDate();

        notification.setLatestEventInfo(this, notificationTitle, notificationContenu, pendingIntent);
        notification.vibrate = new long[] {0,200,100,200,100,200};

        notificationManager.notify(notifyId++, notification);
    }

    @Override
    public void notifyMessageNotSended(Message m) {
        Notification notification = new Notification(R.drawable.ic_launcher, "Message envoyé", System.currentTimeMillis());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DiscussionActivity.class), 0);

        String notificationTitle = "Message non envoyé";
        String notificationContenu = m.getContenu()+"\n"+m.getDate();

        notification.setLatestEventInfo(this, notificationTitle, notificationContenu, pendingIntent);
        notification.vibrate = new long[] {0,200,100,200,100,200};

        notificationManager.notify(notifyId++, notification);
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
