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
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends  Activity { 
	

	
	private RelativeLayout relative;
	private LinearLayout bg_layout;
	private LinearLayout front;
	private ImageView logo;
	private ImageView bg;
	
	private TimerTask time;
	private final Timer _timer = new Timer();
	private final Intent in = new Intent();


	private SharedPreferences sh;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
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
		
		relative = findViewById(R.id.relative);
		bg_layout = findViewById(R.id.bg_layout);
		front = findViewById(R.id.front);
		logo = findViewById(R.id.logo);
		bg = findViewById(R.id.bg);

		sh = getSharedPreferences("sh", Activity.MODE_PRIVATE);


	}

	@Override
	protected void onStart() {
		super.onStart();
		getWindow().setStatusBarColor(0xFFFFFFFF);
	}

	private void initializeLogic() {


		_transparent_satus();

		if (sh.getString("cart_data", "").equals("")) {
			sh.edit().putString("cart_data", "[]").apply();
		}


		/*
bg.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(FileUtil.readFile("ggfd"), 1024, 1024));
Glide.with(getApplicationContext()).load(Uri.parse("tggfxd")).into(bg);
*/
		time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						in.setClass(getApplicationContext(),LayoutActivity.class);

						in.putExtra("cart","");
						// dont remove this
						// this put extra used for launch cart from any activity

						_ActivityTransition(logo, "p", in);
						finish();


						/*SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

						if(sh.getString("user_id", "").equals(""))
						{
							in.setClass(getApplicationContext(),LoginActivity.class);


						} else {
							//startActivity(new Intent(getApplicationContext(),HomeLayout.class));
							in.setClass(getApplicationContext(),LayoutActivity.class);



						}
						_ActivityTransition(logo, "p", in);
						finish();

*/
						time.cancel();
					}
				});
			}
		};
		_timer.schedule(time, 3500);

	/*	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		}*/

	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}


	public void _transparent_satus () {
		Window w = this.getWindow();
		w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { getWindow().setStatusBarColor(Color.TRANSPARENT); }
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
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}


	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
