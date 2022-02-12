package com.pacewisdom.module1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacewisdom.R;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListHolder> {

    public List<Contact> results = new ArrayList<>();

    @NonNull
    @Override
    public ContactListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ContactListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListHolder holder, int position) {
        Contact contact = results.get(position);

        holder.name_tv.setText("Name: "+contact.getName());
        holder.number_tv.setText("Mobile Number: "+contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Contact> results) {

        this.results = results;
        notifyDataSetChanged();
    }


   public class ContactListHolder extends RecyclerView.ViewHolder {

        private TextView name_tv,number_tv;

        public ContactListHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.name_tv);
            number_tv = itemView.findViewById(R.id.number_tv);
        }
    }
}
