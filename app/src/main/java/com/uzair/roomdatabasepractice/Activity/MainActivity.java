package com.uzair.roomdatabasepractice.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.uzair.roomdatabasepractice.Database.DatabaseClient;
import com.uzair.roomdatabasepractice.Entity.User;
import com.uzair.roomdatabasepractice.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ListView userList;
    private EditText user_name , user_course, user_id;
    private Button insert, delete, select;
    private ArrayAdapter adapter;
    private List<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.addBtn);
        delete = findViewById(R.id.deleteBtn);
        select = findViewById(R.id.selectBTn);

        user_name = findViewById(R.id.userName);
        user_course = findViewById(R.id.userCourse);
        user_id = findViewById(R.id.userId);

        userList  = findViewById(R.id.userList);
        arrayList = new ArrayList<>();


        // insert button click
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new insertUser().execute();

            }
        });




        // delete user using userName
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                final Handler delete = new Handler(Looper.getMainLooper());

                // run in background thread
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        String name = user_name.getText().toString().trim();

                        if(!name.isEmpty()) {


                            DatabaseClient.getInstance(getApplicationContext())
                                    .getAppDatabase()
                                    .getDao()
                                    .deleteUserBy(name);

                        }
                        else
                        {

                            // run on main thread
                            delete.post(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(MainActivity.this, "Please enter user name to delete", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

            }
        });



        // select all user and show in list view

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                final Handler handler = new Handler(Looper.getMainLooper());

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                    arrayList =  DatabaseClient.getInstance(getApplicationContext())
                               .getAppDatabase()
                               .getDao()
                               .getUserList();

                        Log.d("userList", "run: "+arrayList.get(0).toString());


                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                                userList.setAdapter(adapter);


                            }
                        });
                    }
                });


            }
        });




    }



    // insert data in database
    class insertUser extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {

            String userName = user_name.getText().toString();
            String userCourse = user_course.getText().toString();
            String userId = user_id.getText().toString();

            if(!userCourse.isEmpty() && !userId.isEmpty() && !userName.isEmpty())
            {

                User user = new User(userId, userCourse, userName);

                Long result = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .getDao()
                        .insertUser(user);

                if(result != -1)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Succesfully added", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
            else
            {
                Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            }



            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Record Save", Toast.LENGTH_SHORT).show();
        }
    }



}
