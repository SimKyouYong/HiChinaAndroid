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
import sky.kr.co.hichina.adapter.PersonStudy_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.obj.PersonStudyObj;

public class PersonStudyActivity extends ActivityEx {
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private ListView list_number;
    private EditText id_edit , email_edit;
    private ArrayList<PersonStudyObj> arr;
    private String [][] Object_Array;
    private PersonStudy_Adapter m_Adapter;

    String val [] = {"KEY_INDEX","PARENT_KEYINDEX", "TITLE", "BODY", "SELF_ID", "GOOD_EA",
            "COMMENT_EA" ,"DATE", "IMG_1" , "IMG_2", "IMG_3",
            "IMG_4","IMG_5","IMG_6","IMG_7","IMG_8",
            "IMG_9","IMG_10","SELF_ID_KEY_INDEX",
            "CATEGORY_1" ,"CATEGORY_2" ,"CATEGORY_3" , "GOOD_FLAG","COUNT"};
    @Override
    public void onResume() {

        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personstudy);

        list_number = (ListView)findViewById(R.id.list_number);



        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);

        //postSelAPI();
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
                    Intent intent = new Intent(PersonStudyActivity.this, PersonStudyWriteActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    private void postSelAPI(){
        customProgressPop();
        arr = new ArrayList<PersonStudyObj>();

        map.put("url", DEFINE.SERVER_URL + "POERSON_STUDY_SELECT.php");
        map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
        map.put("PARENTS_FLAG", "1");

        map.put("CATEGORY_1", "1");
        map.put("CATEGORY_2", "1");
        map.put("CATEGORY_3", "1");

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

                        arr.add(new PersonStudyObj(Object_Array[0][i],Object_Array[1][i], Object_Array[2][i], Object_Array[3][i],Object_Array[4][i],
                                Object_Array[5][i], Object_Array[6][i], Object_Array[7][i],Object_Array[8][i],Object_Array[9][i],
                                Object_Array[10][i], Object_Array[11][i],Object_Array[12][i], Object_Array[13][i], Object_Array[14][i],
                                Object_Array[15][i],Object_Array[16][i], Object_Array[17][i] , Object_Array[18][i], Object_Array[19][i]
                                , Object_Array[20][i], Object_Array[21][i], Object_Array[22][i],count));
                        Log.e("SKY" , "GOOD_FLAG :: "  + Object_Array[19][i]);

                    }
                }

                m_Adapter = new PersonStudy_Adapter(PersonStudyActivity.this ,arr, mAfterAccum);
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
