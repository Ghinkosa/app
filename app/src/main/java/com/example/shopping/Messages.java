package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class Messages extends AppCompatActivity {
RecyclerView recyclerView;
AdapterMessage adapterMessage;
ArrayList<MessageUser> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Objects.requireNonNull(getSupportActionBar()).hide();

        arrayList.add(new MessageUser("chatnnsjjjj","false","10/30/2022"));
        arrayList.add(new MessageUser("chatnnsjjjj","false","10/30/2022"));
        arrayList.add(new MessageUser("chatnnsjjjj","false","10/30/2022"));
        arrayList.add(new MessageUser("chatnnsjjjj","false","10/30/2022"));
        arrayList.add(new MessageUser("chatnnsjjjj","false","10/30/2022"));



        recyclerView=findViewById(R.id.recycle1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterMessage=new AdapterMessage(arrayList,this);
        recyclerView.setAdapter(adapterMessage);
    }
}