package com.buildinglabs.saurabh.snowhigh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AvatarActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG="AvatarActivity";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private ImageButton Button1;
    private ImageButton Button2;
    private ImageButton Button3;
    private ImageButton Button4;
    private ImageButton Button5;
    private ImageButton Button6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Button1= findViewById(R.id.imageButton1);
        Button2= findViewById(R.id.imageButton2);
        Button3= findViewById(R.id.imageButton3);
        Button4= findViewById(R.id.imageButton4);
        Button5= findViewById(R.id.imageButton5);
        Button6= findViewById(R.id.imageButton6);

        Button1.setOnClickListener( this);
        Button2.setOnClickListener( this);
        Button3.setOnClickListener( this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener( this);
        Button6.setOnClickListener( this);
    }

    public void AvatarGet(int i){
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("photoid").setValue(i);
        startActivity(new Intent(AvatarActivity.this, MainActivity.class));
        finish();
    }
    public void onClick(View v) {
        int i = v.getId();
        if (i==R.id.imageButton1) {
            AvatarGet(1);
        }
        if (i==R.id.imageButton2) {
            AvatarGet(2);
        }
        if (i==R.id.imageButton3) {
            AvatarGet(3);
        }
        if (i==R.id.imageButton4) {
            AvatarGet(4);
        }
        if (i==R.id.imageButton5) {
            AvatarGet(5);
        }
        if (i==R.id.imageButton6) {
            AvatarGet(6);
        }

    }

}
