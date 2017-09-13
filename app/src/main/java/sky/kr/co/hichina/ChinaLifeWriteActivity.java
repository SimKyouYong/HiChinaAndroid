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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import co.kr.sky.AccumThread;
import sky.kr.co.hichina.common.ActivityEx;
import sky.kr.co.hichina.common.Check_Preferences;
import sky.kr.co.hichina.common.DEFINE;
import sky.kr.co.hichina.obj.ThumbImageInfo;

public class ChinaLifeWriteActivity extends ActivityEx {
    public static Boolean pic_flag = false;
    public static ArrayList<ThumbImageInfo> mThumbImageInfoList_copy = new ArrayList<ThumbImageInfo>();;
    int serverResponseCode = 0;

    private String CATEGORY = "";
    private Button category_btn;
    private EditText title_edit , body_edit;
    private AccumThread mThread;
    private Map<String, String> map = new HashMap<String, String>();
    private int send_position = 0;

    ImageButton imgB[] ;
    FrameLayout fram[];
    Boolean img_val[] = {false , false , false , false , false , false , false , false , false , false};
    Boolean img_ch_val[] = {false , false , false , false , false , false , false , false , false , false};
    ArrayList<String> filename = new ArrayList<String>();
    private String dTime = "";
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
		setContentView(R.layout.activity_chinalifewrite);


        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
        Date currentTime = new Date ( );
        dTime = formatter.format ( currentTime );
        dTime = dTime.replace(" ", "");
        dTime = dTime.trim();

        title_edit = (EditText)findViewById(R.id.title_edit);
        body_edit = (EditText)findViewById(R.id.body_edit);
        category_btn = (Button)findViewById(R.id.category_btn);

//        title_edit.setText("title 111");
        title_edit.setVisibility(View.GONE);
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

