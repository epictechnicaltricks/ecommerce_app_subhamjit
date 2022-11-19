package kamya.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class SeeMoreActivity extends  AppCompatActivity  {


	private HashMap<String, Object> api_map = new HashMap<>();
	private String list = "";

	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();

	private LinearLayout bg;
	private LinearLayout title_bg;
	private GridView gridview1;
	private LinearLayout linear2;
	private TextView textview1;
	private ProgressBar progressbar1;

	private RequestNetwork re;
	private RequestNetwork.RequestListener _re_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.see_more);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		bg = (LinearLayout) findViewById(R.id.bg);
		title_bg = (LinearLayout) findViewById(R.id.title_bg);
		gridview1 = (GridView) findViewById(R.id.gridview1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview1 = (TextView) findViewById(R.id.textview1);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		re = new RequestNetwork(this);

		_re_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				_show_response(_response);
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

			}
		};
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);

		//_transparentStatusAndNavigation();

		gridview1.setVerticalSpacing((int)0);
		gridview1.setHorizontalSpacing((int)0);

		title_bg.setElevation((float)10);
		textview1.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);

switch (getIntent().getStringExtra("type"))
{
	case "fresh_newerer" : _api_request("dasd");break;
	case "fresh_newerer32" : _api_request("dasder");break;
	case "fresh_newerer34" : _api_request("dasdre");break;
	case "fresh_newerer544" : _api_request("daserd");break;

	default: 	_api_request("allproducts");
}


	}


	public void _api_request (String _type) {
		api_map.clear();
		results.clear();
		api_map.put("method", _type );
		re.setParams(api_map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _re_request_listener);
	}


	public void _show_response (final String _response) {
		try {
			api_map.clear();
			results.clear();
			if (_response.contains("200")) {
				api_map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				// must add resultSet
				//" list " is a String datatype
				list = (new Gson()).toJson(api_map.get("0"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				results = new Gson().fromJson(list, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				// refresh the list or recycle or grid
				gridview1.setAdapter(new Gridview1Adapter(results));
			}
		} catch(Exception e) {
			Util.showMessage(getApplicationContext(), "Error ");
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

	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}



	public class Gridview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.product_cus, null);
			}

			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final RelativeLayout relative = (RelativeLayout) _view.findViewById(R.id.relative);
			final LinearLayout bottom = (LinearLayout) _view.findViewById(R.id.bottom);
			final LinearLayout top = (LinearLayout) _view.findViewById(R.id.top);
			final ImageView product_image = (ImageView) _view.findViewById(R.id.product_image);
			final TextView product_name = (TextView) _view.findViewById(R.id.product_name);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final TextView qty = (TextView) _view.findViewById(R.id.qty);
			final TextView price = (TextView) _view.findViewById(R.id.price);
			final ImageView wish_btn = (ImageView) _view.findViewById(R.id.wish_btn);

			try {
				product_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				qty.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				price.setText("₹"+ Objects.requireNonNull(results.get((int) _position).get("product_price")).toString().replaceAll("[.]00",""));
				product_name.setText(Objects.requireNonNull(results.get((int) _position).get("product_name")).toString());




				Glide.with(getApplicationContext())
						.load(Uri.parse(Objects.requireNonNull(results.get((int) _position).get("product_image")).toString()))
						.error(R.drawable.pyramids)
						.placeholder(R.drawable.pyramids)
						.thumbnail(0.01f)
						.into(product_image);


				wish_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));

				wish_btn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {

						wish_btn.setImageResource(R.drawable.red_heart);
						Util.showMessage(getApplicationContext(), "Wishlist Added");
					}
				});


				cardview1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						Intent in = new Intent();
						in.setClass(SeeMoreActivity.this,ViewProductActivity.class);
						in.putExtra("imageURL", Objects.requireNonNull(results.get((int) _position).get("product_image")).toString());

						in.putExtra("product_id", Objects.requireNonNull(results.get((int) _position).get("product_id")).toString());

						//in.putExtra("desc",Objects.requireNonNull(results.get((int) _position).get("product_desc")).toString());
						in.putExtra("price",price.getText());
						in.putExtra("cat_name", Objects.requireNonNull(results.get((int) _position).get("category_name")).toString());
						in.putExtra("name",product_name.getText());

						_ActivityTransition(product_name, "p", in);


						//startActivity(in);

					}
				});
			} catch (Exception e)
			{
				showMessage(e.toString());
			}




			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}


	public void _ActivityTransition (final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(SeeMoreActivity.this, _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}


}
