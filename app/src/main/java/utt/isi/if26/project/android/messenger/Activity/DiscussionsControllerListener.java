package utt.isi.if26.project.android.messenger.Activity;

import android.widget.ListView;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public interface DiscussionsControllerListener {

    public void update(ListView discussionsLv);
    public void getConversation(String key);
    public void wrongRequest();

}
