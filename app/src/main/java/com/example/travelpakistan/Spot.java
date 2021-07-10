package com.example.travelpakistan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Spot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        Intent intent = getIntent();
        String spot = intent.getStringExtra("spot");
        String city = intent.getStringExtra("city");
        TextView spotName = (TextView)findViewById(R.id.spotName);
        TextView detailTV = (TextView)findViewById(R.id.detail);
        spotName.setText(spot);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cities").child(city).child("Spots");
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String url = snapshot.child(spot).child("image").getValue().toString();
                String detail = snapshot.child(spot).child("detail").getValue().toString();

                ImageView img = (ImageView)findViewById(R.id.img);
                detailTV.setText(detail);

                Picasso.get().load(url).fit().placeholder(R.drawable.loading).into(img);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Spot.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDirections(View view) {
        Intent intent = getIntent();
        String spot = intent.getStringExtra("spot");
        String city = intent.getStringExtra("city");

        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("city",city);
        i.putExtra("spot",spot);
        startActivity(i);
    }

    public void getWeather(View view) {
        Intent intent = getIntent();
        String spot = intent.getStringExtra("spot");
        String city = intent.getStringExtra("city");

        Intent i = new Intent(this, Weather.class);
        i.putExtra("city",city);
        i.putExtra("spot",spot);
        startActivity(i);
    }
}