package com.desafiolanchonete.lanchesja.infrastructure;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public abstract class ImageHelper {

    public static void loadImageByUrl(final String imageUrl, final ImageView imageView, final int placeHolderImageId) {
        Picasso.Builder builder = new Picasso.Builder(imageView.getContext());
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                /* Do nothing */
            }
        });
        builder.downloader(new OkHttpDownloader(imageView.getContext()));
        builder.build().load(imageUrl).placeholder(placeHolderImageId).into(imageView);
    }

}
