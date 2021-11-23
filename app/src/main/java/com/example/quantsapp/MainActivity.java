package com.example.quantsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> arrayListl = new ArrayList<>();
    List<String> arrayLists = new ArrayList<>();
    List<String> arrayListsc = new ArrayList<>();
    List<String> arrayListlu = new ArrayList<>();
    List<String> all = new ArrayList<>();
    RecyclerView recyclerView;
    MyListAdapter adapter;
    TextView L,LU,S,SC,A;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("SYNOPSIS");

        L = findViewById(R.id.l);
        A = findViewById(R.id.a);
        LU = findViewById(R.id.lu);
        S = findViewById(R.id.s);
        SC = findViewById(R.id.sc);
        search = findViewById(R.id.search);


        callApi();
        defaultColour();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equalsIgnoreCase("")){
                    loadList(arrayLists,"a");
                }else{
                    loadList(arrayLists,s.toString());
                }

            }
        });
    }

    private void defaultColour() {
        A.setBackgroundColor(Color.parseColor("#FF9800"));
//        A.setTextColor(Color.parseColor("#FF000000"));
        L.setBackgroundColor(Color.parseColor("#FF000000"));
        LU.setBackgroundColor(Color.parseColor("#FF000000"));
        S.setBackgroundColor(Color.parseColor("#FF000000"));
        SC.setBackgroundColor(Color.parseColor("#FF000000"));
    }

    private void callApi() {
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();
        try {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

            String url = "https://qapptemporary.s3.ap-south-1.amazonaws.com/test/synopsis.json"; //
            // <----enter your post url here
            StringRequest MyStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
//                        System.out.println(response);
                        JSONObject object=new JSONObject(response);
                        String l=object.getString("l");
                        String lu=object.getString("lu");
                        String s=object.getString("s");
                        String sc=object.getString("sc");
                        String[] ls = l.split(";");
                        for(int i=0; i < ls.length;i++){
                            arrayListl.add(ls[i]);
                            all.add(ls[i]);

                        }



                        String[] lus = lu.split(";");
                        for(int i=0; i < lus.length;i++){
                            arrayListlu.add(lus[i]);
                            all.add(lus[i]);
                        }

                        String[] ss = s.split(";");
                        for(int i=0; i < ss.length;i++){
                            arrayLists.add(ss[i]);
                            all.add(ss[i]);
                        }

                        String[] scs = sc.split(";");
                        for(int i=0; i < scs.length;i++){
                            arrayListsc.add(scs[i]);
                            all.add(scs[i]);
                        }

                        loadList(all,"a");



                        System.out.println("soni"+arrayListl.size() + "," + arrayListlu.size());
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            })
            {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    return MyData;
                }
            };
            MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    300000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyRequestQueue.add(MyStringRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadList(List<String> list,String key) {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        adapter = new MyListAdapter(MainActivity.this, list,key);
        recyclerView.setAdapter(adapter);
    }

    public void openGraphL(View view) {
        loadList(arrayListl,"l");
        A.setBackgroundColor(Color.parseColor("#FF000000"));
        L.setBackgroundColor(Color.parseColor("#FF9800"));
        LU.setBackgroundColor(Color.parseColor("#FF000000"));
        S.setBackgroundColor(Color.parseColor("#FF000000"));
        SC.setBackgroundColor(Color.parseColor("#FF000000"));
    }

    public void openGraphLu(View view) {
        loadList(arrayListlu,"lu");
        A.setBackgroundColor(Color.parseColor("#FF000000"));
        L.setBackgroundColor(Color.parseColor("#FF000000"));
        LU.setBackgroundColor(Color.parseColor("#FF9800"));
        S.setBackgroundColor(Color.parseColor("#FF000000"));
        SC.setBackgroundColor(Color.parseColor("#FF000000"));
    }

    public void openGraphS(View view) {
        loadList(arrayLists,"s");
        A.setBackgroundColor(Color.parseColor("#FF000000"));
        L.setBackgroundColor(Color.parseColor("#FF000000"));
        LU.setBackgroundColor(Color.parseColor("#FF000000"));
        S.setBackgroundColor(Color.parseColor("#FF9800"));
        SC.setBackgroundColor(Color.parseColor("#FF000000"));
    }

    public void openGraphSc(View view) {
        loadList(arrayListsc,"sc");
        A.setBackgroundColor(Color.parseColor("#FF000000"));
        L.setBackgroundColor(Color.parseColor("#FF000000"));
        LU.setBackgroundColor(Color.parseColor("#FF000000"));
        S.setBackgroundColor(Color.parseColor("#FF000000"));
        SC.setBackgroundColor(Color.parseColor("#FF9800"));
    }

    public void openGraphA(View view) {
        loadList(all,"a");
        defaultColour();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // given the on click feature for the menu
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Action Click", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}