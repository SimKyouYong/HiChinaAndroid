package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.adapter.SchoolInfo_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.obj.SchoolInfoObj;

public class SchoolInfoActivity extends ActivityEx {

    private EditText id_edit , email_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private ListView list_number;
    private SchoolInfo_Adapter m_Adapter;
    private ArrayList<SchoolInfoObj> arr;
    private String [][] Object_Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinfo);
        list_number = (ListView)findViewById(R.id.list_number);

//
//        id_edit = (EditText)findViewById(R.id.id_edit);
//        email_edit = (EditText)findViewById(R.id.email_edit);
//
//        id_edit.setText("snap40");
//        email_edit.setText("snap0425@gmail.com");

        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab1_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab2_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab3_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab4_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab5_btn).setOnClickListener(btnListener);


        arr = new ArrayList<SchoolInfoObj>();
        String val [] = {"Key_Index","Title", "Body", "Date", "Comment_ea",
                "Comment_id" ,"Comment_nickname", "Image_url" , "Image_urlone", "Image_urltwo",
                "Image_url4","Image_url5","Image_url6","Image_url7","Image_url8",
                "Image_url9","Image_url10",
                "Self_id", "Self_nickname", "choo","City","Tag" ,
                "Meet_day" , "Meet_time" ,	"Meet_place", 	"Meet_etc" , 	"Meet_etc1" ,
                "Meet_memo" , 	"Meet_phone" , 	"Meet_point" , "Country" ,"Danger",
                "Tour_compy","Tour_type","Price","Address","Sale_st",
                "Sale_end","Menu","Room_type" , "Good" , "My_img"};
        map.put("url", DEFINE.SERVER_URL + "HellowTalkSel.php");
        mThread = new AccumThread(this, mAfterAccum , map , 1 , 0 , val);


//        m_Adapter = new SchoolInfo_Adapter( SchoolInfoActivity.this , arr , mAfterAccum);
//        list_number.setAdapter(m_Adapter);
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.top_left_btn:
                    Log.e("SKY"  , "--top_left_btn--");
                    break;
                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    Intent intent = new Intent(SchoolInfoActivity.this, WriteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab1_btn:
                    Log.e("SKY"  , "--tab1_btn--");
                    break;
                case R.id.tab2_btn:
                    Log.e("SKY"  , "--tab2_btn--");
                    break;
                case R.id.tab3_btn:
                    Log.e("SKY"  , "--tab3_btn--");
                    break;
                case R.id.tab4_btn:
                    Log.e("SKY"  , "--tab4_btn--");
                    break;
                case R.id.tab5_btn:
                    Log.e("SKY"  , "--tab5_btn--");
                    break;
            }
        }
    };
    Handler mAfterAccum = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
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
//                        arr.add(new HellowTalkobj(Object_Array[0][i],Object_Array[1][i], Object_Array[2][i], Object_Array[3][i],Object_Array[4][i],
//                                Object_Array[5][i], Object_Array[6][i], Object_Array[7][i],Object_Array[8][i],Object_Array[9][i],
//                                Object_Array[10][i], Object_Array[11][i],Object_Array[12][i], Object_Array[13][i], Object_Array[14][i],
//                                Object_Array[15][i],Object_Array[16][i], Object_Array[17][i], Object_Array[18][i],Object_Array[19][i],
//                                Object_Array[20][i], Object_Array[21][i], Object_Array[22][i], Object_Array[23][i],Object_Array[24][i],
//                                Object_Array[25][i], Object_Array[26][i], Object_Array[27][i], Object_Array[28][i],	Object_Array[29][i],
//                                Object_Array[30][i], Object_Array[31][i], Object_Array[32][i], Object_Array[33][i],	Object_Array[34][i],
//                                Object_Array[35][i], Object_Array[36][i], Object_Array[37][i], Object_Array[38][i],	Object_Array[39][i],
//                                Object_Array[40][i], Object_Array[41][i]
//                        ));
                    }
                }

//                m_Adapter = new HellowTalkAdapter(arr , mContext , mAfterAccum);
//                // Xml에서 추가한 ListView 연결
//                m_ListView.setOnItemClickListener(mItemClickListener);
//                // ListView에 어댑터 연결
//                m_ListView.setAdapter(m_Adapter);

            }
        }
    };
}
