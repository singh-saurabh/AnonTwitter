package com.buildinglabs.saurabh.snowhigh.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyTopPostsFragment extends com.buildinglabs.saurabh.snowhigh.fragment.PostListFragment {

    public MyTopPostsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START my_top_posts_query]
        // My top posts by number of stars
        //String myUserId = getUid();
        Query myTopPostsQuery = databaseReference.child("posts")
                .orderByChild("starCount");
        // [END my_top_posts_query]

        return myTopPostsQuery;
    }
}
