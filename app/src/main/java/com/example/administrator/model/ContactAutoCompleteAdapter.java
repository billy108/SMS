package com.example.administrator.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.administrator.sms.R;

import java.util.ArrayList;
import java.util.List;

public class ContactAutoCompleteAdapter extends BaseAdapter implements Filterable {
    ArrayFilter mFilter;
    Context context;
    List<ContactData> mlist;
    ArrayList<ContactData> mUnfilteredData;

    public ContactAutoCompleteAdapter(Context context, List<ContactData> list) {
        this.context = context;
        this.mlist = list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHoler holer;

        if (convertView == null) {
            view = View.inflate(context, R.layout.contact_autocompletetv, null);

            holer = new ViewHoler();
            holer.autoComplete_name = (TextView) view.findViewById(R.id.contact_autoCompleteTextView_name);
            holer.autoComplete_number = (TextView) view.findViewById(R.id.contact_autoCompleteTextView_number);

            view.setTag(holer);
        } else {
            view = convertView;
            holer = (ViewHoler) view.getTag();
        }

        ContactData contactData = mlist.get(position);

        holer.autoComplete_name.setText(contactData.getName());
        holer.autoComplete_number.setText(contactData.getNumber());

        return view;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }


    static class ViewHoler {
        public TextView autoComplete_name;
        public TextView autoComplete_number;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (mUnfilteredData == null) {
                mUnfilteredData = new ArrayList<ContactData>(mlist);
            }

            if (constraint == null || constraint.length() == 0) {
                ArrayList<ContactData> list = mUnfilteredData;
                filterResults.values = list;
                filterResults.count = list.size();
            } else {
                String preFixString = constraint.toString().toLowerCase();

                ArrayList<ContactData> unfilterValues = mUnfilteredData;
                int count = unfilterValues.size();

                ArrayList<ContactData> newValues = new ArrayList<ContactData>(count);

                for (int i = 0; i < count; i++) {
                    ContactData cd = unfilterValues.get(i);
                    if (cd != null) {
                        if (cd.getName() != null && cd.getName().startsWith(preFixString)) {
                            newValues.add(cd);
                        }else if (cd.getNumber() != null && cd.getNumber().startsWith(preFixString)) {
                            newValues.add(cd);
                        }
                    }
                }

                filterResults.values = newValues;
                filterResults.count = newValues.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mlist = (List<ContactData>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
