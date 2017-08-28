package sky.kr.co.hichina;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class IntroActivity extends FragmentActivity {
	private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,


    };
	private static final int DELAY_TIME = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

//		int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
//		if(permissionCheck== PackageManager.PERMISSION_DENIED){
//			// 권한 없음
//			Log.e("SKY", "권한 없음");
//			ActivityCompat.requestPermissions(this,
//					PERMISSIONS_STORAGE,
//					1);
//		}else{
//			// 권한 있음
//			Log.e("SKY", "권한 있음");
//			ActivityCompat.requestPermissions(this,
//					PERMISSIONS_STORAGE,
//					1);
//		}
		MainMove();
	}
	private void MainMove(){
		Handler handler = new Handler(Looper.getMainLooper());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				//Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
				Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, DELAY_TIME);
	}
	public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
		switch (requestCode) {
			case 1: {
				Log.e("SKY" , "성공 : " + grantResults.length);

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("SKY" , "성공");
                    Log.e("SKY" , "permissions SIZE :: " + permissions.length);
                    if (permissions.length == 3) {
                        MainMove();
                    }else{
                        AlertDialog.Builder alert = new AlertDialog.Builder(IntroActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                        alert.setTitle("알림");
                        alert.setMessage("모두 허용하지 않을경우에는 정상적인 서비스를 받을수 없습니다.");
                        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainMove();
                            }
                        });
                        alert.show();
                    }

                } else {
                    Log.e("SKY" , "실패");
					AlertDialog.Builder alert = new AlertDialog.Builder(IntroActivity.this, AlertDialog.THEME_HOLO_LIGHT);
					alert.setTitle("알림");
					alert.setMessage("모두 허용하지 않을경우에는 정상적인 서비스를 받을수 없습니다.");
					alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
                            MainMove();
						}
					});
					alert.show();
                }
				return;
			}
		}
	}
}
