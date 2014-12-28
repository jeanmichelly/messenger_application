package utt.isi.if26.project.android.messenger.controller;

import android.widget.ListView;
import org.json.JSONException;
import utt.isi.if26.project.android.messenger.Activity.DiscussionControllerListener;
import utt.isi.if26.project.android.messenger.model.Message;
import utt.isi.if26.project.android.messenger.model.User;
import utt.isi.if26.project.android.messenger.network.Util;
import utt.isi.if26.project.android.messenger.network.WebServices;
import utt.isi.if26.project.android.messenger.parser.DiscussionJSONParser;
import utt.isi.if26.project.android.messenger.view.DiscussionView;

import java.util.concurrent.ExecutionException;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionController {

    private DiscussionView discussionView;
    private DiscussionControllerListener listener;

    public DiscussionController (ListView discussionLv, DiscussionControllerListener listener) {
        this.discussionView = new DiscussionView(discussionLv);
        this.listener = listener;
    }

    public void initDiscussionContactRequestOnWebServices(String token, String contact) throws JSONException, InterruptedException, ExecutionException {
        WebServices request = new WebServices();
        WebServices.addParameter("token", token);
        WebServices.addParameter("contact", contact);
        request.execute(Util.DISCUSSION_URL);
        User.getUser().initConversation();
        try {
            if ( !DiscussionJSONParser.error(request) ) {
                DiscussionJSONParser.initRequestToJSONArray(request);

                for (int i = DiscussionJSONParser.length()-1; i>=0 ; i--) {
                    Message message = new Message(
                            DiscussionJSONParser.getContenu(DiscussionJSONParser.getMessage(i)),
                            DiscussionJSONParser.getDate(DiscussionJSONParser.getMessage(i)),
                            DiscussionJSONParser.isSent(DiscussionJSONParser.getMessage(i))
                    );

                    User.getUser().addMessageConversation(message);
                }
                listener.update(discussionView.getDiscussion());
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

}
