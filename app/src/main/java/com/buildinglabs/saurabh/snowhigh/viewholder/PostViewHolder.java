package com.buildinglabs.saurabh.snowhigh.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildinglabs.saurabh.snowhigh.R;
import com.buildinglabs.saurabh.snowhigh.models.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;
    public ImageView imageView;


    public PostViewHolder(View itemView) {
        super(itemView);

        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.post_num_stars);
        bodyView = itemView.findViewById(R.id.post_body);
        imageView =itemView.findViewById(R.id.post_author_photo);
    }

    public void bindToPost(Post post, View.OnClickListener starClickListener) {
        numStarsView.setText(String.valueOf(post.starCount));
        bodyView.setText(post.body);
        imageView.setImageResource(setCommentPhoto(post.photoid_post));
        starView.setOnClickListener(starClickListener);
    }

    public int setCommentPhoto (int i) {
        switch (i){
            case 1: return R.drawable.roundicons_01;
            case 2: return R.drawable.roundicons_02;
            case 3: return R.drawable.roundicons_03;
            case 4: return R.drawable.roundicons_04;
            case 5: return R.drawable.roundicons_05;
            case 6: return R.drawable.roundicons_06;
            default: return R.drawable.roundicons_06;
        }
    }
}
