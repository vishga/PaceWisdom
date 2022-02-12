package com.pacewisdom.module1;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.pacewisdom.module2.ApiInterface;
import com.pacewisdom.module2.PosModel;
import com.pacewisdom.module2.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {

    Cursor cursor;
    String name, phonenumber;
    ArrayList<Contact> list = new ArrayList<>();
    private Context context;
    private ProgressDialog dialog;

    public ContactRepository(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    public MutableLiveData<ArrayList<PosModel>> getPostData() {
        final MutableLiveData<ArrayList<PosModel>> getPostData = new MutableLiveData<>();
        ApiInterface apiInterface = RetroClient.getInstance().create(ApiInterface.class);
        dialog.setMessage("Doing something, please wait.");
        dialog.show();

        apiInterface.getPosts().enqueue(new Callback<ArrayList<PosModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PosModel>> call, Response<ArrayList<PosModel>> response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                getPostData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PosModel>> call, Throwable t) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Log.v("onFailure", t + "");
            }
        });

        return getPostData;
    }


    @SuppressLint("Range")
    public MutableLiveData<ArrayList<Contact>> getContactData() {
        final MutableLiveData<ArrayList<Contact>> getData = new MutableLiveData<>();

        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhoneNumber(phonenumber);
                list.add(contact);
                getData.setValue(list);
            }
        }
        cursor.close();
        return getData;
    }

}
