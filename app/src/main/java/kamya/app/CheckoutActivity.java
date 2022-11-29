package kamya.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class CheckoutActivity extends  AppCompatActivity  {

	int qty_count=1;
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private final String typeace = "";
	
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();

	private ArrayList<HashMap<String, Object>> results2 = new ArrayList<>();


	private final HashMap<String, Object> map = new HashMap<>();

	private final HashMap<String, Object> map2 = new HashMap<>();

	private HashMap<String, Object> api_map = new HashMap<>();

	private HashMap<String, Object> api_map2 = new HashMap<>();

	String list="";

	boolean pin=false;

	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private TextView textview4;
	private TextView textview2;

	private ScrollView vscroll2;
	private LinearLayout inside_scroll;
	private LinearLayout linear6;
	private RecyclerView recyclerview1;
	private LinearLayout price_details;
	private TextView textview13;
	private LinearLayout linear7;
	private TextView textview7;
	private TextView textview8;
	private TextView textview6;
	private TextView textview9;
	private TextView textview11;
	private LinearLayout linear10;
	private LinearLayout linear8;
	private LinearLayout linear11;
	private TextView textview14;
	private TextView textview18;
	private TextView textview12;
	private TextView textview17;
	private TextView textview15;
	private TextView textview19;

	private RequestNetwork req_add_to_cart;
	private RequestNetwork.RequestListener _req_add_to_cart_listener;

	private RequestNetwork req_delete_cart;
	private RequestNetwork.RequestListener _req_delete_cart_listener;

	String user_id;


	private RequestNetwork re;
	private RequestNetwork.RequestListener _re_request_listener;

	private LottieAnimationView loading;

	Button payment;

	Button verify_pin;

	private RequestNetwork pin_code_api;
	private RequestNetwork.RequestListener _pin_code_request_listener;

	EditText pin_code_;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.checkout);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		loading = findViewById(R.id.lottie_loading);

		re = new RequestNetwork(this);
		req_add_to_cart = new RequestNetwork(this);
		req_delete_cart = new RequestNetwork(this);
		pin_code_api = new RequestNetwork(this);


		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);

		pin_code_ = findViewById(R.id.pin_code);

		verify_pin = findViewById(R.id.verify_pin_);

		payment = findViewById(R.id.payment);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		textview4 = findViewById(R.id.textview4);
		textview2 = findViewById(R.id.textview2);

		vscroll2 = findViewById(R.id.vscroll2);
		inside_scroll = findViewById(R.id.inside_scroll);
		linear6 = findViewById(R.id.linear6);
		recyclerview1 = findViewById(R.id.recyclerview1);
		price_details = findViewById(R.id.price_details);
		textview13 = findViewById(R.id.textview13);
		linear7 = findViewById(R.id.linear7);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		textview6 = findViewById(R.id.textview6);
		textview9 = findViewById(R.id.textview9);
		textview11 = findViewById(R.id.textview11);
		linear10 = findViewById(R.id.linear10);
		linear8 = findViewById(R.id.linear8);
		linear11 = findViewById(R.id.linear11);
		textview14 = findViewById(R.id.textview14);
		textview18 = findViewById(R.id.textview18);
		textview12 = findViewById(R.id.textview12);
		textview17 = findViewById(R.id.textview17);
		textview15 = findViewById(R.id.textview15);
		textview19 = findViewById(R.id.textview19);
		
		textview9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				Util.showMessage(getApplicationContext(), "");
			}
		});



		payment.setOnClickListener(view -> {

			if(pin){

				Intent intent = new Intent();
				intent.setClass(CheckoutActivity.this,Payment.class);
				startActivity(intent);
			}else {

				showMessage("Please check pin code first");
			}

		});



		_re_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				loading.setVisibility(View.GONE);
				//Toast.makeText(CheckoutActivity.this, "add to cart \n"+_response, Toast.LENGTH_SHORT).show();
				_show_response(_response);
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

				Toast.makeText(getApplicationContext(), "No Internet !", Toast.LENGTH_SHORT).show();
			}
		};

		_req_add_to_cart_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _response = _param2;

				_api_request(user_id);
				// THIS REQUEST FOR UPDATE CART VALUE
				// WHEN USER CLICK ON PLUS OR MINUS BUTTON ON PRODUCT
				// PROJECT BY SHUBHAMJIT DT 20 NOV 2022 6.37PM
				//Toast.makeText(getActivity(), _response, Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _message = _param2;

				Toast.makeText(getApplicationContext(), "Cart not updated, No Internet !\n\n"+_message, Toast.LENGTH_LONG).show();

			}
		};

		_req_delete_cart_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _response = _param2;

				Toast.makeText(getApplicationContext(), "Removed Successfully", Toast.LENGTH_SHORT).show();
				_api_request(user_id);
				// THIS REQUEST FOR UPDATE CART VALUE

			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _message = _param2;

				Toast.makeText(getApplicationContext(), "Cart not updated, No Internet !\n\n"+_message, Toast.LENGTH_LONG).show();

			}
		};




		verify_pin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				if(pin_code_.length()==6){

					request_pin_code(pin_code_.getText().toString());

				}else {

					payment.setAlpha(.5f);
					payment.setEnabled(false);
					showMessage("Invalid pin code");
				}

			}
		});

		_pin_code_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

				if(response.contains("200")){

					pin = true;
					payment.setAlpha(1f);
					payment.setEnabled(true);
					showMessage("Available on this PIN");
					payment.setText("GO TO PAYMENT");

				}else {

					payment.setAlpha(.5f);
					payment.setEnabled(false);
					payment.setText("Delivery not available");
					showMessage("Delivery not available on this PIN code");
				}

			}

			@Override
			public void onErrorResponse(String tag, String message) {

			}
		};


		textview9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				startActivity(new Intent(CheckoutActivity.this, MyAddress_EDIT_Activity.class));

			}
		});

	}

	private void request_pin_code(String _pin){

		    //api_map2.clear();
			//results2.clear();
			/*api_map2.put("method", "location");
			api_map2.put("pincode", _pin);*/
			//pin_code_api.setParams(api_map2, RequestNetworkController.REQUEST_PARAM);
			pin_code_api.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?method=location&pincode="+_pin, "", _pin_code_request_listener);


	}

	public void _transparent_satus () {
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
	}




	/** Called when the user touches the button */
	public void close(View view)
	{
		finish();
	}

	private void initializeLogic() {

		payment.setEnabled(true);
		pin = false;
		payment.setAlpha(.5f);
		//payment.setEnabled(false);
		_changeActivityFont("google_sans_medium");
		//_transparent_satus();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);

		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);

		SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
		user_id = sh.getString("user_id", "");
		_api_request(user_id);



		recyclerview1.stopScroll();





	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	public void _hight_of_scroll_in_listview (final View _view, final double _hight) {
		_view.getLayoutParams().height=(int)(_hight);
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

	public void _api_request (String user_id) {

		map.clear();
		results.clear();
		map.put("method", "cart_itemlist");
		map.put("user_id", user_id);
		re.setParams(map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET,
				"https://kkkamya.in/index.php/Api_request/api_list?",
				"", _re_request_listener);

	}

	public void request_update_cart(String _product_id, String _qty, String _attrVals) {


		//Toast.makeText(this, _qty+"\n"+_attrVals+"\n"+_product_id, Toast.LENGTH_SHORT).show();




		HashMap<String, Object> map3 = new HashMap<>();
		map3.put("method", "addtocart");
		map3.put("product_id", _product_id);
		map3.put("qty", _qty);
		if(!_attrVals.equals("")) {
			map3.put("attrVals", _attrVals);
		}

		req_add_to_cart.setParams(map3, RequestNetworkController.REQUEST_PARAM);
		req_add_to_cart.startRequestNetwork(
				RequestNetworkController.GET,
				"https://kkkamya.in/index.php/Api_request/api_list?"
				, ""
				, _req_add_to_cart_listener);
	}


	public void _api_request_delete_cart_item (String _cart_id) {
		map2.clear();
		results.clear();
		map2.put("method", "deletecartitem");
		map2.put("cart_id", _cart_id );
		re.setParams(map2, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET,
				"https://kkkamya.in/index.php/Api_request/api_list?",
				"", _req_delete_cart_listener);
	}



	public void _show_response (final String _response) {
		try {
			api_map.clear();
			results.clear();

			loading.setVisibility(View.GONE);

			if (_response.contains("200")) {
				api_map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				// must add resultSet
				//" list " is a String datatype
				list = (new Gson()).toJson(api_map.get("res"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

				results = new Gson().fromJson(list, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

				// refresh the list or recycle or grid

			/*	if(results.size()==0){
					cart_count.setVisibility(View.GONE);
				}else{
					if(results.size()==1){

						cart_count.setText(results.size()+" item available");

					}else {

						cart_count.setText("Total items: "+ results.size());

					}

				}

				*/



				_reftesh();




			}else {
				Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
			}



		} catch(Exception e) {


			Util.showMessage(getApplicationContext(), "Error on cart \n\n"+_response);
			e.printStackTrace();
		}
	}


	public void _reftesh () {

		_hight_of_scroll_in_listview(recyclerview1, results.size() * Util.getDip(getApplicationContext(), 170));

		recyclerview1.setAdapter(new Recyclerview1Adapter(results));
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
	}


	public class Recyclerview1Adapter extends Adapter<Recyclerview1Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.cart_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}

		@Override
		public void onBindViewHolder(Recyclerview1Adapter.ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;

			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout barline = _view.findViewById(R.id.barline);
			final ImageView product_img = _view.findViewById(R.id.product_img);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView name = _view.findViewById(R.id.name);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final TextView varient = _view.findViewById(R.id.varient);
			final TextView product_attr_val = _view.findViewById(R.id.product_attr_val);
			final ImageView minus = _view.findViewById(R.id.minus);
			final TextView qty = _view.findViewById(R.id.qty);
			final ImageView plus = _view.findViewById(R.id.plus);
			final ImageView delete = _view.findViewById(R.id.delete);
			final TextView pro_price_and_qty = _view.findViewById(R.id.pro_price_and_qty);
			final TextView total_price = _view.findViewById(R.id.total_price);

			try {

				bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFECEFF1));
				name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				pro_price_and_qty.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				product_attr_val.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				total_price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				qty.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				varient.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

				Glide.with(getApplicationContext()).load(Uri.parse("https://kkkamya.in/uploads/product/".concat(results.get(_position).get("product_image").toString()))).into(product_img);

				name.setText(results.get(_position).get("product_name").toString());
				qty.setText(results.get(_position).get("quantity").toString());
				pro_price_and_qty.setText(results.get(_position).get("product_price").toString().concat(" x ".concat(results.get(_position).get("quantity").toString())));
				total_price.setText("₹".concat(results.get(_position).get("total_price").toString()));

				plus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View _view) {
						qty_count = Integer.parseInt(qty.getText().toString());

						if(qty_count<99){
							qty.setText(String.valueOf(++qty_count));

							request_update_cart(results.get(_position).get("product_id").toString(),qty.getText().toString(),"");

						}

					}
				});

				minus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View _view) {
						qty_count = Integer.parseInt(qty.getText().toString());

						if(qty_count>1){

							qty.setText(String.valueOf(--qty_count));
							request_update_cart(Objects.requireNonNull(results.get(_position).get("product_id")).toString(),qty.getText().toString(),"");
							Toast.makeText(getApplicationContext(), qty.getText().toString(), Toast.LENGTH_SHORT).show();

						}

					}
				});


				delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View _view) {

								_api_request_delete_cart_item(Objects.requireNonNull(results.get(_position).get("cart_id")).toString());
					}
				});

				delete.setColorFilter(0xFFD50000);

			} catch(Exception e) {

				Util.showMessage(getApplicationContext(), "Error on parameters \n\n" +e);
			}
		}

		@Override
		public int getItemCount() {
			return _data.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder{
			public ViewHolder(View v){
				super(v);
			}
		}

	}




	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
	}
	

}
