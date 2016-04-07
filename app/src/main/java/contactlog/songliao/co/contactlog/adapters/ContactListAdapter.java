package contactlog.songliao.co.contactlog.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.models.Person;

public class ContactListAdapter extends ArrayAdapter<Person> {
    private final LayoutInflater mLayoutInflater;

    public ContactListAdapter(Activity activity, List<Person> people) {
        super(activity, 0);
        mLayoutInflater = activity.getLayoutInflater();
        setContacts(people);
    }


    public void setContacts(List<Person> people) {
        super.clear();
        super.addAll(people);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Person person = getItem(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_person, parent, false);
        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.txt_name);
        nameTxt.setText(person.getFullName());

        TextView informationTxt = (TextView) convertView.findViewById(R.id.txt_information);
        informationTxt.setText("Dob: " + person.getDateOfBirth() + "    | Zip code:" + person.getZipCode());

        return convertView;
    }


}
