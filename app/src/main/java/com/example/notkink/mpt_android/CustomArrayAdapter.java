package com.example.notkink.mpt_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Notkink on 20.11.2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<BillEntry>  {
    private List<BillEntry> billEntries;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView description;
        ImageView thumbnail;
    }

    public CustomArrayAdapter(List<BillEntry> entries, Context context) {
        super(context, R.layout.row, entries);
        billEntries = entries;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BillEntry entry = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder.title = convertView.findViewById(R.id.titleId);
            viewHolder.description = convertView.findViewById(R.id.descriptionId);
            viewHolder.thumbnail = convertView.findViewById(R.id.thmbnailId);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.title.setText(entry.getBillName());
        viewHolder.description.setText(entry.getShopName());
//        viewHolder.description.setText( entry.getPurchaseDate().toString());
//        viewHolder.info.setOnClickListener(this);
        viewHolder.thumbnail.setTag(position);
        viewHolder.thumbnail.setImageBitmap(entry.getPhoto());
        // Return the completed view to render on screen
        return convertView;
    }
}
