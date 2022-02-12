package com.pacewisdom.module1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pacewisdom.MainActivity;
import com.pacewisdom.R;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    RecyclerView recycler;
    ContactListAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mainViewModel = new MainViewModel(this);
        fab = findViewById(R.id.fab);
        initRecyclerView();

        mainViewModel.getContactData().observe(this, new Observer<ArrayList<Contact>>() {
            @Override
            public void onChanged(ArrayList<Contact> contacts) {
                adapter.setResults(contacts);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void initRecyclerView() {
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(ContactActivity.this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new ContactListAdapter();
        recycler.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.save_contact_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        EditText et_name = dialogView.findViewById(R.id.et_name);
        EditText et_number = dialogView.findViewById(R.id.et_number);
        Button btn_save = dialogView.findViewById(R.id.btn_save);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString().trim();
                String number = et_number.getText().toString().trim();

                if (!name.equalsIgnoreCase("") && !number.equalsIgnoreCase("")) {

                    Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, name)
                            .putExtra(ContactsContract.Intents.Insert.PHONE, number);
                    ContactActivityResultLauncher.launch(contactIntent);
                    alertDialog.dismiss();

                }

            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    ActivityResultLauncher<Intent> ContactActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                }
            });
}