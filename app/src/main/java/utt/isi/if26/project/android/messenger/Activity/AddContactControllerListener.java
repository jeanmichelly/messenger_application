package utt.isi.if26.project.android.messenger.Activity;

/**
 * Created by jean-michelly on 09/01/2015.
 */
public interface AddContactControllerListener {

    public boolean isNetworkAvailable();
    public void networkIsUnavailable();
    public void insertSuccess();
    public void insertFailed();

}
