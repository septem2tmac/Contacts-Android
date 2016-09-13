package com.septem.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Septem on 2016/3/24.
 */
public class CrudActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText email;
    private EditText date;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud);
        firstName = (EditText) findViewById(R.id.first_name_edit_text);
        lastName = (EditText) findViewById(R.id.last_name_edit_text);
        phoneNumber = (EditText) findViewById(R.id.phone_number_edit_text);
        email = (EditText) findViewById(R.id.email_edit_text);
        date = (EditText) findViewById(R.id.date_edit_text);

        intent = getIntent();
        if (intent.hasExtra("user")) {
            List<User> users = UserList.getUserList();
            int i = intent.getIntExtra("user", -1);
            firstName.setText(users.get(i).getFirstName());
            lastName.setText(users.get(i).getLastName());
            phoneNumber.setText(users.get(i).getPhoneNumber());
            email.setText(users.get(i).getEmail());
            date.setText(users.get(i).getDate());
        } else {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            date = (EditText) findViewById(R.id.date_edit_text);
            date.setText(dateFormat.format(new Date()));
        }
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (intent.hasExtra("user")) {
                    int i = intent.getIntExtra("user", -1);
                    UserList.getUserList().remove(i);
                    saveInfile(UserList.getUserList());
                }
                finish();*/
                AlertDialog.Builder dialog = new AlertDialog.Builder(CrudActivity.this);
                dialog.setMessage("Do you want to delete this user?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (intent.hasExtra("user")) {
                            int i = intent.getIntExtra("user", -1);
                            UserList.getUserList().remove(i);
                            saveInfile(UserList.getUserList());
                        }
                        finish();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

    private void saveCurrentcontact() {
        if (TextUtils.isEmpty(firstName.getText().toString())) {
            Toast.makeText(this, "First Name must not be empty", Toast.LENGTH_LONG).show();
            return;
        } else if(!CheckDate.isValid(date.getText().toString())) {
            Toast.makeText(this, "The Date Format is xx/xx/xxxx", Toast.LENGTH_SHORT).show();
            return;
        } else if (!intent.hasExtra("user")) {
            UserList.getUserList().add(new User(firstName.getText().toString(),
                                                lastName.getText().toString(),
                                                phoneNumber.getText().toString(),
                                                email.getText().toString(),
                                                date.getText().toString()));
        } else {
            UserList.getUserList().set(intent.getIntExtra("user", -1), new User(firstName.getText().toString(),
                                                                                    lastName.getText().toString(),
                                                                                    phoneNumber.getText().toString(),
                                                                                    email.getText().toString(),
                                                                                    date.getText().toString()));
        }
        saveInfile(UserList.getUserList());
        Toast.makeText(this, "Save is completed", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            saveCurrentcontact();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveInfile(List<User> userList) {
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("contacts", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            for(User user : userList) {
                writer.write(user.Send());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
