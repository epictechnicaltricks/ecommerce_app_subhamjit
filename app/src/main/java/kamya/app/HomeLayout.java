package kamya.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class HomeLayout extends  Activity {
	
	private final Timer _timer = new Timer();
	
	private RelativeLayout relative;
	private LinearLayout bg_layout;
	private LinearLayout front;
	private ImageView logo;
	private ImageView bg;

	private TextView name, email, phone, userid,logout;

	private TimerTask time;
	private final Intent in = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home_layout);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
			}
			else {
				initializeLogic();
			}
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		//relative = findViewById(R.id.relative);
		//bg_layout = findViewById(R.id.bg_layout);
		//front = findViewById(R.id.front);
		//logo = findViewById(R.id.logo);
		bg = findViewById(R.id.bg);
		name = findViewById(R.id.name);
		userid = findViewById(R.id.user_id);
		phone = findViewById(R.id.phone_no);
		email = findViewById(R.id.email);
		logout = findViewById(R.id.Logout);
	}

	@Override
	protected void onStart() {
		super.onStart();
		getWindow().setStatusBarColor(0xFFFFFFFF);
	}

	private void initializeLogic() {
		/*
bg.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(FileUtil.readFile("ggfd"), 1024, 1024));
Glide.with(getApplicationContext()).load(Uri.parse("tggfxd")).into(bg);
*/
	/*	time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {


						in.setClass(getApplicationContext(),LoginActivity.class);
						_ActivityTransition(logo, "p", in);
						finish();

						time.cancel();
					}
				});
			}
		};
		_timer.schedule(time, 3500);*/

		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				SharedPreferences sha = HomeLayout.this.getSharedPreferences("MySharedPref",MODE_PRIVATE);
				SharedPreferences.Editor editor = sha.edit();
				editor.clear();
				editor.apply();

				startActivity(new Intent(getApplicationContext(),LoginActivity.class));
				finish();

			}
		});

		SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

		try{

			String _phone=sh.getString("mobile", "");
			String _fullname=sh.getString("fullname", "");
			String _email=sh.getString("email", "");
			String _id=sh.getString("user_id", "");


			phone.setText(_phone);
			name.setText(_fullname);
			email.setText(_email);
			userid.setText(_id);

		}catch(Exception e)
		{
			showMessage(e.toString());
		}





		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		}

	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _transparentStatusAndNavigation () {
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		);
		setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
				| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		getWindow().setNavigationBarColor(Color.TRANSPARENT);
	}
	private void setWindowFlag(final int bits, boolean on) {
		    Window win = getWindow();
		    WindowManager.LayoutParams winParams = win.getAttributes();
		    if (on) {
			        winParams.flags |= bits;
			    } else {
			        winParams.flags &= ~bits;
			    }
		    win.setAttributes(winParams);
	}

	public void _ActivityTransition (final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(HomeLayout.this, _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}


	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
