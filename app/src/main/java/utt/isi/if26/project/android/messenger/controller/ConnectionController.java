package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.ConnectionControllerListener;
import utt.isi.if26.project.android.messenger.constant.ErrorConstants;
import utt.isi.if26.project.android.messenger.network.Util;
import utt.isi.if26.project.android.messenger.network.WebServices;
import utt.isi.if26.project.android.messenger.parser.ConnectionJSONParser;
import utt.isi.if26.project.android.messenger.parser.EmailValidator;
import utt.isi.if26.project.android.messenger.view.ConnectionView;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 24/12/2014.
 */
public class ConnectionController implements OnClickListener {

    private ConnectionView connectionView;
    private ConnectionControllerListener listener;

    public ConnectionController (EditText loginEt, EditText passwordEt, Button signInB, ConnectionControllerListener listener) {
        connectionView = new ConnectionView(loginEt, passwordEt, signInB);
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        // Check for a valid email address.
/*
        if (connectionView.getLogin().isEmpty() || connectionView.getLogin().equals(""))
            connectionView.setLoginError(ErrorConstants.ERROR_FIELD_REQUIRED);
        else if (!connectionView.getLogin().contains("@"))
            connectionView.setLoginError(ErrorConstants.ERROR_INVALID_EMAIL);
        else if (!EmailValidator.validate(connectionView.getLogin()))
            connectionView.setLoginError(ErrorConstants.ERROR_INVALID_EMAIL);

        if (connectionView.getPassword().isEmpty() || connectionView.getPassword().equals(""))
            connectionView.setPasswordError(ErrorConstants.ERROR_FIELD_REQUIRED);
        else if (connectionView.getPassword().length() < 3)
            connectionView.setPasswordError(ErrorConstants.ERROR_INVALID_PASSWORD);
        else {*/
            try {
                signInRequestOnWebServices();
            }

            catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
     //  }
    }

    public void signInRequestOnWebServices() throws JSONException, InterruptedException, ExecutionException {
        WebServices request = new WebServices();

        WebServices.addParameter("email", connectionView.getLogin());
        WebServices.addParameter("password", connectionView.getPassword());
        request.execute(Util.signInURL);

        if (!ConnectionJSONParser.error(request)) {
            ConnectionJSONParser.initRequestToJSONArray(request);
            listener.onLoginSuccess();
        } else {
            listener.onLoginFailed();
        }
    }

    public void setListeners () {
        connectionView.setListeners(this);
    }
}