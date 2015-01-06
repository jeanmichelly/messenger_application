package utt.isi.if26.project.android.messenger.Activity;

import utt.isi.if26.project.android.messenger.model.Message;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public interface DiscussionControllerListener {

    public void notifyMessageSended(Message m);
    public void notifyMessageNotSended(Message m);
    public void wrongRequest();
    public boolean isNetworkAvailable();

}
