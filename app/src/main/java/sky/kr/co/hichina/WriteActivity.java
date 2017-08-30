package sky.kr.co.hichina;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.obj.ThumbImageInfo;

public class WriteActivity extends FragmentActivity {
    public static Boolean pic_flag = false;
    public static ArrayList<ThumbImageInfo> mThumbImageInfoList_copy = new ArrayList<ThumbImageInfo>();;

    private String CATEGORY = "";
    private Button category_btn;
    private EditText title_edit , body_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();

    ImageButton imgB[] ;
    FrameLayout fram[];
    Boolean img_val[] = {false , false , false , false , false , false , false , false , false , false};
    Boolean img_ch_val[] = {false , false , false , false , false , false , false , false , false , false};
    ArrayList<String> filename = new ArrayList<String>();

    @Override
    public void onResume() {
        if (pic_flag) {
            //멀티 사진 가져오기
            pic_flag = false;
            for (int j = 0; j < mThumbImageInfoList_copy.size(); j++) {
                Log.e("SKY" , "mThumbImageInfoList_copy1 :: " + mThumbImageInfoList_copy.get(j).getIndex());
                Log.e("SKY" , "mThumbImageInfoList_copy2 :: " + mThumbImageInfoList_copy.get(j).getData());

                fram[j].setVisibility(View.VISIBLE);
                img_ch_val[j] = true;
                img_val[j] = true;
                imgB[j].setVisibility(View.VISIBLE);
                String filePath = mThumbImageInfoList_copy.get(j).getData();
                System.out.println("path::" + filePath); // logCat으로 경로확인.

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap selectedImage = BitmapFactory.decodeFile(filePath, options);


                imgB[j].setImageBitmap(selectedImage);
                filename.set(j, filePath);
            }
        }
        super.onResume();
    }
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write);

        title_edit = (EditText)findViewById(R.id.title_edit);
        body_edit = (EditText)findViewById(R.id.body_edit);
        category_btn = (Button)findViewById(R.id.category_btn);

        title_edit.setText("title 111");
        body_edit.setText("title 111222");

        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);
        findViewById(R.id.category_btn).setOnClickListener(btnListener);
        findViewById(R.id.potho_btn).setOnClickListener(btnListener);
        findViewById(R.id.send_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);
        findViewById(R.id.top_left_btn).setOnClickListener(btnListener);


        imgB = new ImageButton[10];
        fram = new FrameLayout[10];

        fram[0] = (FrameLayout)findViewById(R.id.fram1);
        fram[1] = (FrameLayout)findViewById(R.id.fram2);
        fram[2] = (FrameLayout)findViewById(R.id.fram3);
        fram[3] = (FrameLayout)findViewById(R.id.fram4);
        fram[4] = (FrameLayout)findViewById(R.id.fram5);
        fram[5] = (FrameLayout)findViewById(R.id.fram6);
        fram[6] = (FrameLayout)findViewById(R.id.fram7);
        fram[7] = (FrameLayout)findViewById(R.id.fram8);
        fram[8] = (FrameLayout)findViewById(R.id.fram9);
        fram[9] = (FrameLayout)findViewById(R.id.fram10);


        imgB[0] = (ImageButton)findViewById(R.id.btn_img1_img1);
        imgB[1] = (ImageButton)findViewById(R.id.btn_img1_img2);
        imgB[2] = (ImageButton)findViewById(R.id.btn_img1_img3);
        imgB[3] = (ImageButton)findViewById(R.id.btn_img1_img4);
        imgB[4] = (ImageButton)findViewById(R.id.btn_img1_img5);
        imgB[5] = (ImageButton)findViewById(R.id.btn_img1_img6);
        imgB[6] = (ImageButton)findViewById(R.id.btn_img1_img7);
        imgB[7] = (ImageButton)findViewById(R.id.btn_img1_img8);
        imgB[8] = (ImageButton)findViewById(R.id.btn_img1_img9);
        imgB[9] = (ImageButton)findViewById(R.id.btn_img1_img10);

        for (int i = 0; i < 10; i++) {
            filename.add("");
        }
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.top_left_btn:
                    Log.e("SKY"  , "--top_left_btn--");
                    finish();
                    break;
                case R.id.category_btn:
                    Log.e("SKY"  , "--category_btn--");
                    final CharSequence[] items = {"학생(어학연수)", "학생(본과생)", "학생(석사/박사)" , "학부모" , "직장인" , "사업자" , "공무원"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(WriteActivity.this);
                    builder.setTitle("집업을 선택하세요")
                            .setItems(items, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                                public void onClick(DialogInterface dialog, int index){
                                    CATEGORY = (String) items[index];
                                    category_btn.setText(""+CATEGORY);
                                }
                            });

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기
                    break;
                case R.id.send_btn:
                    Log.e("SKY"  , "--send_btn--");
                    break;
                case R.id.potho_btn:
                    Log.e("SKY"  , "--potho_btn--");
                    Intent intent = new Intent(WriteActivity.this, GalleryActivity.class);
                    startActivity(intent);
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
                String res = (String)msg.obj;
                Log.e("CHECK" , "RESULT  -> " + res);


            }
        }
    };
    @Override
    protected void onDestroy(){
        for (int i = 0; i < imgB.length; i++) {
            Drawable d = imgB[i].getDrawable();
            if(d instanceof BitmapDrawable){
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }
        super.onDestroy();
    }
}
