package com.example.administrator.login_password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    Context context1;
    ArrayList<Book>lists;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference bookRef;



    public Adapter(Context context1, ArrayList<Book> contacts) {
        this.context1 = context1;
        this.lists = contacts;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context1);
        View view=inflater.inflate(R.layout.listview,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Book list = lists.get(i);
        database = FirebaseDatabase.getInstance();

        reference = database.getReference().child("List");
        bookRef = reference.child(list.getBookId());
        if (bookRef != null) {

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    DecimalFormat decimalFormat = new DecimalFormat("#.#");

                    if (dataSnapshot.hasChild("avgRating")) {
                        float avg = dataSnapshot.child("avgRating").getValue(Float.class);
                        viewHolder.price.setText(decimalFormat.format(avg));
                    }
                    viewHolder.view.setText(String.valueOf(dataSnapshot.child("viewCount").getValue(Long.class)));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            viewHolder.name.setText(list.getName());


            Glide.with(context1).load(list.getImage()).into(viewHolder.image);

        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("bookId", list.bookId);
                Intent intent = new Intent(context1,Detail.class);
                intent.putExtras(bundle);
                context1.startActivity(intent);
            }
        });
        }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
