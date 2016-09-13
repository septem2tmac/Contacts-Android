package com.septem.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Septem on 2016/3/24.
 */
public class NamePhoneAdapter extends ArrayAdapter<User> {

    private int resourceId;

    public NamePhoneAdapter(Context context, int textViewResourceId, List<User> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.phoneNumner = (TextView) view.findViewById(R.id.phone_number);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(user.getFirstName() + " " + user.getLastName());
        viewHolder.phoneNumner.setText(user.getPhoneNumber());
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView phoneNumner;
    }
}
