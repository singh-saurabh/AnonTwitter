package com.buildinglabs.saurabh.snowhigh.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public int photoid;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, int photoid) {
        this.username = username;
        this.email = email;
        this.photoid= photoid;
    }

}
// [END blog_user_class]
