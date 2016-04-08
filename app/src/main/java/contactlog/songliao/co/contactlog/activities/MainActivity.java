package contactlog.songliao.co.contactlog.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.fragments.ContactListFragment;
import contactlog.songliao.co.contactlog.fragments.EditFragment;
import contactlog.songliao.co.contactlog.models.User;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitleTxt;
    private FloatingActionButton mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpFloatingIcon();
        goToContacts();
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mTitleTxt = (TextView) mToolbar.findViewById(R.id.txt_toolbar_title);

    }
    private void setUpFloatingIcon() {
        mButtonAdd = (FloatingActionButton) findViewById(R.id.btn_new_contact);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new contact
                goToEdit(null);
            }
        });
    }

    public void goToContacts() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ContactListFragment())
                .addToBackStack(null)
                .commit();
    }

    public void goToEdit(User user) {
        mButtonAdd.setVisibility(View.GONE);
        if (user == null) {
            mTitleTxt.setText("Create new contact");
        } else {
            mTitleTxt.setText("Edit contact");
        }
        EditFragment editFragment = new EditFragment();
        editFragment.setUser(user);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        mTitleTxt.setText("My Contact");
        mButtonAdd.setVisibility(View.VISIBLE);
        if (fm.getBackStackEntryCount() > 1) {
            super.onBackPressed();
        }
    }
}
