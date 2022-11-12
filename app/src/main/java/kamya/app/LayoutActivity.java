package kamya.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class LayoutActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	
	private RelativeLayout relative;
	private LinearLayout nav_layout;
	private LinearLayout loading;


	private String fontName = "";
	private String typeace = "";

	private LinearLayout linear1;
	private ViewPager viewpager1;
	private BottomNavigationView bottomnavigation1;
	private TextView title;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.layout);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(LayoutActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		relative = (RelativeLayout) findViewById(R.id.relative);
		nav_layout = (LinearLayout) findViewById(R.id.nav_layout);
		loading = (LinearLayout) findViewById(R.id.loading);

		viewpager1 = (ViewPager) findViewById(R.id.viewpager1);
		bottomnavigation1 = (BottomNavigationView) findViewById(R.id.bottomnavigation1);


		viewpager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int _position) {

				bottomnavigation1.getMenu().getItem(_position).setChecked(true);

				/*if (_position == 0) {
					title.setText("Home");
				}
				if (_position == 1) {
					title.setText("Search");
				}
				if (_position == 2) {
					title.setText("Analysis");
				}
				if (_position == 3) {
					title.setText("Profile");
				}
				*/
			}

			@Override
			public void onPageScrollStateChanged(int _scrollState) {

			}
		});

		bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				final int _itemId = item.getItemId();


				viewpager1.setCurrentItem((int)_itemId);
				return true;
			}
		});



		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				_clickAnimation(_fab);
				viewpager1.setCurrentItem(2);
				//_fab.setBackgroundTintList(ColorStateList.valueOf(0xFFE1D353));


			}
		});
	}



	public class MyFragmentAdapter extends FragmentStatePagerAdapter {
		Context context;
		int tabCount;

		public MyFragmentAdapter(Context context, FragmentManager fm, int tabCount) {
			super(fm);
			this.context = context;
			this.tabCount = tabCount;
		}

		@Override
		public int getCount(){
			return tabCount;
		}

		@Override
		public CharSequence getPageTitle(int _position) {

			return null;
		}

		@Override
		public Fragment getItem(int _position22) {
			if (_position22 == 0) {
				return new Category_FragmentActivity();
			}
			if (_position22 == 1) {
				return new Cart_FragmentActivity();
			}
			if (_position22 == 2) {

					return new Home_FragmentActivity();
			}
			if (_position22 == 3) {
				return new Wishlist_FragmentActivity();
			}
			if (_position22 == 4) {
				return new Account_FragmentActivity();
			}
			return null;
		}

	}

	public void _UI () {
		viewpager1.setAdapter(new LayoutActivity.MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 5));
		bottomnavigation1.getMenu().add(0, 0, 0, "Category").setIcon(R.drawable.catego);
		bottomnavigation1.getMenu().add(0, 1, 0, "Cart").setIcon(R.drawable.bags2);
		bottomnavigation1.getMenu().add(0, 2, 0, "").setIcon(R.drawable.trans);
		bottomnavigation1.getMenu().add(0, 3, 0, "Wishlist").setIcon(R.drawable.heart);
		bottomnavigation1.getMenu().add(0, 4, 0, "Account").setIcon(R.drawable.user2);
		//bottomnavigation1.getMenu().add(0, 5, 0, "Account").setIcon(R.drawable.user);

		_fab.performClick();

		_changeActivityFont("google_sans_medium");
		/*getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);*/



	}


	public void _shape (final double _top1, final double _top2, final double _bottom2, final double _bottom1, final String _inside_color, final String _side_color, final double _side_size, final View _view) {
		Double tlr = _top1;
		Double trr = _top2;
		Double blr = _bottom2;
		Double brr = _bottom1;
		Double sw = _side_size;
		android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
		s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		s.setCornerRadii(new float[] {tlr.floatValue(),tlr.floatValue(), trr.floatValue(),trr.floatValue(), blr.floatValue(),blr.floatValue(), brr.floatValue(),brr.floatValue()});

		s.setColor(Color.parseColor(_inside_color));
		s.setStroke(sw.intValue(), Color.parseColor(_side_color));
		_view.setBackground(s);
	}


	private void initializeLogic() {

		_transparent_satus();
		_shape(100, 100, 0, 0, "#ffffff", "#ffffff", 0, bottomnavigation1);
		bottomnavigation1.setElevation((float)15);
        _UI();
		//bottomnavigation1.getBar().setBackgroundColor(0xFF000000);
		//bottomnavigation1.setActiveTabColor("#FFFFFE");
		try{ Objects.requireNonNull(getSupportActionBar()).hide(); }
		catch (Exception e){}



	}
	public void _clickAnimation (final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.8f, 1f, 0.8f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(200);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}

	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}

	public void _transparent_satus () {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);}
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { getWindow().setStatusBarColor(Color.TRANSPARENT); }
	}



	public void _changeActivityFont (final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView());
	}
	private void overrideFonts(final Context context, final View v) {

		try {
			Typeface
					typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
					 i < vg.getChildCount();
					 i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)

		{
			Util.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}

/*	public void _show_dialog_progress (final boolean _visibility) {
		if (_visibility) {
			if (d == null){
				d = new ProgressDialog(MainActivity.this,ProgressDialog.THEME_HOLO_DARK);
				d.setIndeterminate(true);
				 d.setMax(100);
				 d.setTitle("Loading");
				 d.setMessage("Please wait 2 sec...");
				 d.setProgress(100);
				                    d.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				 d.setCancelable(true);
				 d.create();
				 d.show();
				
				
			}
		}
		else {
			if (d != null){
				d.dismiss();
			}
		}
	}
	private ProgressDialog d;
	{
	}
	*/
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
