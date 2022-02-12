package com.pacewisdom.module2;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pacewisdom.module1.Contact;
import com.pacewisdom.module1.ContactRepository;

import java.util.ArrayList;

public class PostViewModel extends ViewModel {

    ContactRepository contactRepository;
    private Context context;


    MutableLiveData<ArrayList<PosModel>> getpost;

    public PostViewModel(Context context) {
        contactRepository = new ContactRepository(context);
    }

    public LiveData<ArrayList<PosModel>> getPostData()
    {
        if (getpost==null)
        {
            getpost = contactRepository.getPostData();
        }
        return getpost;
    }
}