                    final CharSequence[] items = {"생활Tip", "동영상", "이슈" , "교통.물류" , "증권.은행" , "오락/음식" , "부동산","기타"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChinaLifeWriteActivity.this);
                    builder.setTitle("선택하세요")
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
                    if (category_btn.getText().toString().equals("-카테고리 선택-")) {
                        Toast.makeText(getApplicationContext() , "카테고리를 선택해주세요" , Toast.LENGTH_SHORT).show();
                        return;
                    }else if(body_edit.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext() , "내용을 입력해주세요" , Toast.LENGTH_SHORT).show();
                        return;
                    }
                    customProgressPop();
                    newSend();
                    break;
                case R.id.potho_btn:
                    Log.e("SKY"  , "--potho_btn--");
                    Intent intent = new Intent(ChinaLifeWriteActivity.this, GalleryActivity.class);
                    intent.putExtra("tag" , "2");

                    startActivity(intent);
                    break;

            }
        }
    };
    private void newSend(){
        send_position = 0;
        int fullsize = 0;
        for (int i = 0; i < filename.size(); i++) {
            if (!filename.get(i).equals("")) {
                fullsize++;
            }
        }
        Log.e("SKY" , "FF :: " + fullsize);
        if (fullsize == 0) {
            //글만 전송
            //파라미터 전송


            map.put("url", DEFINE.SERVER_URL + "CHINALIFE_WRITE.php");
            //map.put("TITLE",title_edit.getText().toString());
            map.put("BODY",body_edit.getText().toString());
            map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"MEMBER_ID"));
            map.put("DATE", dTime);
            map.put("SELF_ID_KEY_INDEX",Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
            map.put("CATEGORY_1",category_btn.getText().toString());

            for (int i = 0; i < filename.size(); i++) {
                String[] file_name = filename.get(i).split("/");
                map.put("image_name" + (i+1),file_name[file_name.length-1]);		//type 에 따른.. 값으로..edit
            }

            mThread = new AccumThread(ChinaLifeWriteActivity.this , mAfterAccum , map , 0 , 0 , null);
            mThread.start();		//스레드 시작!!
            return;
        }else{
            //사진전송 모두 하고 글 전송
            AccumThread_i mThread = new AccumThread_i(null,0 , true);
            mThread.start();
        }
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

                finish();

            }
        }
    };
    //스레드 구현 부분
    class AccumThread_i extends Thread{
        Handler mAfter;
        int i;
        boolean _a;
        AccumThread_i(Handler after , int i_ , boolean newsend)
        {
            mAfter = after;
            i = i_;
            _a = newsend;
        }
        @Override
        public void run()
        {
            Log.e("SKY" , "send_position  :: " + send_position);

            if (!filename.get(i).equals("")){
                Log.i("SKY", "filename.get" + filename.get(i));
                uploadFile(filename.get(i));
            }

            send_position++;
            if (send_position == 10) {
                if (_a) {

                    Log.e("SKY" , "filename.size() :: " + filename.size());
                    Log.e("SKY" , "i :: " + (i+1));
                    map.put("url", DEFINE.SERVER_URL + "CHINALIFE_WRITE.php");
                    //map.put("TITLE",title_edit.getText().toString());
                    map.put("BODY",body_edit.getText().toString());
                    map.put("SELF_ID", Check_Preferences.getAppPreferences(getApplicationContext() ,"MEMBER_ID"));
                    map.put("DATE", dTime);
                    map.put("SELF_ID_KEY_INDEX",Check_Preferences.getAppPreferences(getApplicationContext() ,"KEY_INDEX"));
                    map.put("CATEGORY_1",category_btn.getText().toString());
                    String MEMBER_ID = Check_Preferences.getAppPreferences(ChinaLifeWriteActivity.this , "MEMBER_ID");

                    for (int i = 0; i < filename.size(); i++) {
                        String[] file_name = filename.get(i).split("/");
                        if (file_name[file_name.length-1].length() > 3){
                            map.put("IMG_" + (i+1),MEMBER_ID + "_"+ dTime +"_"+file_name[file_name.length-1]);		//type 에 따른.. 값으로..edit
                        }
                    }

                    mThread = new AccumThread(ChinaLifeWriteActivity.this , mAfterAccum , map , 0 , 0 , null);
                    mThread.start();		//스레드 시작!!

                }
            }else{
                Log.e("SKY" , "전송--------------");
                AccumThread_i mThread = new AccumThread_i(null , send_position , _a);
                mThread.start();
            }
        }
    }
    private int uploadFile(String sourceFileUri) {
        Log.e("CHECK" , "fileName-->" + sourceFileUri);
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);
        String[] file_name = sourceFileUri.split("/");
        //여기서 파일 이름 변경!하고 값 다시 담기!
        if (!sourceFile.isFile()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Log.i("SKY", "!sourceFile.isFile() ---> 수정일 때");
                }
            });
            return 0;
        }
        else
        {
            Log.i("SKY", "sourceFile.isFile() ---> 수정일 때");
            Log.e("uploadFile", "gogo");
            Log.e("SKY", "file_name[file_name.length] :: " + file_name[file_name.length-1]);
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(DEFINE.SERVER_URL + "POTHO_SEND.php");

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setChunkedStreamingMode(1024);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", sourceFileUri);

                dos = new DataOutputStream(conn.getOutputStream());

                String MEMBER_ID = Check_Preferences.getAppPreferences(ChinaLifeWriteActivity.this , "MEMBER_ID");
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                if (file_name[file_name.length-1].length() > 3){
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";" +
                            "filename=\""+ MEMBER_ID + "_" + dTime+"_"+file_name[file_name.length-1]+ "" + "\"" + lineEnd);
                }else{
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";" +
                            "filename=\""+ file_name[file_name.length-1]+ "" + "\"" + lineEnd);
                }


                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                Log.e("uploadFile", "bufferSize-->" + bufferSize);

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    Log.e("uploadFile", "bufferSize-->" + bufferSize);
                    Log.e("uploadFile", "bytesRead-->" + bytesRead);
                    Log.e("uploadFile", "bytesAvailable-->" + bytesAvailable);


                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Log.e("SKY" , "이미지 전송 성공");
                        }
                    });
                }
                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                    }
                });
                Log.e("SKY", "Exception : "+ e.getMessage());
            }
            return serverResponseCode;

        } // End else block
    }
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
