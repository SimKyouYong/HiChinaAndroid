package sky.kr.co.hichina;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import sky.kr.co.hichina.adapter.PersonStudy_Adapter;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.obj.PersonStudyObj;


public class BuySellActivity extends ActivityEx {
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private ListView list_number;
    private EditText id_edit , email_edit;
    private ArrayList<PersonStudyObj> arr;
    private String [][] Object_Array;
    private PersonStudy_Adapter m_Adapter;

    private String CATEGORY1="" ,CATEGORY2="" ,CATEGORY3="",CATEGORY4="" ;

    private Button tab1_btn ,tab2_btn ,tab3_btn, tab4_btn;
    String val [] = {"KEY_INDEX","PARENT_KEYINDEX", "BODY", "TITLE", "SELF_ID", "GOOD_EA",
            "COMMENT_EA" ,"DATE", "IMG_1" , "IMG_2", "IMG_3",
            "IMG_4","IMG_5","IMG_6","IMG_7","IMG_8",
            "IMG_9","IMG_10","SELF_ID_KEY_INDEX",
            "CATEGORY_1" ,"CATEGORY_2" ,"CATEGORY_3","CATEGORY_4" , "GOOD_FLAG","COUNT"};
    @Override
    public void onResume() {
        postSelAPI(CATEGORY1,CATEGORY2,CATEGORY3,CATEGORY4);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buysell);

        list_number = (ListView)findViewById(R.id.list_number);
        tab1_btn = (Button)findViewById(R.id.tab1_btn);
        tab2_btn = (Button)findViewById(R.id.tab2_btn);
        tab3_btn = (Button)findViewById(R.id.tab3_btn);
        tab4_btn = (Button)findViewById(R.id.tab4_btn);



        findViewById(R.id.top_right_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab1_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab2_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab3_btn).setOnClickListener(btnListener);
        findViewById(R.id.tab4_btn).setOnClickListener(btnListener);
        tab1_btn.setText("항목선택:"+"모두");
        tab2_btn.setText("보증서:"+"모두");
        tab3_btn.setText("청결상태:"+"모두");
        tab4_btn.setText("비용선택:"+"모두");

    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tab1_btn:
                    Log.e("SKY"  , "--tab1_btn--");
                    final CharSequence[] items = {"주방가전", "주방용품", "거실가구", "가습기/선풍기", "수납함", "세탁관련용품", "의류/가방", "화장품", "기타"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(BuySellActivity.this);
                    builder.setTitle("선택하세요")
                            .setItems(items, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    CATEGORY1 = (String) items[index];
                                    tab1_btn.setText("항목선택:"+CATEGORY1);
                                    postSelAPI(CATEGORY1,CATEGORY2,CATEGORY3,CATEGORY4);
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기
                    break;
                case R.id.tab2_btn:
                    Log.e("SKY"  , "--tab2_btn--");
                    final CharSequence[] items2 = {"보증서/유", "보증서/무"};
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(BuySellActivity.this);
                    builder2.setTitle("선택하세요")
                            .setItems(items2, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    CATEGORY2 = (String) items2[index];
                                    tab2_btn.setText("보증서:"+CATEGORY2);
                                    postSelAPI(CATEGORY1,CATEGORY2,CATEGORY3,CATEGORY4);
                                }
                            });

                    AlertDialog dialog2 = builder2.create();    // 알림창 객체 생성
                    dialog2.show();    // 알림창 띄우기
                    break;
                case R.id.tab3_btn:
                    Log.e("SKY"  , "--tab3_btn--");
                    final CharSequence[] items3 = {"A", "B", "C", "D", "E"};
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(BuySellActivity.this);
                    builder3.setTitle("선택하세요")
                            .setItems(items3, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    CATEGORY3 = (String) items3[index];
                                    tab3_btn.setText("청결상태:"+CATEGORY3);
                                    postSelAPI(CATEGORY1,CATEGORY2,CATEGORY3,CATEGORY4);
                                }
                            });

                    AlertDialog dialog3 = builder3.create();    // 알림창 객체 생성
                    dialog3.show();    // 알림창 띄우기
                    break;
                case R.id.tab4_btn:
                    Log.e("SKY"  , "--tab4_btn--");
                    final CharSequence[] items4 = {"50元", "60元", "70元", "80元", "90元", "100元"};
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(BuySellActivity.this);
                    builder4.setTitle("선택하세요")
                            .setItems(items4, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    CATEGORY4 = (String) items4[index];
                                    tab4_btn.setText("비용선택:"+CATEGORY4);
                                    postSelAPI(CATEGORY1,CATEGORY2,CATEGORY3,CATEGORY4);
                                }
                            });

                    AlertDialog dialog4 = builder4.create();    // 알림창 객체 생성
                    dialog4.show();    // 알림창 띄우기
                    break;
                case R.id.top_left_btn:
                    Log.e("SKY"  , "--top_left_btn--");
                    finish();
                    break;
                case R.id.top_right_btn:
                    Log.e("SKY"  , "--top_right_btn--");
                    Intent intent = new Intent(BuySellActivity.this, BuySellWriteActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    private void postSelAPI(String category_1 , String category_2 , String category_3, String category_4){
        customProgressPop();
        arr = new ArrayList<PersonStudyObj>();
        map.clear();
        map.put("url", DEFINE.SERVER_URL + "BUYSELL_SELECT.php");
        map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
        map.put("PARENTS_FLAG", "3");

        map.put("CATEGORY_1", category_1);
        map.put("CATEGORY_2", category_2);
        map.put("CATEGORY_3", category_3);
        map.put("CATEGORY_4", category_4);

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
                        if (Object_Array[17][i].length() != 0)
                            count++;

                        arr.add(new PersonStudyObj(Object_Array[0][i],Object_Array[1][i], Object_Array[2][i], Object_Array[3][i],Object_Array[4][i],
                                Object_Array[5][i], Object_Array[6][i], Object_Array[7][i],Object_Array[8][i],Object_Array[9][i],
                                Object_Array[10][i], Object_Array[11][i],Object_Array[12][i], Object_Array[13][i], Object_Array[14][i],
                                Object_Array[15][i],Object_Array[16][i], Object_Array[17][i] , Object_Array[18][i], Object_Array[19][i]
                                , Object_Array[20][i], Object_Array[21][i], Object_Array[22][i], Object_Array[23][i],count));
                        Log.e("SKY" , "GOOD_FLAG :: "  + Object_Array[19][i]);

                    }
                }

                m_Adapter = new PersonStudy_Adapter(BuySellActivity.this ,arr, mAfterAccum);
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
            Intent board = new Intent(getApplicationContext(), BuySellDetailActivity.class);
            board.putExtra("Object", arr.get(position));
            startActivity(board);
        }
    };
}
