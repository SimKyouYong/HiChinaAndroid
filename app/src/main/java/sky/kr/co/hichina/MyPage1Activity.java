package sky.kr.co.hichina;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.adapter.Mypage1_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.obj.Mypag1eObj;

public class MyPage1Activity extends ActivityEx {
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    private ArrayList<Mypag1eObj> arr = new ArrayList<Mypag1eObj>();
    private Mypage1_Adapter m_Adapter;

    private String[] items1 = {"기타", "증명서발급", "학생사무실" , "학부모" , "수업/선생님" , "건의사항" , "실용중국어" ,"생활Tip", "동영상", "이슈" , "교통.물류" , "증권.은행" , "오락/음식" , "부동산","기타" , "주방가전", "주방용품", "거실가구", "가습기/선풍기", "수납함", "세탁관련용품", "의류/가방", "화장품", "기타"};
    private ListView list_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage1);


        customProgressPop();
        map.put("url", DEFINE.SERVER_URL + "MYPAGE1_SELECT.php");
        map.put("MEMBER_KEY_INDEX", Check_Preferences.getAppPreferences(this , "KEY_INDEX"));
        //스레드 생성
        mThread = new AccumThread(this , mAfterAccum , map , 0 , 0 , null);
        mThread.start();		//스레드 시작!!

        list_number = (ListView)findViewById(R.id.list_number);

    }
    Handler mAfterAccum = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            customProgressClose();
            if (msg.arg1  == 0 ) {
                String res = (String)msg.obj;
                Log.e("CHECK" , "RESULT  -> " + res);
                String val[] = res.split(",");

                for (int i= 0; i < val.length; i++){
                    if (val[i].equals("1")){
                        //add
                        arr.add(new Mypag1eObj(items1[i] , val[i]));
                    }
                }

                m_Adapter = new Mypage1_Adapter(MyPage1Activity.this ,arr, mAfterAccum);
                // Xml에서 추가한 ListView 연결
                //list_number.setOnItemClickListener(mItemClickListener);
                // ListView에 어댑터 연결
                list_number.setAdapter(m_Adapter);


            }
        }
    };
}
