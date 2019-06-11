package com.mam.shareView.tools;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mam.shareView.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * *
 * *          ____  ____ _____ ___   ____
 * *         | \ \ / / |/ _  || \ \ / / |
 * *         | |\ V /| | (_| || |\ V /| |
 * *         |_| \_/ |_|\__,_||_| \_/ |_|
 * *
 * Created by Mohammad Ali Mirshahbazi
 */
public class ShareUtils {

    private static final String TAG = ShareUtils.class.getSimpleName();

    public static void shareImage(Context context, Bitmap bitmap, String message) {

        if (context == null || bitmap == null) {
            if (context != null)
                Toast.makeText(context, "Bitmap is NULL", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Make sure that this path String is equals than filepaths.xml => path="shared_image"
            // <cache-path name="shared_image" path="shared_image/"/>
            String path = "shared_image/";

            // save bitmap to cache directory.
            File cachePath = new File(context.getCacheDir(), path);

            // don't forget to make the directory
            if (cachePath.mkdirs()) {
                Log.i(TAG, "New path created");
            } else {
                Log.i(TAG, "New path created");
            }

            // overwrites this image every time
            String fileName = "image_to_share.jpg";                   // jpg extension
            String filePath = cachePath + "/" + fileName;
            FileOutputStream stream = new FileOutputStream(filePath); // JPEG CompressFormat
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();

            // get saved image
            File newFile = new File(cachePath, fileName);

            // get the file Uri
            Uri contentUri =
                    FileProvider.getUriForFile(
                            context,
                            context.getString(R.string.file_provider),
                            newFile);

            if (contentUri != null) {

                // create an Intent
                final Intent share = new Intent();

                // set the intent action
                share.setAction(Intent.ACTION_SEND);

                // temp permission for receiving app to read this file
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // set as new task
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // pass the newFile as Stream
                share.putExtra(Intent.EXTRA_STREAM, contentUri);

                // set the message if it's not empty nor null
                if (!TextUtils.isEmpty(message)) share.putExtra(Intent.EXTRA_TEXT, message);

                // content type
                share.setType("image/*");

                // get the chooser title
                String title = context.getString(R.string.chooser_title);

                // finally start the activity as Chooser
                context.startActivity(Intent.createChooser(share, title));
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "shareImage IOException", Toast.LENGTH_SHORT).show();
        }
    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, Drawable drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}