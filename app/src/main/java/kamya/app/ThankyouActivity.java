package kamya.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;


public class ThankyouActivity extends  AppCompatActivity  { 
	
	private final Timer _timer = new Timer();
	
	private RelativeLayout relative;
	private LinearLayout bottom;
	private LinearLayout top;
	private LottieAnimationView lottie2;
	private LottieAnimationView lottie1;
	private TextView textview2;
	private TextView textview1;
	
	private TimerTask time;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.thankyou);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		relative = findViewById(R.id.relative);
		bottom = findViewById(R.id.bottom);
		top = findViewById(R.id.top);
		lottie2 = findViewById(R.id.lottie2);
		lottie1 = findViewById(R.id.lottie1);
		textview2 = findViewById(R.id.textview2);
		textview1 = findViewById(R.id.textview1);
		
		textview1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(new Intent(getApplicationContext(),MyOrdersActivity.class));
			}
		});
	}
	
	private void initializeLogic() {

		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);


		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						lottie2.setVisibility(View.GONE);
						time.cancel();
					}
				});
			}
		};
		_timer.schedule(time, 5000);





	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	public void _transparent_satus () {
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { getWindow().setStatusBarColor(Color.TRANSPARENT); }
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
