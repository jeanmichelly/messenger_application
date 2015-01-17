package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.AddContactControllerListener;
import android.view.View.OnClickListener;
import utt.isi.if26.project.android.messenger.constant.ErrorConstants;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.network.Util;
import utt.isi.if26.project.android.messenger.network.WebServices;
import utt.isi.if26.project.android.messenger.parser.ConnectionJSONParser;
import utt.isi.if26.project.android.messenger.parser.EmailValidator;
import utt.isi.if26.project.android.messenger.view.AddContactView;

import java.util.concurrent.ExecutionException;


/**
 * Created by jean-michelly on 09/01/2015.
 */
public class AddContactController implements OnClickListener {

    private AddContactView addContactView;
    private AddContactControllerListener listener;

    public AddContactController (EditText contactEt, Button addContactB, AddContactControllerListener listener) {
        addContactView = new AddContactView(contactEt, addContactB);
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        // Check for a valid email address.

        if ( listener.isNetworkAvailable() ) {
            if (addContactView.getContact().isEmpty() || addContactView.getContact().equals(""))
                addContactView.setContactError(ErrorConstants.ERROR_FIELD_REQUIRED);
            else if (!addContactView.getContact().contains("@"))
                addContactView.setContactError(ErrorConstants.ERROR_INVALID_EMAIL);
            else if (!EmailValidator.validate(addContactView.getContact()))
                addContactView.setContactError(ErrorConstants.ERROR_INVALID_EMAIL);
            else {
                try {
                    addContactRequestOnWebServices();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } else {
            listener.networkIsUnavailable();
        }
    }

    public void addContactRequestOnWebServices() throws JSONException, InterruptedException, ExecutionException {
        WebServices request = new WebServices();
        WebServices.addParameter("token", User.getUser().getToken());
        WebServices.addParameter("contact", addContactView.getContact());

        request.execute(Util.ADD_CONTACT_URL);

        if (!ConnectionJSONParser.error(request)) {
            listener.insertSuccess();
        } else {
            listener.insertFailed();
        }

    }

    public void setListeners () {
        addContactView.setListeners(this);
    }

}
