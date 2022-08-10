package com.example.shopping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText email,name,password,reset;
    TextView error;
    private final   String url="http://10.240.72.40:8000/api/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView register = findViewById(R.id.back);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
         name = findViewById(R.id.full_name);
         email = findViewById(R.id.user_email);
         password= findViewById(R.id.password);
         reset = findViewById(R.id.recet);
         TextView create = findViewById(R.id.create);
          error = findViewById(R.id.error);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              insertData();
            }
        });
    }
        private void insertData() {
            final String name1 = name.getText().toString().trim();
            final String email1 = email.getText().toString().trim();
            final String password1 = password.getText().toString().trim();
            final String reset1=reset.getText().toString().trim();
            if (name1.length() < 1) {
                error.setText("user name is required");
            } else if (email1.length() < 1) {
                error.setText("email is required");
            } else if (password1.length() < 1) {
                error.setText("password is required");
            } else if (!(password1.equals(reset1))) {
                error.setText("password not match");
            } else {
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        name.setText("");
                        password.setText("");
                        email.setText("");
                        reset.setText("");
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("name", name1);
                        param.put("email", email1);
                        param.put("password", password1);
                        param.put("password_confirmation",reset1);
                        return param;
                    }
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Accept","application/json");
                        return headers;
                    }


                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            }
        }
}