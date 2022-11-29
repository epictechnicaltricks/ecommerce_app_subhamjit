package kamya.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout toolbar;
	private LinearLayout linear2;
	private ImageView back;
	private TextView title;
	private ImageView imageview4;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private Button materialbutton1;
	private ImageView imageview1;
	private EditText edittext3;
	private ImageView imageview2;
	private EditText edittext1;
	private ImageView imageview3;
	private EditText edittext2;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		toolbar = findViewById(R.id.toolbar);
		linear2 = findViewById(R.id.linear2);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		imageview4 = findViewById(R.id.imageview4);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		materialbutton1 = findViewById(R.id.materialbutton1);
		imageview1 = findViewById(R.id.imageview1);
		edittext3 = findViewById(R.id.edittext3);
		imageview2 = findViewById(R.id.imageview2);
		edittext1 = findViewById(R.id.edittext1);
		imageview3 = findViewById(R.id.imageview3);
		edittext2 = findViewById(R.id.edittext2);
		
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		materialbutton1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {

				Util.showMessage(getApplicationContext(), "Updated ");
			}
		});
	}
	
	private void initializeLogic() {
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		toolbar.setElevation((float)15);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
