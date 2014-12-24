package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import utt.isi.if26.project.android.messenger.Activity.ConnectionControllerListener;
import utt.isi.if26.project.android.messenger.view.ConnectionView;

/**
 * Created by jean-michelly on 24/12/2014.
 */
public class ConnectionController implements OnClickListener {

    private ConnectionView connectionView;
    private ConnectionControllerListener listener;

    public ConnectionController (Button signInB, ConnectionControllerListener listener) {
        connectionView = new ConnectionView(signInB);
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        listener.onLoginSuccess();
    }

    public void setListeners () {
        connectionView.setListeners(this);
    }
}