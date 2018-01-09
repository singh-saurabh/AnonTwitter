package com.buildinglabs.saurabh.snowhigh.models;

import com.google.firebase.database.IgnoreExtraProperties;

// [START comment_class]
@IgnoreExtraProperties
public class Comment {

    public String uid;
    public String text;
    public int photoid_comment;

    public Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Comment(String uid, String text,int photoid_comment) {
        this.uid = uid;
        this.text = text;
        this.photoid_comment=photoid_comment;
    }

}
// [END comment_class]
