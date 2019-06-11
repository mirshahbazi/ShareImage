package com.mam.shareView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mam.shareView.tools.ShareUtils;

/**
 * *
 * *          ____  ____ _____ ___   ____
 * *         | \ \ / / |/ _  || \ \ / / |
 * *         | |\ V /| | (_| || |\ V /| |
 * *         |_| \_/ |_|\__,_||_| \_/ |_|
 * *
 * Created by Mohammad Ali Mirshahbazi
 */
public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDemoView();
    }

    private void setupDemoView() {
        // find the image to share holder (ImageView)
        ImageView demoImageView = findViewById(R.id.demo_image_view);
        final ConstraintLayout main = findViewById(R.id.main);

        // get bitmap from imageView
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) demoImageView.getDrawable();
//        if (bitmapDrawable != null)
//            bitmap = bitmapDrawable.getBitmap();


        //get bitmap from VectorDrawable

        VectorDrawableCompat humIbCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_mam, null);
        bitmap = ShareUtils.getBitmapFromVectorDrawable(this,humIbCompat);


        // set onClick listener to demo share button
        findViewById(R.id.share_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform share image
                ShareUtils.shareImage(MainActivity.this, bitmap, "Share Image Demo Message");
            }
        });

        findViewById(R.id.share_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform share View
                ShareUtils.shareImage(MainActivity.this, ShareUtils.getBitmapFromView(main), "Share View Demo Message");
            }
        });
    }


}
