package utt.isi.if26.project.android.messenger.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jean-michelly on 24/12/2014.
 */
public class ConnectionView {

    EditText loginEt;
    EditText passwordEt;
    Button signInB;

    public ConnectionView ( EditText loginEt, EditText passwordEt, Button signInB ) {
        this.loginEt = loginEt;
        this.passwordEt = passwordEt;
        this.signInB = signInB;
    }

    public void setListeners(View.OnClickListener onClickListener){
        signInB.setOnClickListener(onClickListener);
    }

    public String getLogin() {return loginEt.getText().toString();}
    public String   getPassword() {return passwordEt.getText().toString();}

    public void setLoginError(String error) {loginEt.setError(error);}
    public void setPasswordError(String error) {passwordEt.setError(error);}

}
