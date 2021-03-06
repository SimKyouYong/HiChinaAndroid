package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.adapter.SchoolInfo_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.obj.SchoolInfoObj;

public class ChinaLifeActivity extends ActivityEx {

    private EditText id_edit , email_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private ListView list_number;
    private SchoolInfo_Adapter m_Adapter;
    private ArrayList<SchoolInfoObj> arr;
    private String [][] Object_Array;
    private int tab_position = 0;
    private String CATEGORY_1 = "";
    private Button tab3_btn , tab4_btn , tab5_btn , tab6_btn, tab7_btn, tab8_btn, tab9_btn, tab10_btn;
    String val [] = {"KEY_INDEX","PARENT_KEYINDEX", "BODY", "SELF_ID", "GOOD_EA",
            "COMMENT_EA" ,"DATE", "IMG_1" , "IMG_2", "IMG_3",
            "IMG_4","IMG_5","IMG_6","IMG_7","IMG_8",
            "IMG_9","IMG_10","SELF_ID_KEY_INDEX",
            "CATEGORY_1" , "GOOD_FLAG","COUNT"};
    @Override
    public void onResume() {

        postSelAPI(CATEGORY_1);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinalife);
        list_number = (ListView)findViewById(R.id.list_number);

        tab3_btn = (Button)findViewById(R.id.tab3_btn);
        tab4_btn = (Button)findViewById(R.id.tab4_btn);
        tab5_btn = (Button)findViewById(R.id.tab5_btn);
        tab6_btn = (Button)findViewById(R.id.tab6_btn);
        tab7_btn = (Button)findViewById(R.id.tab7_btn);
        tab8_btn = (Button)findViewById(R.id.tab8_btn);
        tab9_btn = (Button)findViewById(R.id.tab9_btn);
        tab10_btn = (Button)findViewById(R.id.tab10_btn);

        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab1_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab2_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab3_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab4_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab5_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab6_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab7_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab8_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab9_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab10_btn).setOnClickListener(btnListener);

    }
    private void postSelAPI(String CATEGORY_1){
        customProgressPop();
        arr = new ArrayList<SchoolInfoObj>();

        map.put("url", DEFINE.SERVER_URL + "CHINALIFE_SELECT.php");
        map.put("TAG", ""+tab_position);
        map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
        map.put("PARENTS_FLAG", "2");
        map.put("CATEGORY_1", CATEGORY_1);


        mThread = new AccumThread(this, mAfterAccum , map , 1 , 0 , val);
        mThread.start();		//스레드 시작!!
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.top_left_btn:
                    Log.e("SKY"  , "--top_left_btn--");
                    finish();
                    break;
                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    Intent intent = new Intent(ChinaLifeActivity.this, ChinaLifeWriteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tab1_btn:
                    Log.e("SKY"  , "--tab1_btn--");
                    tab_position = 0;
                    postSelAPI("");
                    break;
                case R.id.tab2_btn:
                    Log.e("SKY"  , "--tab2_btn--");
                    tab_position = 1;
                    postSelAPI("");
                    break;
                case R.id.tab3_btn:
                    Log.e("SKY"  , "--tab3_btn--");
                    tab_position = 2;
                    CATEGORY_1 = tab3_btn.getText().toString();
                    postSelAPI(CATEGORY_1);
                    break;
                case R.id.tab4_btn:
                    Log.e("SKY"  , "--tab4_btn--");
                    tab_position = 3;
                    CATEGORY_1 = tab4_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;
                case R.id.tab5_btn:
                    Log.e("SKY"  , "--tab5_btn--");
                    tab_position = 4;
                    CATEGORY_1 = tab5_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;

                case R.id.tab6_btn:
                    Log.e("SKY"  , "--tab6_btn--");
                    tab_position = 5;
                    CATEGORY_1 = tab6_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;
                case R.id.tab7_btn:
                    Log.e("SKY"  , "--tab7_btn--");
                    tab_position = 6;
                    CATEGORY_1 = tab7_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;
                case R.id.tab8_btn:
                    Log.e("SKY"  , "--tab8_btn--");
                    tab_position = 7;
                    CATEGORY_1 = tab8_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;
                case R.id.tab9_btn:
                    Log.e("SKY"  , "--tab9_btn--");
                    tab_position = 8;
                    CATEGORY_1 = tab9_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;
                case R.id.tab10_btn:
                    Log.e("SKY"  , "--tab10_btn--");
                    tab_position = 9;
                    CATEGORY_1 = tab10_btn.getText().toString();

                    postSelAPI(CATEGORY_1);
                    break;
            }
        }
    };
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

                m_Adapter = new SchoolInfo_Adapter(ChinaLifeActivity.this ,arr, mAfterAccum);
                // Xml에서 추가한 ListView 연결
                list_number.setOnItemClickListener(mItemClickListener);
                // ListView에 어댑터 연결
                list_number.setAdapter(m_Adapter);

            }
        }
    };
    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position,
                                long id) {
            Intent board = new Intent(getApplicationContext(), ChinaLifeDetailActivity.class);
            board.putExtra("Object", arr.get(position));
            startActivity(board);
        }
    };
}
