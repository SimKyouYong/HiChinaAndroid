package sky.kr.co.hichina;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.common.H5ImageLoader;
import sky.kr.co.hichina.obj.SchoolInfoObj;

public class SchoolInfoDetailActivity extends ActivityEx {


    SchoolInfoObj obj;
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinfodetail);


        Bundle bundle = getIntent().getExtras();
        obj = bundle.getParcelable("Object");

        Log.e("SKY" , "URL1 :: " + DEFINE.SERVER_IMG_URL + obj.getIMG_1());
        Log.e("SKY" , "GetCount :: " + obj.GetCount());


        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ImagePagerAdapter(this));

    }
    private class ImagePagerAdapter extends PagerAdapter {

        private LayoutInflater mInflater;

        public ImagePagerAdapter(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return obj.GetCount();
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            Log.e("SKY" , "position  :: " + position);

            View v = null;
            v = mInflater.inflate(R.layout.viewpager_img, null);
            NetworkImageView iv_one = (NetworkImageView)v.findViewById(R.id.iv_one);
            String IMG = "";
            switch (position){
                case 0:
                    IMG = obj.getIMG_1();
                    break;
                case 1:
                    IMG = obj.getIMG_2();
                    break;
                case 2:
                    IMG = obj.getIMG_3();
                    break;
                case 3:
                    IMG = obj.getIMG_4();
                    break;
                case 4:
                    IMG = obj.getIMG_5();
                    break;
                case 5:
                    IMG = obj.getIMG_6();
                    break;
                case 6:
                    IMG = obj.getIMG_7();
                    break;
                case 7:
                    IMG = obj.getIMG_8();
                    break;
                case 8:
                    IMG = obj.getIMG_9();
                    break;
                case 9:
                    IMG = obj.getIMG_10();
                    break;

            }
            Log.e("SKY" , "URL :: " + DEFINE.SERVER_IMG_URL + IMG);
            H5ImageLoader.getInstance(getApplicationContext()).set(DEFINE.SERVER_IMG_URL + IMG, iv_one);
            ((ViewPager)pager).addView(v, 0);

            return v;
        }

        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager)pager).removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }
        @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        @Override public Parcelable saveState() { return null; }
        @Override public void startUpdate(View arg0) {}
        @Override public void finishUpdate(View arg0) {}
    }
}
