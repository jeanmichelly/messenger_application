package utt.isi.if26.project.android.messenger.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jean-michelly on 24/12/2014.
 */
public class ConnectionView {

    Button signInB;

    public ConnectionView ( Button signInB ) {
        this.signInB = signInB;
    }

    public void setListeners(View.OnClickListener onClickListener){
        signInB.setOnClickListener(onClickListener);
    }

}
