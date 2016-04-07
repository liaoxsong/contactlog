package contactlog.songliao.co.contactlog.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import java.util.ArrayList;
import java.util.List;
import contactlog.songliao.co.contactlog.activities.MainActivity;
import contactlog.songliao.co.contactlog.adapters.ContactListAdapter;
import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.models.Person;

public class ContactListFragment extends ListFragment {

    private ContactListAdapter mAdapter;
    private ListView mList;

    public ContactListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Person> people = new ArrayList<>();
        Person p = new Person();
        p.setFirstName("A");
        p.setLastName("B");
        p.setDateOfBirth("sdfdsf");
        p.setZipCode(5555);
        people.add(p);
        people.add(p);
        people.add(p);
        mAdapter = new ContactListAdapter(getActivity(), people);
        setListAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        mList = (ListView) view.findViewById(android.R.id.list);
        registerForContextMenu(mList);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        Firebase myFirebaseRef = new Firebase("https://contactlog.firebaseio.com/");
//
//        Firebase contact = myFirebaseRef.child("contacts").child("alanisawesome");
//        Person alan = new Person("Alan", "Turing", "01/01/1912", 55124);
//        contact.setValue(alan);

        if (item.getItemId() == R.id.action_edit) {
            //myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");

//            Person alan = new Person("Alan", "Turing", "01/01/1912", 55124);
//            Firebase contact = myFirebaseRef.child("contacts").updateChildren();
//            //Person alan = new Person("Alan", "Turing", "01/01/1912", 55124);
//            contact.setValue(alan);

            ((MainActivity) getActivity()).goToEdit(null);
            Toast.makeText(getActivity(), "edit:" + index, Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.action_delete) {
            Toast.makeText(getActivity(), "delete: " + index, Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
