package com.buildinglabs.saurabh.snowhigh;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.buildinglabs.saurabh.snowhigh.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.buildinglabs.saurabh.snowhigh.models.Post;
import com.buildinglabs.saurabh.snowhigh.models.User;

import java.util.HashMap;
import java.util.Map;

public class NewPostActivity extends BaseActivity {

    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;
    private DatabaseReference mUser;

    private EditText mBodyField;
    private ImageView mImage;
    private FloatingActionButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUser=mDatabase.child("users").child(getUid());

        mBodyField = findViewById(R.id.field_body);
        mSubmitButton = findViewById(R.id.fab_submit_post);
        mImage= findViewById(R.id.new_post_image);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

@Override
public void onStart () {
        super.onStart();
        mUser.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user=dataSnapshot.getValue(User.class);
                        mImage.setImageResource(setAuthorPhoto(user.photoid));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                        Toast.makeText(NewPostActivity.this, "Failed to load image.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
}
    public int setAuthorPhoto (int i) {
        switch (i){
            case 1: return R.drawable.roundicons_01;
            case 2: return R.drawable.roundicons_02;
            case 3: return R.drawable.roundicons_03;
            case 4: return R.drawable.roundicons_04;
            case 5: return R.drawable.roundicons_05;
            case 6: return R.drawable.roundicons_06;
            default: return R.drawable.roundicons_01;
        }
    }

    private void submitPost() {
        final String body = mBodyField.getText().toString();


        // Body is required
        if (TextUtils.isEmpty(body)) {
            mBodyField.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(NewPostActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            int photoid_post=user.photoid;
                            writeNewPost(userId, body, photoid_post);
                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        mBodyField.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    private void writeNewPost(String userId, String body, int photoid_post) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, body, photoid_post);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
