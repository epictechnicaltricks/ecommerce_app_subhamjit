package kamya.app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tastytoast.TastyToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class ViewProductActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout bg;
	private LinearLayout bottom_layout;
	private ScrollView vscroll1;
	private LinearLayout linear3;
	private RelativeLayout relative;
	private LinearLayout bottom_of_image;
	private LinearLayout image_bottom;
	private LinearLayout image_top;
	private ImageView imageview1;
	private LinearLayout linear8;
	private ImageView back;
	private LinearLayout linear9;
	private ImageView cart;
	private TextView title;
	private TextView category_name;
	private LinearLayout linear11;
	private TextView product_avl;
	private LinearLayout description_layout;
	private LinearLayout linear12;
	private TextView price;
	private TextView delivery_type,delivery_type2;
	private TextView textview9;
	private TextView description;
	private TextView textview10;
	private ScrollView vscroll2;
	private LinearLayout linear13;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private TextView textview2;
	private TextView textview1;

	private RequestNetwork re;
	private RequestNetwork re2;
	private RequestNetwork.RequestListener _re_request_listener;
	private RequestNetwork.RequestListener _re_request_listener22;
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();
	private HashMap<String, Object> api_map = new HashMap<>();
	private HashMap<String, Object> map = new HashMap<>();
	String list="";

	private TextView plus_,minus_,qty_product;
	int qty_count=1;

	String product_id_variable = "";

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_product);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		re = new RequestNetwork(this);

		re2 = new RequestNetwork(this);

		bg = (LinearLayout) findViewById(R.id.bg);
		bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		relative = (RelativeLayout) findViewById(R.id.relative);
		bottom_of_image = (LinearLayout) findViewById(R.id.bottom_of_image);
		image_bottom = (LinearLayout) findViewById(R.id.image_bottom);
		image_top = (LinearLayout) findViewById(R.id.image_top);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		back = (ImageView) findViewById(R.id.back);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		cart = (ImageView) findViewById(R.id.cart);
		title = (TextView) findViewById(R.id.title);
		category_name = (TextView) findViewById(R.id.category_name);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		product_avl = (TextView) findViewById(R.id.product_avl);
		description_layout = (LinearLayout) findViewById(R.id.description_layout);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		price = (TextView) findViewById(R.id.price);

		delivery_type = (TextView) findViewById(R.id.delivery_type);

		delivery_type2 = (TextView) findViewById(R.id.delivery_type2);

		textview9 = (TextView) findViewById(R.id.textview9);
		description = (TextView) findViewById(R.id.description);
		textview10 = (TextView) findViewById(R.id.textview10);
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		/*imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		imageview5 = (ImageView) findViewById(R.id.imageview5);*/
		textview2 = (TextView) findViewById(R.id.textview2);
		textview1 = (TextView) findViewById(R.id.textview1);

		qty_product = findViewById(R.id.qty_product);
		plus_ = findViewById(R.id.plus_);
		minus_ = findViewById(R.id.minus_);

		minus_.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(qty_count>1){

					qty_product.setText(String.valueOf(--qty_count));
					long price_total= (long)(Double.parseDouble(getIntent().getStringExtra("price").replaceAll("₹","")) * qty_count);
					price.setText("₹"+price_total);
				}
			}
		});

		plus_.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				if(qty_count<99){
					qty_product.setText(String.valueOf(++qty_count));
					long price_total= (long)(Double.parseDouble(getIntent().getStringExtra("price").replaceAll("₹","")) * qty_count);
					price.setText("₹"+price_total);
				}
			}
		});



		_re_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _response = _param2;

				//Toast.makeText(getActivity(), _response, Toast.LENGTH_SHORT).show();
				_show_response_product(_response);
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

				Toast.makeText(ViewProductActivity.this, "No internet !", Toast.LENGTH_SHORT).show();

			}
		};

		_re_request_listener22 = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _response = _param2;

				TastyToast.success(getApplicationContext(),"Added to Cart                                        ", TastyToast. LENGTH_LONG, TastyToast.SHAPE_RECTANGLE,false);
				//Toast.makeText(getActivity(), _response, Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

				Toast.makeText(ViewProductActivity.this, "No internet !", Toast.LENGTH_SHORT).show();

			}
		};




	}
	
	private void initializeLogic() {

		description.setText("Loading..");

          product_id_variable = getIntent().getStringExtra("product_id");

		_api_request_product(product_id_variable);




		_rippleRoundStroke(plus_,"#00000000","#E8EAF6",50,3,"#E8EAF6");
		_rippleRoundStroke(minus_,"#00000000","#E8EAF6",50,3,"#E8EAF6");



		delivery_type.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFF1F4FF));

		delivery_type2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFFFF7EE));


		back.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFFFFFFF));
		cart.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFFFFFFF));
		_transparent_satus();
		Glide.with(getApplicationContext()).load(Uri.parse("https://lukeroberts.tv/blog/wp-content/uploads/2019/05/gif1.gif")).into(imageview1);
		bottom_layout.setElevation((float)20);
		bottom_of_image.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		category_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		delivery_type.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

		delivery_type2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

		plus_.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		minus_.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		qty_product.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);



		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		product_avl.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);

		/*Intent in = new Intent();
		in.setClass(getContext(),ViewProductActivity.class);
		in.putExtra("imageURL", Objects.requireNonNull(results.get((int) _position).get("product_image")).toString());
		in.putExtra("desc","");
		in.putExtra("price",price.getText());
		in.putExtra("cat_name", Objects.requireNonNull(results.get((int) _position).get("category_name")).toString());
		in.putExtra("name",product_name.getText());
		startActivity(in);
*/
		category_name.setText("Category > "+ getIntent().getStringExtra("cat_name"));
		title.setText(getIntent().getStringExtra("name"));
		price.setText(getIntent().getStringExtra("price"));
		//description.setText(getIntent().getStringExtra("desc"));

		Glide.with(getApplicationContext())
				.load(Uri.parse(getIntent().getStringExtra("imageURL")))
				.error(R.drawable.pyramids)
				.placeholder(R.drawable.pyramids)
				.thumbnail(0.01f)
				.into(imageview1);

		_setTransitionName(vscroll1, "p");

		textview1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(ViewProductActivity.this, "Buy now clicked", Toast.LENGTH_SHORT).show();
			}
		});


		textview2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				_api_request_add_to_cart(product_id_variable,qty_product.getText().toString(),"6");

							}
		});

		/*
description_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)3, 0xFF5C6BC0, 0xFFE8EAF6));

*/
	}

	public void _setTransitionName (final View _view, final String _transitionName) {
		_view.setTransitionName(_transitionName);
	}


	public void _api_request_product (String _product_id) {
		map.clear();
		results.clear();
		map.put("method", "product_details");
		map.put("product_id", _product_id);

		re.setParams(map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _re_request_listener);
	}


	public void _api_request_add_to_cart (String _product_id, String _qty, String _attrVals) {


		//Toast.makeText(this, _qty+"\n"+_attrVals+"\n"+_product_id, Toast.LENGTH_SHORT).show();
		HashMap<String,Object> map2 = new HashMap<>();
		map2.put("method", "addtocart");
		map2.put("product_id", _product_id);
		map2.put("qty", _qty);
		map2.put("attrVals", _attrVals);

		re2.setParams(map2, RequestNetworkController.REQUEST_PARAM);
		re2.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _re_request_listener22);
	}

	public void _show_response_product (final String _response) {
		try {

			//Toast.makeText(this, _response, Toast.LENGTH_SHORT).show();
			api_map.clear();
			results.clear();
			if (_response.contains("200")) {
				api_map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				// must add resultSet
				//" list " is a String datatype
				list = (new Gson()).toJson(api_map.get("0"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				results = new Gson().fromJson(list, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				String desc = Objects.requireNonNull(results.get(0).get("product_desc")).toString();
				if(desc.equals(""))
				{
					description.setText("Sorry no description provided by the shop..");
				}else {

					description.setText(desc);
				}

				// refresh the list or recycle or grid

			}
		} catch(Exception e) {
			Util.showMessage(getApplicationContext(), "Error on parameter \n\n"+_response);
		}
	}


	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	public void _transparent_satus () {
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { getWindow().setStatusBarColor(Color.TRANSPARENT); }
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		GradientDrawable GG = new GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		RippleDrawable RE = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
