package com.example.travelpakistan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class City extends AppCompatActivity {
    ListView lv;
    ArrayList<String> spots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText("Top Spots in "+city);

//        Toast.makeText(this, city, Toast.LENGTH_SHORT).show();

        lv = (ListView)findViewById(R.id.lv);
        spots = new ArrayList<String>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cities").child(city).child("Spots");
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Name = String.valueOf(ds.child("name").getValue());
                    spots.add(Name);
                    update();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(City.this, error.toException().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        Intent i = new Intent(this, Spot.class);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                i.putExtra("spot", selectedItem);
                i.putExtra("city", city);
                startActivity(i);

//                Toast.makeText(City.this, selectedItem, Toast.LENGTH_SHORT).show();
//                textView.setText("The best football player is : " + selectedItem);
            }
        });

    }

    private void update() {
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, spots);
        lv.setAdapter(adp);
    }
}