package com.bhavyakoshiya.coruscate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button btn_present;
    private Button btn_absent;
    private TextView tv_date;
    private Button btn_chk_att;
    private DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ID
        btn_present = (Button) findViewById(R.id.btn_present);
        btn_absent = (Button) findViewById(R.id.btn_absent);
        tv_date = (TextView) findViewById(R.id.tv_date);
        btn_chk_att = (Button) findViewById(R.id.btn_chk_att);
        mref = FirebaseDatabase.getInstance().getReference().child("attendance");

        //Get Date for Present Day
        Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());
        tv_date.setText(currentDate);

        //Uploading Present to Firebase
        btn_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> map = new HashMap<>();
                map.put("date", currentDate);
                map.put("status", "Present");

                mref.child(currentDate).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("B", "onComplete: ");
                        Toast.makeText(MainActivity.this, "Attendance Updated!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("B", "onFailure: " + e.toString());
                    }
                });

            }
        });

        //Uploading Absent to Firebase
        btn_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> map = new HashMap<>();
                map.put("date", currentDate);
                map.put("status", "Absent");

                mref.child(currentDate).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("B", "onComplete: ");
                        Toast.makeText(MainActivity.this, "Attendance Updated!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("B", "onFailure: " + e.toString());
                    }
                });

            }
        });

        btn_chk_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                startActivity(intent);

            }
        });
    }
}