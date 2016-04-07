package contactlog.songliao.co.contactlog.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.firebase.client.Firebase;
import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.fragments.ContactListFragment;
import contactlog.songliao.co.contactlog.fragments.EditFragment;
import contactlog.songliao.co.contactlog.models.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        goToContacts();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add)
                {
                    goToEdit(null);
                }
                return false;
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

    public void goToEdit(Person person) {
        EditFragment editFragment = new EditFragment();
        editFragment.setPerson(person);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new EditFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
