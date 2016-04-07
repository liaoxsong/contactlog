package contactlog.songliao.co.contactlog.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import contactlog.songliao.co.contactlog.R;
import contactlog.songliao.co.contactlog.models.Person;

public class EditFragment extends Fragment {

    private Person mPerson;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setPerson(Person p){
        this.mPerson = p;
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

        Button doneButton = (Button) view.findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p = new Person();
                if (!TextUtils.isEmpty(firstNameEdit.getText().toString())) {
                    p.setFirstName(firstNameEdit.getText().toString());
                }
                if (!TextUtils.isEmpty(lastNameEdit.getText().toString())) {
                    p.setLastName(lastNameEdit.getText().toString());
                }
                if (!TextUtils.isEmpty(dobEdit.getText().toString())) {
                    p.setDateOfBirth(dobEdit.getText().toString());
                }
                if (!TextUtils.isEmpty(zipEdit.getText().toString())) {
                    p.setZipCode(Integer.parseInt(zipEdit.getText().toString()));
                }

            }
        });
    }

}
