package com.example.administrator.login_password;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecycleView extends AppCompatActivity {
    RecyclerView rList;
    Adapter adapter;
    FirebaseAuth auth;
    ArrayList<Book> lists;
    FirebaseDatabase database;
    DatabaseReference bokREf;
    DatabaseReference adminREf;
    Button btn;
    String uid;
    String adminId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        rList=findViewById(R.id.book_rv);
        lists=new ArrayList<>();
        btn=findViewById(R.id.add);
        auth=FirebaseAuth.getInstance();
        uid=auth.getCurrentUser().getUid();
        adapter=new Adapter(this,lists);
        rList.setLayoutManager(new LinearLayoutManager(this));
        rList.setAdapter(adapter);
        database=FirebaseDatabase.getInstance();
        bokREf=database.getReference("List");
        adminREf=database.getReference().child("admin");


        adminREf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminId=dataSnapshot.child("adminId").getValue(String.class);

                if(uid.equals(adminId)){
                    btn.setVisibility(View.VISIBLE);
                    btn.setClickable(true);
                }
                else {
                    btn.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
       lists.add(new Book("123","abc","price","zyxcfd"));


        bokREf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Book book=dataSnapshot.getValue(Book.class);
                lists.add(book);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }

    }

