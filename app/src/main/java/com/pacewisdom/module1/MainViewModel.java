package com.pacewisdom.module1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    ContactRepository contactRepository;
    private Context context;


    MutableLiveData<ArrayList<Contact>> getContact;

    public MainViewModel(Context context) {
        contactRepository = new ContactRepository(context);
    }

    public LiveData<ArrayList<Contact>> getContactData()
    {
        if (getContact==null)
        {
            getContact = contactRepository.getContactData();
        }
        return getContact;
    }
}
