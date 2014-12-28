package utt.isi.if26.project.android.messenger.view;

import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public class DiscussionsView {

    ListView discussionsLv;

    public DiscussionsView (ListView discussionsLv) {
        this.discussionsLv = discussionsLv;
    }

    public void setListeners(AdapterView.OnItemClickListener onItemClickListener){
        discussionsLv.setOnItemClickListener(onItemClickListener);
    }

    public ListView getDiscussions () {
        return discussionsLv;
    }

}
