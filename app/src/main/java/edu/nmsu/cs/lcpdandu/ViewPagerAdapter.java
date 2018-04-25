package edu.nmsu.cs.lcpdandu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;//context of current state of the application/object.
    private LayoutInflater mLayoutInflater;//Instantiates a layout XML file into its corresponding View objects.

    //Images for Image Slider
    private int [] images = {R.drawable.annie_spratt_441579_unsplash, R.drawable.james_baldwin_276255_unsplash, R.drawable.david_sola_530108_unsplash};

    //Texts for ImageSlider
    private int [] texts = {R.string.slide_text_1, R.string.slide_text_2, R.string.slide_text_3};

    public ViewPagerAdapter(Context context) {

        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //Retrieves view in activity_main_slider
        View itemView = mLayoutInflater.inflate(R.layout.activity_main_slider, container, false);

        //Retrieves ImageView from itemView. Then sets imageView image inside of images[]
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        //Retrieves textView from itemView. Then sets textView to text inside of texts[]
        TextView textView = (TextView) itemView.findViewById(R.id.textView);
        textView.setText(texts[position]);

        //Background color is now wrapped around text inside of textView
        String text = (String) textView.getText();
        Spannable spanna = new SpannableString(text);
        spanna.setSpan(new BackgroundColorSpan(R.color.background_color),0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanna);

        //create view
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}