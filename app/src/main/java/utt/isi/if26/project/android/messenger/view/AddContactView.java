package utt.isi.if26.project.android.messenger.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jean-michelly on 09/01/2015.
 */
public class AddContactView {

    EditText contactEt;
    Button addContactB;

    public AddContactView ( EditText contactEt, Button addContactB ) {
        this.contactEt = contactEt;
        this.addContactB = addContactB;
    }

    public void setListeners(View.OnClickListener onClickListener){
        addContactB.setOnClickListener(onClickListener);
    }

    public EditText getContactEt() {return contactEt;}
    public Button getAddContactB() {return addContactB;}

    public String getContact() {return contactEt.getText().toString();}

    public void setContactError(String error) {contactEt.setError(error);}

}
