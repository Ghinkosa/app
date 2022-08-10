package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class users_activity extends AppCompatActivity{
    RecyclerView recyclerView;
    AdapterClass adapterClass;
    String token=GlobalVariable.getToken();
    ArrayList<User> arrayList=new ArrayList<>();
    private static final String url = "http://10.240.72.40:8000/api/index";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Objects.requireNonNull(getSupportActionBar()).hide();


        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fillArray();

    }


    public void fillArray(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    if(jsonArray.length()>0){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String id=jsonObject1.getString("id");
                            String name=jsonObject1.getString("name");
                            User user=new User(id,name);
                            arrayList.add(user);
                        }
                        adapterClass=new AdapterClass(users_activity.this,arrayList);
                        recyclerView.setAdapter(adapterClass);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(users_activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization","Bearer "+token);
                return headers;
            }

        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}