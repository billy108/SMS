package com.example.administrator.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.sms.R;

import java.util.ArrayList;
import java.util.List;

public class ContactModel{
    private Context context;
    private LayoutInflater inflater;

    private List<ContactData> list;

    public ContactModel(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<ContactData> getPhoneContacts(){
        list = new ArrayList<ContactData>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(indexId);
            int indexDisplayName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(indexDisplayName);

            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
            while (phones.moveToNext()){
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                ContactData data = new ContactData();
                data.id = contactId;
                data.name = name;
                data.number = phoneNumber;
                list.add(data);
            }
        }
        return list;
    }

    public BaseAdapter contactAdapter(){
        final List<ContactData> list = getPhoneContacts();

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null){
                    convertView = inflater.inflate(R.layout.contact_item, null);
                }

                TextView name = (TextView) convertView.findViewById(R.id.contactItem_name);
                TextView number = (TextView) convertView.findViewById(R.id.contactItem_number);
                TextView rank = (TextView) convertView.findViewById(R.id.contactItem_rank);

                name.setText(list.get(position).getName().toString());
                number.setText(list.get(position).getNumber().toString());


                return convertView;
            }
        };
        return adapter;
    }
}
