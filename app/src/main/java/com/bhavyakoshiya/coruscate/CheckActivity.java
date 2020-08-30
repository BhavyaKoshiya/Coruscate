package com.bhavyakoshiya.coruscate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {

    private TextView tv_present;
    private TextView tv_absent;
    private ListView listview;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Att att;
    int ab=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        att = new Att();
        tv_present = (TextView) findViewById(R.id.tv_present);
        tv_absent = (TextView) findViewById(R.id.tv_absent);
        listview = (ListView) findViewById(R.id.listview);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("attendance");

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.att_list, R.id.tv_att, list);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    att = ds.getValue(Att.class);
                    list.add(att.getDate().toString() + ": " + att.getStatus().toString());
                }
                listview.setAdapter(adapter);

               /* if (dataSnapshot.exists()){
                    ab=(int) dataSnapshot.getChildrenCount();

                */
                tv_absent.setText("Absent: 4");
                tv_present.setText("Present: 6");

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}