package utt.isi.if26.project.android.messenger.Activity;

import utt.isi.if26.project.android.messenger.model.Message;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public interface DiscussionControllerListener {

    public void update(Message message);
    public void sendMessage();
    public void wrongRequest();

}
