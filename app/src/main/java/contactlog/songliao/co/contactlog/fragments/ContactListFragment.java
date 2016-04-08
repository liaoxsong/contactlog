package contactlog.songliao.co.contactlog.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import contactlog.songliao.co.contactlog.activities.MainActivity;
import contactlog.songliao.co.contactlog.adapters.ContactListAdapter;
import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.models.User;
import contactlog.songliao.co.contactlog.tools.API;

public class ContactListFragment extends ListFragment {

    private static final String TAG = "ContactListFragment";
    private ContactListAdapter mAdapter;
    private ListView mList;
    private List<User> mUsers = new ArrayList<>();

    public ContactListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ContactListAdapter(getActivity(), mUsers);
        setListAdapter(mAdapter);
        setUpFirebaseUpdateListListener();
    }

    private void setUpFirebaseUpdateListListener() {
        Firebase firebase = new Firebase(API.URL);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mUsers.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    mUsers.add(user);
                }
                mAdapter.setUsers(mUsers);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "Failure connecting to firebase server");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        mList = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(getActivity(), view);
                popup.getMenuInflater()
                        .inflate(R.menu.menu_list, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_edit) {
                            ((MainActivity) getActivity()).goToEdit(mUsers.get(position));

                        } else if (item.getItemId() == R.id.action_delete) {
                            User u = mUsers.get(position);
                            Firebase firebase = new Firebase(API.URL);
                            firebase.child(u.getId()).removeValue();
                            mUsers.remove(u);
                            mAdapter.setUsers(mUsers);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}
