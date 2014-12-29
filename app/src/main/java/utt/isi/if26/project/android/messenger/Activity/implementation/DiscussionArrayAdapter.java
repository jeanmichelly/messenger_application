package utt.isi.if26.project.android.messenger.Activity.implementation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import utt.isi.if26.project.android.messenger.R;
import utt.isi.if26.project.android.messenger.model.Message;
import utt.isi.if26.project.android.messenger.model.User;

import java.util.ArrayList;
import java.util.List;

public class DiscussionArrayAdapter extends ArrayAdapter<Message> {

    private TextView chatText;
    private List<Message> chatMessageList = new ArrayList<Message>();
    private LinearLayout singleMessageContainer;

    @Override
    public void add(Message object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public DiscussionArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_discussion_singlemessage, parent, false);
        }
        singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
        Message chatMessageObj = getItem(position);
        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.messageView());
        chatText.setBackgroundResource(R.drawable.bubble_a);
        System.out.println("ID AUTHOR : "+chatMessageObj.getAuthor()+ " "+User.getUser().getId());
        chatText.setBackgroundResource(chatMessageObj.getAuthor() == User.getUser().getId() ? R.drawable.bubble_a : R.drawable.bubble_b);
        singleMessageContainer.setGravity(chatMessageObj.getAuthor() == User.getUser().getId() ? Gravity.RIGHT : Gravity.LEFT);
        return row;
    }

}
