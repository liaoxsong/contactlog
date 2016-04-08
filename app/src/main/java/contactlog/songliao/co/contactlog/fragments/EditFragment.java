package contactlog.songliao.co.contactlog.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.activities.MainActivity;
import contactlog.songliao.co.contactlog.models.User;
import contactlog.songliao.co.contactlog.tools.API;

public class EditFragment extends Fragment {

    private User mUser;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUser(User p){
        this.mUser = p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        render(view);
        return view;
    }

    private void render(View view) {
        final EditText firstNameEdit = (EditText) view.findViewById(R.id.edit_first_name);
        final EditText lastNameEdit = (EditText) view.findViewById(R.id.edit_last_name);

        final EditText dobEdit = (EditText) view.findViewById(R.id.edit_dob);
        final EditText zipEdit = (EditText) view.findViewById(R.id.edit_zip);

        if (mUser != null) {
            firstNameEdit.setText(mUser.getFirstName());
            lastNameEdit.setText(mUser.getLastName());
            dobEdit.setText(mUser.getDateOfBirth());
            zipEdit.setText(String.valueOf(mUser.getZipCode()));
        }

        Button doneButton = (Button) view.findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(firstNameEdit, lastNameEdit, dobEdit, zipEdit)) {
                    Firebase firebase = new Firebase(API.URL);
                    if (mUser == null) {//new one
                        User user = new User(firstNameEdit.getText().toString(),
                                lastNameEdit.getText().toString(),
                                dobEdit.getText().toString(),
                                Integer.parseInt(zipEdit.getText().toString()));
                        Firebase u = firebase.child(user.getId());
                        u.setValue(user);
                    } else { //existing one
                        Firebase u = firebase.child(mUser.getId());
                        mUser.setFirstName(firstNameEdit.getText().toString());
                        mUser.setLastName(lastNameEdit.getText().toString());
                        mUser.setDateOfBirth(dobEdit.getText().toString());
                        mUser.setZipCode(Integer.parseInt(zipEdit.getText().toString()));
                        u.setValue(mUser);
                    }
                    getActivity().onBackPressed();
                }
            }
        });
    }

    private boolean validate(EditText first, EditText last, EditText dob, EditText zip) {
        if (TextUtils.isEmpty(first.getText().toString())) {
            Toast.makeText(getActivity(), "Make sure first name field is not empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(last.getText().toString())) {
            Toast.makeText(getActivity(), "Make sure last name field is not empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(dob.getText().toString())) {
            Toast.makeText(getActivity(), "Make sure date of birth field is not empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(zip.getText().toString())) {
            Toast.makeText(getActivity(), "Make sure zip code field empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
