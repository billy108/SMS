package com.example.administrator.sms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.model.ContactAutoCompleteAdapter;
import com.example.administrator.model.ContactData;
import com.example.administrator.model.ContactModel;

import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    private ListView mContactListVIew;
    private AutoCompleteTextView autoCompleteTextView;

    private ContactModel contactModel;
    private List<ContactData> list;
    public static final int ContactListActivity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContactListVIew = (ListView) findViewById(R.id.contactList_listView);
        contactModel = new ContactModel(this);

        list = contactModel.getPhoneContacts();

        mContactListVIew.setAdapter(contactModel.contactAdapter());
        mContactListVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("number", list.get(position).getNumber().toString());
                i.putExtra("name", list.get(position).getName().toString());
                setResult(ContactListActivity, i);
                finish();
            }
        });

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.contactList_autoCompleteTextView);
        ContactAutoCompleteAdapter adapter = new ContactAutoCompleteAdapter(this, list);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("number", list.get(position).getNumber().toString());
                i.putExtra("name", list.get(position).getName().toString());
                setResult(ContactListActivity, i);
                finish();
            }
        });
    }
}
