package contactlog.songliao.co.contactlog.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.models.User;

public class ContactListAdapter extends ArrayAdapter<User> {
    private final LayoutInflater mLayoutInflater;

    public ContactListAdapter(Activity activity, List<User> users) {
        super(activity, 0);
        mLayoutInflater = activity.getLayoutInflater();
        setUsers(users);
    }

    public void setUsers(List<User> users) {
        super.clear();
        super.addAll(users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final User user = getItem(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_person, parent, false);
        }
        TextView nameTxt = (TextView) convertView.findViewById(R.id.txt_name);
        nameTxt.setText(user.getFirstName() + " " + user.getLastName());

        TextView informationTxt = (TextView) convertView.findViewById(R.id.txt_information);
        informationTxt.setText("Date of birth: " + user.getDateOfBirth() + "\nZip code:" + user.getZipCode());

        return convertView;
    }
}
