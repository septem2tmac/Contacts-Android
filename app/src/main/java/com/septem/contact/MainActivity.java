package com.septem.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Collections.sort(UserList.getUserList(), new UserComparator());
        NamePhoneAdapter adapter = new NamePhoneAdapter(this, R.layout.name_phone, UserList.getUserList());
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //User user = userList.get(position);
                Intent intent = new Intent(MainActivity.this, CrudActivity.class);
                intent.putExtra("user", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            Intent intent = new Intent(this, CrudActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void load() {
        FileInputStream in;
        BufferedReader reader = null;
        List<User> list = new ArrayList<>();
        try {
            in = openFileInput("contacts");
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                list.add(new User(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        UserList.setUserList(list);
    }
}
