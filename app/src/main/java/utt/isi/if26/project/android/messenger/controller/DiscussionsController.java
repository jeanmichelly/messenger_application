package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.DiscussionsControllerListener;
import utt.isi.if26.project.android.messenger.model.Contact;
import utt.isi.if26.project.android.messenger.model.Message;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.network.Util;
import utt.isi.if26.project.android.messenger.network.WebServices;
import utt.isi.if26.project.android.messenger.parser.DiscussionsJSONParser;
import utt.isi.if26.project.android.messenger.view.DiscussionsView;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionsController implements OnItemClickListener {

    private DiscussionsView discussionsView;
    private DiscussionsControllerListener listener;

    public DiscussionsController (ListView discussionsLv, DiscussionsControllerListener listener) {
        discussionsView = new DiscussionsView(discussionsLv);
        this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        listener.getConversation(((TextView) arg1).getText().toString());
    }

    public void initContactsUserRequestOnWebServices() throws JSONException, InterruptedException, ExecutionException {
        WebServices request = new WebServices();
        WebServices.addParameter("token", User.getUser().getToken());
        request.execute(Util.DISCUSSIONS_URL);

        try {
            if (!DiscussionsJSONParser.error(request)) {
                DiscussionsJSONParser.initRequestToJSONArray(request);

                Contact c;

                for (int i = 0; i < DiscussionsJSONParser.length(); i++) {
                    if ( DiscussionsJSONParser.hasDiscussion(i) ) {
                        c = new Contact(
                                DiscussionsJSONParser.getIdFromContact(i),
                                DiscussionsJSONParser.getEmail(DiscussionsJSONParser.getContact(i)),
                                DiscussionsJSONParser.getFirstName(DiscussionsJSONParser.getContact(i)),
                                DiscussionsJSONParser.getLastName(DiscussionsJSONParser.getContact(i)),
                                new Message(
                                        DiscussionsJSONParser.getContenu(DiscussionsJSONParser.getMessageFromContact(i)),
                                        DiscussionsJSONParser.getDate(DiscussionsJSONParser.getMessageFromContact(i)),
                                        DiscussionsJSONParser.isSent(DiscussionsJSONParser.getMessageFromContact(i))
                                )
                        );
                    } else {
                        c = new Contact(
                                DiscussionsJSONParser.getIdFromContact(i),
                                DiscussionsJSONParser.getEmail(DiscussionsJSONParser.getContact(i)),
                                DiscussionsJSONParser.getFirstName(DiscussionsJSONParser.getContact(i)),
                                DiscussionsJSONParser.getLastName(DiscussionsJSONParser.getContact(i))
                        );
                    }
                    User.getUser().addContact(c.getFirstName() + " " + c.getLastName(), c);
                }
                listener.update(discussionsView.getDiscussions());
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
        discussionsView.setListeners(this);
    }

}
