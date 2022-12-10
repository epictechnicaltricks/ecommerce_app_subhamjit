package kamya.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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


public class SearchActivity extends  AppCompatActivity  {
	
	
	private TextView textview1,text9;
	String fontName="";

	EditText search_edittext;

	LinearLayout search;

	private HashMap<String, Object> api_map = new HashMap<>();
	private String list = "";
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();

	private RequestNetwork search_api;
	private RequestNetwork.RequestListener _search_api_listener;

	private GridView gridview1;

	ProgressBar prog1;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.search_layout);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		search_api = new RequestNetwork(this);

		textview1 = (TextView) findViewById(R.id.textview1);

		search_edittext = findViewById(R.id.search_textview);
		search = findViewById(R.id.search);

		gridview1 = findViewById(R.id.gridview1);

		prog1 = findViewById(R.id.progressbar1);

		search_edittext.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				if(charSequence.length()>2){

					_api_request("allproducts");
				}

			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});


		_search_api_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

				_show_response(response);
			}

			@Override
			public void onErrorResponse(String tag, String message) {

				Toast.makeText(SearchActivity.this, "No internet!", Toast.LENGTH_SHORT).show();

			}
		};


	}


	public void close(View view)
	{
		finish();
	}



	public void go_check(View view) {
		startActivity(new Intent(getApplicationContext(),CheckoutActivity.class));
	}
	
	private void initializeLogic() {





		prog1.setVisibility(View.GONE);
		search.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)4, 0xFFBDBDBD, Color.TRANSPARENT));

		search_edittext.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)0, 0xFFFFFFFF));


		_changeActivityFont("google_sans_medium");
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);

	}


	public void _api_request (String _type) {

		prog1.setVisibility(View.VISIBLE);
		api_map.clear();
		results.clear();
		api_map.put("method", _type );
		search_api.setParams(api_map, RequestNetworkController.REQUEST_PARAM);
		search_api.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _search_api_listener);
	}


	public void _show_response (final String _response) {
		try {

			prog1.setVisibility(View.GONE);
			api_map.clear();
			results.clear();
			if (_response.contains("200")) {
				api_map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				// must add resultSet
				//" list " is a String datatype
				list = (new Gson()).toJson(api_map.get("0"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				results = new Gson().fromJson(list, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				// refresh the list or recycle or grid

				if(results.size()>0){

				}else {


				}

				gridview1.setAdapter(new Gridview1Adapter(results));



			}
		} catch(Exception e) {
			Util.showMessage(getApplicationContext(), "Error ");
		}
	}




	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
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

			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final RelativeLayout relative = _view.findViewById(R.id.relative);
			final LinearLayout bottom = _view.findViewById(R.id.bottom);
			final LinearLayout top = _view.findViewById(R.id.top);
			final ImageView product_image = _view.findViewById(R.id.product_image);
			final TextView product_name = _view.findViewById(R.id.product_name);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView qty = _view.findViewById(R.id.qty);
			final TextView price = _view.findViewById(R.id.price);
			final ImageView wish_btn = _view.findViewById(R.id.wish_btn);

			try {
				product_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				qty.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				price.setText("₹"+ Objects.requireNonNull(results.get(_position).get("product_price")).toString().replaceAll("[.]00",""));
				product_name.setText(Objects.requireNonNull(results.get(_position).get("product_name")).toString());




				Glide.with(getApplicationContext())
						.load(Uri.parse(Objects.requireNonNull(results.get(_position).get("product_image")).toString()))
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
						in.setClass(SearchActivity.this,ViewProductActivity.class);
						in.putExtra("imageURL", Objects.requireNonNull(results.get(_position).get("product_image")).toString());

						in.putExtra("product_id", Objects.requireNonNull(results.get(_position).get("product_id")).toString());

						//in.putExtra("desc",Objects.requireNonNull(results.get((int) _position).get("product_desc")).toString());
						in.putExtra("price",price.getText());
						in.putExtra("cat_name", Objects.requireNonNull(results.get(_position).get("category_name")).toString());
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



	public void _ActivityTransition (final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(this, _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}



	public void _changeActivityFont (final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView());
	}
	private void overrideFonts(final Context context, final View v) {

		try {
			Typeface
					typeace = Typeface.createFromAsset(getAssets(), fontName);
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
		}
	}


}
