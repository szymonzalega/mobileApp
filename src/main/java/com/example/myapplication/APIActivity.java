package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapter.AdapterUser;
import com.example.myapplication.db.UserDBContract;
import com.example.myapplication.db.UserDBHelper;
import com.example.myapplication.dto.User;
import com.example.myapplication.dto.UserRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIActivity extends AppCompatActivity {

    private ListView list;
    private AdapterUser adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        final ListView listView = (ListView) findViewById(R.id.listView1);



        UserDBHelper dbHelper = new UserDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://mars.iti.pk.edu.pl/~nkg/pm/api/v1/users.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        UserRequest user = new Gson().fromJson(response, UserRequest.class);


                        SQLiteDatabase db = dbHelper.getReadableDatabase();

                        String[] projection = {
                                BaseColumns._ID,
                                UserDBContract.UserEntry.COLUMN_NAME_NICK,
                                UserDBContract.UserEntry.COLUMN_NAME_DESC
                        };


                        Cursor cursor = db.query(
                                UserDBContract.UserEntry.TABLE_NAME,   // The table to query
                                projection,             // The array of columns to return (pass null to get all)
                                null,              // The columns for the WHERE clause
                                null,          // The values for the WHERE clause
                                null,                   // don't group the rows
                                null,                   // don't filter by row groups
                                null               // The sort order
                        );

                        ArrayList<User> usersList = new ArrayList<>(
                        );
                        while(cursor.moveToNext()) {

                            User userDto = new User();

                            userDto.setNick(cursor.getString(
                                    cursor.getColumnIndexOrThrow(UserDBContract.UserEntry.COLUMN_NAME_NICK)));
                            userDto.setDescription(cursor.getString(
                                    cursor.getColumnIndexOrThrow(UserDBContract.UserEntry.COLUMN_NAME_DESC)));

                            usersList.add(userDto);
                        }
                        cursor.close();

                        list = (ListView) findViewById(R.id.apiList);

                        adapter = new AdapterUser(getApplicationContext(),
                                R.layout.list_row, usersList);

                        list.setAdapter(adapter);

                        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {

                                Toast.makeText(getApplicationContext(), ((AppCompatTextView) view).getText(), Toast.LENGTH_LONG).show();


                            }
                        });
*/

                        ContentValues values = new ContentValues();

                        for (User u : user.getUsers()) {
                            values.put(UserDBContract.UserEntry.COLUMN_NAME_NICK, u.getNick());
                            values.put(UserDBContract.UserEntry.COLUMN_NAME_DESC, u.getDescription());
                            long newRowId = db.insert(UserDBContract.UserEntry.TABLE_NAME, null, values);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
            }
        });




// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

}
