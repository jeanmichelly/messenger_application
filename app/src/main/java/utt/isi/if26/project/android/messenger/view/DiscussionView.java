package utt.isi.if26.project.android.messenger.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionView {

    ListView discussionLv;
    EditText messageEt;
    Button sendMessageB;

    public DiscussionView (ListView discussionLv, EditText messageEt, Button sendMessageB) {
        this.discussionLv = discussionLv;
        this.messageEt = messageEt;
        this.sendMessageB = sendMessageB;
    }

    public void setListeners(View.OnClickListener onClickListener){
        sendMessageB.setOnClickListener(onClickListener);
    }

    public ListView getDiscussion () {
        return discussionLv;
    }

}
