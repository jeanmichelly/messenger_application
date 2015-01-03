package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.ContactsControllerListener;
import utt.isi.if26.project.android.messenger.Activity.DiscussionsControllerListener;
import utt.isi.if26.project.android.messenger.model.Contact;
import utt.isi.if26.project.android.messenger.model.Message;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.network.Util;
import utt.isi.if26.project.android.messenger.network.WebServices;
import utt.isi.if26.project.android.messenger.parser.DiscussionsJSONParser;
import utt.isi.if26.project.android.messenger.view.ContactsView;
import utt.isi.if26.project.android.messenger.view.DiscussionsView;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 03/01/2015.
 */
public class ContactsController implements OnItemClickListener {

    private ContactsView contactsView;
    private ContactsControllerListener listener;

    public ContactsController (ListView contactsLv, ContactsControllerListener listener) {
        contactsView = new ContactsView(contactsLv);
        this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        listener.showContact(((TextView) arg1).getText().toString());
    }

    public void initContactsUserRequestOnWebServices() throws JSONException, InterruptedException, ExecutionException {
        WebServices request = new WebServices();
        WebServices.addParameter("token", User.getUser().getToken());
        request.execute(Util.CONTACTS_URL);

        try {
            if (!DiscussionsJSONParser.error(request)) {
                listener.update(contactsView.getContacts());
            } else {
                listener.wrongRequest();
            }
        } catch (InterruptedException e) {
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

    public void setListeners () {
        contactsView.setListeners(this);
    }

}
