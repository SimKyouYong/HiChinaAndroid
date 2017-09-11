package sky.kr.co.hichina;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.adapter.SchoolInfoComment_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.common.H5ImageLoader;
import sky.kr.co.hichina.obj.SchoolInfoObj;

public class SchoolInfoDetailActivity extends ActivityEx {

    private ListView list_number;

    SchoolInfoObj obj;
    TextView txt_name , txt_body , date_tv , comment_tv , good_tv;


    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private SchoolInfoComment_Adapter m_Adapter;
    private ArrayList<SchoolInfoObj> arr;
    private String [][] Object_Array;
    private int tab_position = 0;
    private EditText comment_eidt;
    private Button top_right_btn;

    String val [] = {"KEY_INDEX","PARENT_KEYINDEX", "BODY", "SELF_ID", "GOOD_EA",
            "COMMENT_EA" ,"DATE", "IMG_1" , "IMG_2", "IMG_3",
            "IMG_4","IMG_5","IMG_6","IMG_7","IMG_8",
            "IMG_9","IMG_10","SELF_ID_KEY_INDEX",
            "CATEGORY_1", "GOOD_FLAG","COUNT"};

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinfodetail);


        txt_name = (TextView)findViewById(R.id.txt_name);
        txt_body = (TextView)findViewById(R.id.txt_body);
        date_tv = (TextView)findViewById(R.id.date_tv);
        comment_tv = (TextView)findViewById(R.id.comment_tv);
        good_tv = (TextView)findViewById(R.id.good_tv);
        list_number = (ListView)findViewById(R.id.list_number);
        comment_eidt = (EditText)findViewById(R.id.comment_eidt);
        top_right_btn = (Button)findViewById(R.id.top_right_btn);


        Bundle bundle = getIntent().getExtras();
        obj = bundle.getParcelable("Object");

        Log.e("SKY" , "URL1 :: " + DEFINE.SERVER_IMG_URL + obj.getIMG_1());
        Log.e("SKY" , "getGOOD_FLAG :: " + obj.getGOOD_FLAG());
        Log.e("SKY" , "GetCount :: " + obj.getCOUNT());


        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ImagePagerAdapter(this));

        txt_name.setText(obj.getSELF_ID());
        txt_body.setText(obj.getBODY());
        date_tv.setText(obj.getDATE());
        comment_tv.setText("댓글 : " + obj.getCOMMENT_EA());
        good_tv.setText("좋아요 : " + obj.getGOOD_EA());

        postCommetSelAPI();

        findViewById(R.id.comment_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.comment_btn:
                    Log.e("SKY"  , "--comment_btn--");
                    customProgressPop();
                    arr = new ArrayList<SchoolInfoObj>();
                    map.put("url", DEFINE.SERVER_URL + "BOARD_COMMENT_WRITE.php");
                    map.clear();
                    map.put("PARENT_KEYINDEX", ""+obj.getKEY_INDEX());
                    map.put("BODY", ""+comment_eidt.getText().toString());
                    map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"MEMBER_ID"));
                    map.put("SELF_ID_KEY_INDEX",Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
                    mThread = new AccumThread(SchoolInfoDetailActivity.this , mAfterAccum , map , 0 , 1 , null);
                    mThread.start();


                    break;
                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    customProgressPop();
                    map.clear();
                    map.put("url", DEFINE.SERVER_URL + "BOARD_GOOD.php");
                    String Flag = "";
                    if (obj.getGOOD_FLAG().trim().equals("TRUE")){
                        Flag = "OFF";
                    }else{
                        Flag = "ON";
                    }
                    map.put("GOOD", ""+Flag);           //ON 좋아요  , OFF 좋아요 해제
                    map.put("PARENTS_FLAG", "0");
                    map.put("PARENT_KEYINDEX", ""+obj.getKEY_INDEX());

                    map.put("SELF_ID_KEY_INDEX",Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
                    mThread = new AccumThread(SchoolInfoDetailActivity.this , mAfterAccum , map , 0 , 3 , null);
                    mThread.start();
                    break;

            }
        }
    };
    private void postCommetSelAPI(){
        customProgressPop();
        arr = new ArrayList<SchoolInfoObj>();
        map.put("url", DEFINE.SERVER_URL + "BOARD_COMMENT_SELECT.php");
        map.put("KEY_INDEX", ""+obj.getKEY_INDEX());
        mThread = new AccumThread(this, mAfterAccum , map , 1 , 0 , val);
        mThread.start();		//스레드 시작!!
    }
    Handler mAfterAccum = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            customProgressClose();
            if (msg.arg1  == 0 ) {
                arr.clear();
                //				Map<String, ArrayList<String>> Object_Array = new HashMap<String, ArrayList<String>>();
                Object_Array = (String [][]) msg.obj;
                if (Object_Array.length == 0) {
                    return;
                }
                //				Log.e("CHECK" ,"**********************  --->" + Object_Array[0].length);
                for (int i = 0; i < Object_Array.length; i++) {
                    for (int j = 0; j < Object_Array[0].length; j++) {
                        Log.e("CHECK" ,"value----> ---> Object_Array [" +i+"]["+j+"]"+  Object_Array[i][j]);
                    }
                }
                for (int i = 0; i < (Object_Array[0].length); i++){
                    if (Object_Array[0][i] != null) {

                        arr.add(new SchoolInfoObj(Object_Array[0][i],Object_Array[1][i], Object_Array[2][i], Object_Array[3][i],Object_Array[4][i],
                                Object_Array[5][i], Object_Array[6][i], Object_Array[7][i],Object_Array[8][i],Object_Array[9][i],
                                Object_Array[10][i], Object_Array[11][i],Object_Array[12][i], Object_Array[13][i], Object_Array[14][i],
                                Object_Array[15][i],Object_Array[16][i], Object_Array[17][i] , Object_Array[18][i], Object_Array[19][i],0));

                    }
                }
                m_Adapter = new SchoolInfoComment_Adapter(SchoolInfoDetailActivity.this ,arr, mAfterAccum);
                // Xml에서 추가한 ListView 연결
                //list_number.setOnItemClickListener(mItemClickListener);
                // ListView에 어댑터 연결
                list_number.setAdapter(m_Adapter);
            }else if(msg.arg1  == 1){
                String res = (String)msg.obj;
                Log.e("CHECK" , "RESULT  -> " + res);
                Toast.makeText(getApplicationContext() , "등록 완료" , Toast.LENGTH_SHORT).show();
                comment_eidt.setText("");
                postSelAPI();

            }else if(msg.arg1  == 2){
                arr.clear();
                //				Map<String, ArrayList<String>> Object_Array = new HashMap<String, ArrayList<String>>();
                Object_Array = (String [][]) msg.obj;
                if (Object_Array.length == 0) {
                    return;
                }
                //				Log.e("CHECK" ,"**********************  --->" + Object_Array[0].length);
                for (int i = 0; i < Object_Array.length; i++) {
                    for (int j = 0; j < Object_Array[0].length; j++) {
                        Log.e("CHECK" ,"value----> ---> Object_Array [" +i+"]["+j+"]"+  Object_Array[i][j]);
                    }
                }
                for (int i = 0; i < (Object_Array[0].length); i++){
                    if (Object_Array[0][i] != null) {
                        int count = 0;
                        if (Object_Array[7][i].length() != 0)
                            count++;
                        if (Object_Array[8][i].length() != 0)
                            count++;
                        if (Object_Array[9][i].length() != 0)
                            count++;
                        if (Object_Array[10][i].length() != 0)
                            count++;
                        if (Object_Array[11][i].length() != 0)
                            count++;
                        if (Object_Array[12][i].length() != 0)
                            count++;
                        if (Object_Array[13][i].length() != 0)
                            count++;
                        if (Object_Array[14][i].length() != 0)
                            count++;
                        if (Object_Array[15][i].length() != 0)
                            count++;
                        if (Object_Array[16][i].length() != 0)
                            count++;

                        arr.add(new SchoolInfoObj(Object_Array[0][i],Object_Array[1][i], Object_Array[2][i], Object_Array[3][i],Object_Array[4][i],
                                Object_Array[5][i], Object_Array[6][i], Object_Array[7][i],Object_Array[8][i],Object_Array[9][i],
                                Object_Array[10][i], Object_Array[11][i],Object_Array[12][i], Object_Array[13][i], Object_Array[14][i],
                                Object_Array[15][i],Object_Array[16][i], Object_Array[17][i] , Object_Array[18][i], Object_Array[19][i],count));
                        Log.e("SKY" , "GOOD_FLAG :: "  + Object_Array[19][i]);

                    }
                }
                obj = arr.get(0);
                txt_name.setText(obj.getSELF_ID());
                txt_body.setText(obj.getBODY());
                date_tv.setText(obj.getDATE());
                comment_tv.setText("댓글 : " + obj.getCOMMENT_EA());
                good_tv.setText("좋아요 : " + obj.getGOOD_EA());
                postCommetSelAPI();
            }else if(msg.arg1  == 3){
                //좋아요
                String res = (String)msg.obj;
                Log.e("CHECK" , "RESULT  -> " + res);
                Toast.makeText(getApplicationContext() , "등록 완료" , Toast.LENGTH_SHORT).show();
                comment_eidt.setText("");
                postSelAPI();

            }
        }
    };
    private void postSelAPI(){
        customProgressPop();
        arr = new ArrayList<SchoolInfoObj>();
        map.clear();
        map.put("url", DEFINE.SERVER_URL + "BOARD_SELECT_IN.php");
        map.put("KEY_INDEX", ""+obj.getKEY_INDEX());
        map.put("TAG", ""+tab_position);
        map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
        map.put("PARENTS_FLAG", "0");
        mThread = new AccumThread(this, mAfterAccum , map , 1 , 2 , val);
        mThread.start();		//스레드 시작!!
    }
    private class ImagePagerAdapter extends PagerAdapter {

        private LayoutInflater mInflater;

        public ImagePagerAdapter(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return obj.getCOUNT();
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
