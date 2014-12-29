package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.DiscussionControllerListener;
import utt.isi.if26.project.android.messenger.Activity.implementation.DiscussionArrayAdapter;
import utt.isi.if26.project.android.messenger.model.Contact;
import utt.isi.if26.project.android.messenger.model.Message;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.network.Util;
import utt.isi.if26.project.android.messenger.network.WebServices;
import utt.isi.if26.project.android.messenger.parser.DiscussionJSONParser;
import utt.isi.if26.project.android.messenger.view.DiscussionView;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionController implements OnClickListener {


    private DiscussionView discussionView;
    private DiscussionArrayAdapter discussionArrayAdapter;
    private DiscussionControllerListener listener;

    public DiscussionController (ListView discussionLv, DiscussionArrayAdapter discussionArrayAdapter, EditText messageEt, Button sendMessageB, DiscussionControllerListener listener) {
        this.discussionArrayAdapter = discussionArrayAdapter;
        discussionLv.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        discussionLv.setAdapter(discussionArrayAdapter);
        this.discussionView = new DiscussionView(discussionLv, messageEt, sendMessageB);

        this.listener = listener;
    }

    public void initDiscussionContactRequestOnWebServices() throws JSONException, InterruptedException, ExecutionException {
        WebServices request = new WebServices();
        WebServices.addParameter("token", User.getUser().getToken());
        WebServices.addParameter("contact", String.valueOf(User.getUser().getContactSelectioned().getId()));
        request.execute(Util.DISCUSSION_URL);
        User.getUser().initConversation();
        try {
            if ( !DiscussionJSONParser.error(request) ) {
                DiscussionJSONParser.initRequestToJSONArray(request);

                for (int i = DiscussionJSONParser.length()-1; i>=0 ; i--) {
                    Message message = new Message(
                            DiscussionJSONParser.getContenu(DiscussionJSONParser.getMessage(i)),
                            DiscussionJSONParser.getDate(DiscussionJSONParser.getMessage(i)),
                            DiscussionJSONParser.isSent(DiscussionJSONParser.getMessage(i)),
                            DiscussionJSONParser.getAuthor(DiscussionJSONParser.getMessage(i))
                    );

                    User.getUser().addMessageConversation(message);
                }
                Iterator<Message> iter = User.getUser().getConversation().iterator();

                while (iter.hasNext()) {
                    discussionArrayAdapter.add(iter.next());
                }
            } else {
                listener.wrongRequest();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        listener.sendMessage();


        WebServices request = new WebServices();
        WebServices.addParameter("token", User.getUser().getToken());
        WebServices.addParameter("contact", String.valueOf(User.getUser().getContactSelectioned().getId()));
        WebServices.addParameter("message", "toto");
        request.execute(Util.MESSAGE_URL);
    }

    public void setListeners () {
        discussionView.setListeners(this);
    }
}
