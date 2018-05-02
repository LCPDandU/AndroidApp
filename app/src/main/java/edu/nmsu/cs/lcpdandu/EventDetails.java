package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.Date;

public class EventDetails extends AppCompatActivity {

    private TextView TitleTextView;
    private TextView LocationTextView;
    private TextView DateTextView;
    private TextView TimeTextView;
    private TextView AMPMTextView;
    private TextView DescriptionTextView;

    private NetworkImageView SelectedMediaImageView;
    private NetworkImageView Media1ImageView;
    private NetworkImageView Media2ImageView;
    private NetworkImageView Media3ImageView;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        TitleTextView = (TextView) findViewById(R.id.events_list_details_title);
        TitleTextView.setText(getIntent().getStringExtra("Title"));

        LocationTextView = (TextView) findViewById(R.id.events_list_details_location);
        LocationTextView.setText(getIntent().getStringExtra("Location"));

        DateTextView = (TextView) findViewById(R.id.events_list_details_date);
        DateTextView.setText(getIntent().getStringExtra("Date"));

        TimeTextView = (TextView) findViewById(R.id.events_list_details_time);
        TimeTextView.setText(getIntent().getStringExtra("Time"));

        AMPMTextView = (TextView) findViewById(R.id.events_list_details_ampm);
        AMPMTextView.setText(getIntent().getStringExtra("AMPM"));

        DescriptionTextView = (TextView) findViewById(R.id.events_list_details_description);
        DescriptionTextView.setText(getIntent().getStringExtra("Description"));

        String Media1Url = getIntent().getStringExtra("Media1");
        String Media2Url = getIntent().getStringExtra("Media2");
        String Media3Url = getIntent().getStringExtra("Media3");

        context = this;

        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        SelectedMediaImageView = (NetworkImageView) findViewById(R.id.events_list_details_media);
        SelectedMediaImageView.setImageUrl(Media1Url, mImageLoader);

        Media1ImageView = (NetworkImageView) findViewById(R.id.events_list_details_media1);
        Media1ImageView.setImageUrl(Media1Url, mImageLoader);

        Media2ImageView = (NetworkImageView) findViewById(R.id.events_list_details_media2);
        Media2ImageView.setImageUrl(Media2Url, mImageLoader);

        Media3ImageView = (NetworkImageView) findViewById(R.id.events_list_details_media3);
        Media3ImageView.setImageUrl(Media3Url, mImageLoader);

    }
}
