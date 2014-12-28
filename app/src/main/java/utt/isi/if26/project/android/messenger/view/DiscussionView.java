package utt.isi.if26.project.android.messenger.view;

import android.widget.ListView;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionView {

    ListView discussionLv;

    public DiscussionView (ListView discussionLv) {
        this.discussionLv = discussionLv;
    }

    public ListView getDiscussion () {
        return discussionLv;
    }

}
