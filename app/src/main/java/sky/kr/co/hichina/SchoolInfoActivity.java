package sky.kr.co.hichina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private int tab_position = 0;
    @Override
    public void onResume() {

        postSelAPI();
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolinfo);
        list_number = (ListView)findViewById(R.id.list_number);

        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab1_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab2_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab3_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab4_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab5_btn).setOnClickListener(btnListener);

    }
    private void postSelAPI(){
        customProgressPop();
        arr = new ArrayList<SchoolInfoObj>();
        String val [] = {"KEY_INDEX","PARENT_KEYINDEX", "BODY", "SELF_ID", "GOOD_EA",
                "COMMENT_EA" ,"DATE", "IMG_1" , "IMG_2", "IMG_3",
                "IMG_4","IMG_5","IMG_6","IMG_7","IMG_8",
                "IMG_9","IMG_10","SELF_ID_KEY_INDEX",
                "CATEGORY_1"};
        map.put("url", DEFINE.SERVER_URL + "BOARD_SELECT.php");
        map.put("TAG", ""+tab_position);
        mThread = new AccumThread(this, mAfterAccum , map , 1 , 0 , val);
        mThread.start();		//스레드 시작!!
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
                    tab_position = 0;
                    postSelAPI();
                    //Toast.makeText(SchoolInfoActivity.this, "준비중..", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tab2_btn:
                    Log.e("SKY"  , "--tab2_btn--");
                    tab_position = 1;
                    postSelAPI();
                    //Toast.makeText(SchoolInfoActivity.this, "준비중..", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tab3_btn:
                    Log.e("SKY"  , "--tab3_btn--");
                    tab_position = 2;
                    postSelAPI();
                    //Toast.makeText(SchoolInfoActivity.this, "준비중..", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tab4_btn:
                    Log.e("SKY"  , "--tab4_btn--");
                    tab_position = 3;
                    postSelAPI();
                    //Toast.makeText(SchoolInfoActivity.this, "준비중..", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tab5_btn:
                    Log.e("SKY"  , "--tab5_btn--");
                    tab_position = 4;
                    postSelAPI();
                    //Toast.makeText(SchoolInfoActivity.this, "준비중..", Toast.LENGTH_SHORT).show();
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
                        arr.add(new SchoolInfoObj(Object_Array[0][i],Object_Array[1][i], Object_Array[2][i], Object_Array[3][i],Object_Array[4][i],
                                Object_Array[5][i], Object_Array[6][i], Object_Array[7][i],Object_Array[8][i],Object_Array[9][i],
                                Object_Array[10][i], Object_Array[11][i],Object_Array[12][i], Object_Array[13][i], Object_Array[14][i],
                                Object_Array[15][i],Object_Array[16][i], Object_Array[17][i] , Object_Array[18][i]));
                    }
                }

                m_Adapter = new SchoolInfo_Adapter(SchoolInfoActivity.this ,arr, mAfterAccum);
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
            Intent board = new Intent(getApplicationContext(), SchoolInfoDetailActivity.class);
            board.putExtra("Object", arr.get(position));
            startActivity(board);
        }
    };
}
