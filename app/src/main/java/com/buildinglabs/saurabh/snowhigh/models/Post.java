package com.buildinglabs.saurabh.snowhigh.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {

    public String uid;
    public String body;
    public int photoid_post;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String body, int photoid_post) {
        this.uid = uid;
        this.body = body;
        this.photoid_post = photoid_post;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("photoid_post", photoid_post);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
