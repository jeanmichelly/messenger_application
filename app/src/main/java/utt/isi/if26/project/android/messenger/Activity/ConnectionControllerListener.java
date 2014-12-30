package utt.isi.if26.project.android.messenger.Activity;

/**
 * Created by jean-michelly on 24/12/2014.
 */
public interface ConnectionControllerListener {

    public boolean isNetworkAvailable();
    public void networkIsUnavailable();
    public void onLoginSuccess();
    public void onLoginFailed();

}
