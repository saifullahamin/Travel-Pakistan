package com.example.travelpakistan;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Cities");
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String peshURL = snapshot.child("Peshawar").child("image").getValue().toString();
            String swatURL = snapshot.child("Swat").child("image").getValue().toString();
            String kaghannaranURL = snapshot.child("Kaghan Naran").child("image").getValue().toString();
            String kumratURL = snapshot.child("Kumrat").child("image").getValue().toString();
            String galiyatURL = snapshot.child("Galiyat").child("image").getValue().toString();
            String hunzaURL = snapshot.child("Hunza").child("image").getValue().toString();
            String skarduURL = snapshot.child("Skardu").child("image").getValue().toString();
            String gilgitURL = snapshot.child("Gilgit").child("image").getValue().toString();
            String murreeURL = snapshot.child("Murree").child("image").getValue().toString();
            String lahoreURL = snapshot.child("Lahore").child("image").getValue().toString();
            String quettaURL = snapshot.child("Quetta").child("image").getValue().toString();
            String gwadarURL = snapshot.child("Gwadar").child("image").getValue().toString();


            ImageView peshImg = (ImageView)findViewById(R.id.peshawar);
            ImageView swatImg = (ImageView)findViewById(R.id.swat);
            ImageView kaghannaranImg = (ImageView)findViewById(R.id.kaghannaran);
            ImageView kumratImg = (ImageView)findViewById(R.id.kumrat);
            ImageView galiyatImg = (ImageView)findViewById(R.id.galiyat);
            ImageView hunzaImg = (ImageView)findViewById(R.id.hunza);
            ImageView skarduImg = (ImageView)findViewById(R.id.skardu);
            ImageView gilgitImg = (ImageView)findViewById(R.id.gilgit);
            ImageView murreeImg = (ImageView)findViewById(R.id.murree);
            ImageView lahoreImg = (ImageView)findViewById(R.id.lahore);
            ImageView quettaImg = (ImageView)findViewById(R.id.quetta);
            ImageView gwadarImg = (ImageView)findViewById(R.id.gwadar);


            Picasso.get().load(peshURL).fit().placeholder(R.drawable.loading).into(peshImg);
            Picasso.get().load(swatURL).fit().placeholder(R.drawable.loading).into(swatImg);
            Picasso.get().load(kaghannaranURL).fit().placeholder(R.drawable.loading).into(kaghannaranImg);
            Picasso.get().load(kumratURL).fit().placeholder(R.drawable.loading).into(kumratImg);
            Picasso.get().load(galiyatURL).fit().placeholder(R.drawable.loading).into(galiyatImg);
            Picasso.get().load(hunzaURL).fit().placeholder(R.drawable.loading).into(hunzaImg);
            Picasso.get().load(skarduURL).fit().placeholder(R.drawable.loading).into(skarduImg);
            Picasso.get().load(gilgitURL).fit().placeholder(R.drawable.loading).into(gilgitImg);
            Picasso.get().load(murreeURL).fit().placeholder(R.drawable.loading).into(murreeImg);
            Picasso.get().load(lahoreURL).fit().placeholder(R.drawable.loading).into(lahoreImg);
            Picasso.get().load(quettaURL).fit().placeholder(R.drawable.loading).into(quettaImg);
            Picasso.get().load(gwadarURL).fit().placeholder(R.drawable.loading).into(gwadarImg);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadCity(View view) {
//        Toast.makeText(this, getResources().getResourceEntryName(view.getId()), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, City.class);
        String city = "";

        if (getResources().getResourceEntryName(view.getId()).equals("peshawarL")){
            city="Peshawar";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("swatL")){
            city="Swat";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("kaghannaranL")){
            city="Kaghan Naran";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("kumratL")){
            city="Kumrat";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("galiyatL")){
            city="Galiyat";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("hunzaL")){
            city="Hunza";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("skarduL")){
            city="Skardu";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("gilgitL")){
            city="Gilgit";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("murreeL")){
            city="Murree";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("lahoreL")){
            city="Lahore";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("quettaL")){
            city="Quetta";
        }
        else if (getResources().getResourceEntryName(view.getId()).equals("gwadarL")){
            city="Gwadar";
        }

        i.putExtra("city", city);
        startActivity(i);

    }
}