package kamya.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class Home_FragmentActivity extends  Fragment  {


	private Timer _timer = new Timer();

	private double count = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String rawdata = "";
	private double ki = 0;
	private String di = "";
	private String daydates = "";
	private HashMap<String, Object> converted = new HashMap<>();
	private String finedimage = "";
	private String post = "";
	private String ptitle = "";
	private String userimage = "";
	private String postcat = "";
	private String username = "";
	private String error = "";
	private HashMap<String, Object> api_map = new HashMap<>();
	private String list = "";
	private String product_desc = "";

	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> shut = new ArrayList<>();
	private ArrayList<String> allraw = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> readypost = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();

	private LinearLayout bg;
	private LinearLayout linear2;
	private ScrollView vscroll1;
	private LinearLayout linear4;
	private LinearLayout search;
	private LinearLayout linear5;
	private TextView cart_count;
	private ImageView imageview3;
	private TextView welcome;
	private TextView user_name;
	private ImageView imageview2,menu;
	private TextView search_textview;
	private LinearLayout scroll;
	private ViewPager viewpager1,viewpager2,viewpager3;
	private LinearLayout slider_layout;
	private GridView gridview1;
	private LinearLayout title_layout;
	private HorizontalScrollView hscroll1;
	private TextView title,title2,title3,title4;
	private TextView show_more,show_more2,show_more3,show_more4;
	private LinearLayout linear6;

	private TimerTask srcoll_timer;
	private RequestNetwork re;
	private RequestNetwork.RequestListener _re_request_listener;

	private LinearLayout linear_g2,linear_g22,linear_g222,linear_g2222;
	private ListView listview1,listview2,listview3,listview4;

	String tempJson="{\n" +
			"   \"status\":200,\n" +
			"   \"0\":[\n" +
			"      {\n" +
			"         \"product_id\":\"8\",\n" +
			"         \"category_name\":\"Saree n Saree\",\n" +
			"         \"product_name\":\"Bollywood Saree\",\n" +
			"         \"product_price\":\"1500.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20210822_1629642096.jpg\",\n" +
			"         \"product_desc\":\"\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"9\",\n" +
			"         \"category_name\":\"Women Clothing\",\n" +
			"         \"product_name\":\"Gown\",\n" +
			"         \"product_price\":\"2000.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20210824_1629799434.jpg\",\n" +
			"         \"product_desc\":\"\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"11\",\n" +
			"         \"category_name\":\"Clothing & Apparel\",\n" +
			"         \"product_name\":\"Chudidar\",\n" +
			"         \"product_price\":\"800.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20210824_1629801048.jpg\",\n" +
			"         \"product_desc\":\"<p>Cotton Chudidar</p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"12\",\n" +
			"         \"category_name\":\"Women Clothing\",\n" +
			"         \"product_name\":\"Western Dresses\",\n" +
			"         \"product_price\":\"600.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20210824_1629802060.jpg\",\n" +
			"         \"product_desc\":\"\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"14\",\n" +
			"         \"category_name\":\"Women Clothing\",\n" +
			"         \"product_name\":\"Western Dress\",\n" +
			"         \"product_price\":\"800.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20210824_1629806637.jpg\",\n" +
			"         \"product_desc\":\"\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"15\",\n" +
			"         \"category_name\":\"Home Appliances\",\n" +
			"         \"product_name\":\"Micro Oven\",\n" +
			"         \"product_price\":\"2200.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20210824_1629814968.jpg\",\n" +
			"         \"product_desc\":\"\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"23\",\n" +
			"         \"category_name\":\"Fashion\",\n" +
			"         \"product_name\":\"Ladies Bag\",\n" +
			"         \"product_price\":\"500.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20221004_1664895798.jpg\",\n" +
			"         \"product_desc\":\"<p>Ladies Bag<br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"24\",\n" +
			"         \"category_name\":\"Fashion\",\n" +
			"         \"product_name\":\"Ladies Bag\",\n" +
			"         \"product_price\":\"550.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20221004_1664896457.jpg\",\n" +
			"         \"product_desc\":\"<p>3 no. of Ladies Bag </p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"25\",\n" +
			"         \"category_name\":\"Fashion\",\n" +
			"         \"product_name\":\"Ladies Bags\",\n" +
			"         \"product_price\":\"380.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20211015_1634312536.jpg\",\n" +
			"         \"product_desc\":\"<p>One no. of Ladies Lather Bag</p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"26\",\n" +
			"         \"category_name\":\"Fashion\",\n" +
			"         \"product_name\":\"Ladies styles Bag\",\n" +
			"         \"product_price\":\"400.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20211015_1634312855.jpg\",\n" +
			"         \"product_desc\":\"<p><br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"28\",\n" +
			"         \"category_name\":\"Home Décor & Furniture\",\n" +
			"         \"product_name\":\"Furniture\",\n" +
			"         \"product_price\":\"20000.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20211229_1640717390.jpg\",\n" +
			"         \"product_desc\":\"\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"30\",\n" +
			"         \"category_name\":\"Sharee\",\n" +
			"         \"product_name\":\"Designer Shree\",\n" +
			"         \"product_price\":\"950.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220117_1642420992.jpg\",\n" +
			"         \"product_desc\":\"<p>Grand Look</p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"33\",\n" +
			"         \"category_name\":\"Women Clothing\",\n" +
			"         \"product_name\":\"Chudidar Suit\",\n" +
			"         \"product_price\":\"800.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220119_1642615897.jpg\",\n" +
			"         \"product_desc\":\"<p>Chudidar Suit<br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"34\",\n" +
			"         \"category_name\":\"Wedding-dresses\",\n" +
			"         \"product_name\":\"Marrage joda\",\n" +
			"         \"product_price\":\"1800.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220122_1642860583.jpg\",\n" +
			"         \"product_desc\":\"<p>Marrage joda<br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"36\",\n" +
			"         \"category_name\":\"Ethnic ware\",\n" +
			"         \"product_name\":\"Couple set\",\n" +
			"         \"product_price\":\"800.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220125_1643132931.jpg\",\n" +
			"         \"product_desc\":\"Couple set\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"42\",\n" +
			"         \"category_name\":\"Ladis Fashion\",\n" +
			"         \"product_name\":\"Kurtis\",\n" +
			"         \"product_price\":\"280.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220211_1644599438.jpg\",\n" +
			"         \"product_desc\":\"<p>Kurtis<br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"43\",\n" +
			"         \"category_name\":\"Electronic\",\n" +
			"         \"product_name\":\"Head phone\",\n" +
			"         \"product_price\":\"320.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220211_1644602240.jpg\",\n" +
			"         \"product_desc\":\"<p>Head phone<br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"44\",\n" +
			"         \"category_name\":\"Home Décor & Furniture\",\n" +
			"         \"product_name\":\"Sofa cum Bed\",\n" +
			"         \"product_price\":\"11500.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220211_1644603203.jpg\",\n" +
			"         \"product_desc\":\"<p>Sofa cum Bed<br></p>\"\n" +
			"      },\n" +
			"      {\n" +
			"         \"product_id\":\"49\",\n" +
			"         \"category_name\":\"Ladis Fashion\",\n" +
			"         \"product_name\":\"Goun\",\n" +
			"         \"product_price\":\"360.00\",\n" +
			"         \"product_image\":\"https://kkkamya.in/uploads/product/Product_20220226_1645888210.jpg\",\n" +
			"         \"product_desc\":\"<p><span style=\\\"color: rgb(5, 5, 5); font-family: \\\"Segoe UI Historic\\\", \\\"Segoe UI\\\", Helvetica, Arial, sans-serif; font-size: 15px; white-space: pre-wrap;\\\">Ensemble Kurtis</span></p><p><span style=\\\"color: rgb(5, 5, 5); font-family: \\\"Segoe UI Historic\\\", \\\"Segoe UI\\\", Helvetica, Arial, sans-serif; font-size: 15px; white-space: pre-wrap;\\\">Get this fashionable and stylish kurta for you to wear in any occasion. This is a comfortable wear.  This Regular Kurta is a perfect partner for all your ethnic wear needs.  You can pair it up with any causal or ethnic bottoms and you're sure to make heads turn. </span></p>\"\n" +
			"      }\n" +
			"   ]\n" +
			"}";
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home__fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}

	private void initialize(Bundle _savedInstanceState, View _view) {

		linear_g2 = (LinearLayout) _view.findViewById(R.id.linear_g2);
		linear_g22 = (LinearLayout) _view.findViewById(R.id.linear_g3);
		linear_g222 = (LinearLayout) _view.findViewById(R.id.linear_g4);
		linear_g2222 = (LinearLayout) _view.findViewById(R.id.linear_g5);



		listview1 = (ListView) _view.findViewById(R.id.listview1);
		listview2 = (ListView) _view.findViewById(R.id.listview2);
		listview3 = (ListView) _view.findViewById(R.id.listview3);
		listview4 = (ListView) _view.findViewById(R.id.listview4);


		bg = (LinearLayout) _view.findViewById(R.id.bg);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		vscroll1 = (ScrollView) _view.findViewById(R.id.vscroll1);
		linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
		search = (LinearLayout) _view.findViewById(R.id.search);
		linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
		cart_count = (TextView) _view.findViewById(R.id.cart_count);
		imageview3 = (ImageView) _view.findViewById(R.id.imageview3);

		menu = (ImageView) _view.findViewById(R.id.menu);

		welcome = (TextView) _view.findViewById(R.id.welcome);
		user_name = (TextView) _view.findViewById(R.id.user_name);
		imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
		search_textview = (TextView) _view.findViewById(R.id.search_textview);
		scroll = (LinearLayout) _view.findViewById(R.id.scroll);
		viewpager1 = (ViewPager) _view.findViewById(R.id.viewpager1);
		viewpager2 = (ViewPager) _view.findViewById(R.id.viewpager2);
		viewpager3 = (ViewPager) _view.findViewById(R.id.viewpager3);
		slider_layout = (LinearLayout) _view.findViewById(R.id.slider_layout);
		gridview1 = (GridView) _view.findViewById(R.id.gridview1);
		title_layout = (LinearLayout) _view.findViewById(R.id.title_layout);

		title = (TextView) _view.findViewById(R.id.title);
		title2 = (TextView) _view.findViewById(R.id.title2);
		title3 = (TextView) _view.findViewById(R.id.title22);
		title4 = (TextView) _view.findViewById(R.id.title3);

		show_more = (TextView) _view.findViewById(R.id.show_more);
		show_more2 = (TextView) _view.findViewById(R.id.show_more2);
		show_more3 = (TextView) _view.findViewById(R.id.show_more23);
		show_more4 = (TextView) _view.findViewById(R.id.show_more3);


menu.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View view) {

		((LayoutActivity) Objects.requireNonNull(getActivity())).openDrawer();

	}
});

		re = new RequestNetwork((Activity)getContext());

		search_textview.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {

				return true;
			}
		});

		_re_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;

				//Toast.makeText(getActivity(), _response, Toast.LENGTH_SHORT).show();
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
		SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", 0);

		try{

			String _phone=sh.getString("mobile", "");
			String _fullname=sh.getString("fullname", "");
			String _email=sh.getString("email", "");
			String _id = sh.getString("user_id", "");
			//String _bill = sh.getString("bill_id", "");


			//phone.setText(_phone);
			user_name.setText(_fullname);
			//email.setText(_email);
			//userid.setText(_id);
			//bill.setText(_bill +"\nbill_id");

		}catch(Exception e)
		{
			e.printStackTrace();
		}







		//cart_count.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFEB3B));
		search.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)4, 0xFFBDBDBD, Color.TRANSPARENT));
		//slider_layout.setBackground(new GradientDrawable(GradientDrawable.Orientation.BR_TL,new int[] {0xFFFFEBEE,0xFFFCE4EC}));
		cart_count.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);


		show_more.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		show_more2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		title2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		show_more3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		title3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		show_more4.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		title4.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);




		search_textview.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
		user_name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		welcome.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

		//_show_response(tempJson);

		_api_request();


		show_more3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent in = new Intent();
				in.setClass(getContext(), SeeMoreActivity.class);
				in.putExtra("type","fresh_new");
				startActivity(in);


			}
		});

	}

	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

		super.onActivityResult(_requestCode, _resultCode, _data);

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

	}

	@Override
	public void onPause() {
		super.onPause();
		srcoll_timer.cancel();
	}

	@Override
	public void onResume() {
		super.onResume();
		_slider();
	}
	public void _slider () {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://img.freepik.com/free-vector/sale-banner-with-product-description_1361-1333.jpg?w=1060&t=st=1668227787~exp=1668228387~hmac=ac2270de07f9c4c11efb8059d177001a13632e89fb616a24c7c29dc2d0515e59");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://img.freepik.com/free-vector/gradient-summer-sale-banner-with-photo_23-2148942890.jpg?w=1060&t=st=1668228482~exp=1668229082~hmac=2d1f5b975c21c83ebb40751fabd8a8c362122c8e17336529251d26a637ae89bf");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://img.freepik.com/free-vector/realistic-akshaya-tritiya-sale-banner-template_52683-83400.jpg?w=1060&t=st=1668228410~exp=1668229010~hmac=c7dcf284d7c11abf2f723b717187c9cd31975637dc102ff75fe7d3f01cceea8c");
			listmap.add(_item);
		}

		final float scaleFactor = 0.96f;
		viewpager1.setPageMargin(-15);
		viewpager1.setOffscreenPageLimit(2);
		viewpager1.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager1.setAdapter(new Viewpager1Adapter(listmap));

		viewpager2.setPageMargin(-15);
		viewpager2.setOffscreenPageLimit(2);
		viewpager2.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager2.setAdapter(new Viewpager1Adapter(listmap));

		viewpager3.setPageMargin(-15);
		viewpager3.setOffscreenPageLimit(2);
		viewpager3.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager3.setAdapter(new Viewpager1Adapter(listmap));



		srcoll_timer = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						count++;
						if (count == 4) {
							count = 0;
						}
						viewpager1.setCurrentItem((int)count);
						viewpager2.setCurrentItem((int)count);
						viewpager3.setCurrentItem((int)count);

					}
				});
			}
		};
		_timer.scheduleAtFixedRate(srcoll_timer, (int)(1500), (int)(3000));
	}



	public void _api_request () {
		map.clear();
		listmap.clear();
		map.put("method", "allproducts");
		re.setParams(map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _re_request_listener);
	}

/*	public void _api_request () {
		map.clear();
		listmap.clear();
		map.put("method", "allproducts");
		re.setParams(map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _re_request_listener);
	}*/

	public void _api_request_category () {
		map.clear();
		listmap.clear();
		map.put("method", "allcategories");
		re.setParams(map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET, "https://kkkamya.in/index.php/Api_request/api_list?", "", _re_request_listener);
	}


	public void _show_response (final String _response) {
		try {
			map.clear();
			results.clear();
			if (_response.contains("200")) {
				map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				// must add resultSet
				//" list " is a String datatype
				list = (new Gson()).toJson(map.get("0"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				results = new Gson().fromJson(list, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				// refresh the list or recycle or grid

				Collections.shuffle(results);

				_grid_from_list(results);

				_grid_from_list2(results);

				_grid_from_list3(results);

				_grid_from_list4(results);



				//gridview1.setAdapter(new Gridview1Adapter(results));
				//gridview1.setNumColumns((int)100);
				//Util.showMessage(getContext(), _response);
			}
		} catch(Exception e) {

			Util.showMessage(getContext(), "Error \n\n"+e);
		}
	}




	public class Viewpager1Adapter extends PagerAdapter {
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}

		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getContext();
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}

		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}

		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}

		@Override
		public CharSequence getPageTitle(int pos) {
			// use the activitiy event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}

		@Override
		public  Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.custom_slider, _container, false);

			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);




			Glide.with(Objects.requireNonNull(getContext()))
					.load(Uri.parse(listmap.get((int)_position).get("image").toString()))
					.error(R.drawable.pyramids)
					.placeholder(R.drawable.pyramids)
					.thumbnail(0.01f)
					.into(imageview1);

			_container.addView(_view);
			return _view;
		}
	}

/*
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			product_name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), 1);
			qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), 0);
			price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), 0);
			price.setText(results.get((int)_position).get("product_price").toString());
			product_name.setText(results.get((int)_position).get("product_name").toString());
			Glide.with(getContext()).load(Uri.parse(results.get((int)_position).get("product_image").toString())).into(product_image);
			wish_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
			product_desc = results.get((int)_position).get("product_desc").toString();
			wish_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Util.showMessage(getContext(), "Wishlist Added");
				}
			});

			return _view;
		}
	}
*/


	public void _grid_from_list (final ArrayList<HashMap<String, Object>> _listmap) {
		GridView gridView = new GridView(getActivity());

		gridView.setLayoutParams(new GridView.LayoutParams(_listmap.size()*(int)getDip(180), GridLayout.LayoutParams.WRAP_CONTENT));
		gridView.setBackgroundColor(Color.TRANSPARENT);

		gridView.setNumColumns(_listmap.size());
		gridView.setColumnWidth(GridView.AUTO_FIT);
		gridView.setVerticalSpacing(0);

		/*

Code By EPIC Technical Tricks on 26th April 2022

*/


		gridView.setHorizontalSpacing(0);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.invalidateViews();






		gridView.setAdapter(new Listview1Adapter(_listmap));

		//here change your adapter

		((BaseAdapter)gridView.getAdapter()).notifyDataSetChanged();

		//linear_g.removeAllViews();

		linear_g2.removeAllViews();

		//linear_g.addView(gridView);

		linear_g2.addView(gridView);




	}

	public void _grid_from_list2 (final ArrayList<HashMap<String, Object>> _listmap) {
		GridView gridView = new GridView(getActivity());

		gridView.setLayoutParams(new GridView.LayoutParams(_listmap.size()*(int)getDip(180), GridLayout.LayoutParams.WRAP_CONTENT));
		gridView.setBackgroundColor(Color.TRANSPARENT);

		gridView.setNumColumns(_listmap.size());
		gridView.setColumnWidth(GridView.AUTO_FIT);
		gridView.setVerticalSpacing(0);

		/*

Code By EPIC Technical Tricks on 26th April 2022

*/


		gridView.setHorizontalSpacing(0);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.invalidateViews();






		gridView.setAdapter(new Listview2Adapter(_listmap));

		//here change your adapter

		((BaseAdapter)gridView.getAdapter()).notifyDataSetChanged();

		//linear_g.removeAllViews();


		linear_g22.removeAllViews();

		//linear_g.addView(gridView);


		linear_g22.addView(gridView);



	}

	public void _grid_from_list3 (final ArrayList<HashMap<String, Object>> _listmap) {
		GridView gridView = new GridView(getActivity());

		gridView.setLayoutParams(new GridView.LayoutParams(_listmap.size()*(int)getDip(180), GridLayout.LayoutParams.WRAP_CONTENT));
		gridView.setBackgroundColor(Color.TRANSPARENT);

		gridView.setNumColumns(_listmap.size());
		gridView.setColumnWidth(GridView.AUTO_FIT);
		gridView.setVerticalSpacing(0);

		/*

Code By EPIC Technical Tricks on 26th April 2022

*/


		gridView.setHorizontalSpacing(0);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.invalidateViews();






		gridView.setAdapter(new Listview3Adapter(_listmap));

		//here change your adapter

		((BaseAdapter)gridView.getAdapter()).notifyDataSetChanged();

		//linear_g.removeAllViews();


		linear_g222.removeAllViews();


		//linear_g.addView(gridView);


		linear_g222.addView(gridView);



	}

	public void _grid_from_list4 (final ArrayList<HashMap<String, Object>> _listmap) {
		GridView gridView = new GridView(getActivity());

		gridView.setLayoutParams(new GridView.LayoutParams(_listmap.size()*(int)getDip(180), GridLayout.LayoutParams.WRAP_CONTENT));
		gridView.setBackgroundColor(Color.TRANSPARENT);

		gridView.setNumColumns(_listmap.size());
		gridView.setColumnWidth(GridView.AUTO_FIT);
		gridView.setVerticalSpacing(0);

		/*

Code By EPIC Technical Tricks on 26th April 2022

*/


		gridView.setHorizontalSpacing(0);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.invalidateViews();






		gridView.setAdapter(new Listview4Adapter(_listmap));

		//here change your adapter

		((BaseAdapter)gridView.getAdapter()).notifyDataSetChanged();

		//linear_g.removeAllViews();


		linear_g2222.removeAllViews();

		//linear_g.addView(gridView);


		linear_g2222.addView(gridView);



	}





	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			try{

				product_name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

				price.setText("₹"+ Objects.requireNonNull(results.get((int) _position).get("product_price")).toString().replaceAll("[.]00",""));
				product_name.setText(results.get((int)_position).get("product_name").toString());




				Glide.with(getContext())
						.load(Uri.parse(results.get((int)_position).get("product_image").toString()))
						.error(R.drawable.pyramids)
						.placeholder(R.drawable.pyramids)
						.thumbnail(0.01f)
						.into(product_image);


				wish_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));


				 wish_btn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {

						wish_btn.setImageResource(R.drawable.red_heart);
						Util.showMessage(getContext(), "Wishlist Added");
					}
				});

				cardview1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {

						Intent in = new Intent();
						in.setClass(getContext(),ViewProductActivity.class);
						in.putExtra("imageURL", Objects.requireNonNull(results.get((int) _position).get("product_image")).toString());
						//in.putExtra("desc",Objects.requireNonNull(results.get((int) _position).get("product_desc")).toString());
						in.putExtra("price",price.getText());
						in.putExtra("cat_name", Objects.requireNonNull(results.get((int) _position).get("category_name")).toString());
						in.putExtra("name",product_name.getText());

						_ActivityTransition(product_name, "p", in);


						//startActivity(in);

					}
				});


			}catch (Exception e)
			{
				Util.showMessage(getContext(),"Error on api parameter \n\n"+e);
			}



			return _view;
		}
	}


	public void _ActivityTransition (final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(getActivity(), _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}


	public class Listview2Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			product_name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
			qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			price.setText("₹"+results.get((int)_position).get("product_price").toString().replaceAll("[.]00",""));
			product_name.setText(results.get((int)_position).get("product_name").toString());




			Glide.with(getContext())
					.load(Uri.parse(results.get((int)_position).get("product_image").toString()))
					.error(R.drawable.pyramids)
					.placeholder(R.drawable.pyramids)
					.thumbnail(0.01f)
					.into(product_image);


			wish_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
			//product_desc = results.get((int)_position).get("product_desc").toString();
			wish_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {

					wish_btn.setImageResource(R.drawable.red_heart);
					Util.showMessage(getContext(), "Wishlist Added");
				}
			});


			return _view;
		}
	}
	public class Listview3Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			product_name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
			qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			price.setText("₹"+results.get((int)_position).get("product_price").toString().replaceAll("[.]00",""));
			product_name.setText(results.get((int)_position).get("product_name").toString());




			Glide.with(getContext())
					.load(Uri.parse(results.get((int)_position).get("product_image").toString()))
					.error(R.drawable.pyramids)
					.placeholder(R.drawable.pyramids)
					.thumbnail(0.01f)
					.into(product_image);


			wish_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
				wish_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {

					wish_btn.setImageResource(R.drawable.red_heart);
					Util.showMessage(getContext(), "Wishlist Added");
				}
			});


			return _view;
		}
	}
	public class Listview4Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			product_name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
			qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
			price.setText("₹"+results.get((int)_position).get("product_price").toString().replaceAll("[.]00",""));
			product_name.setText(results.get((int)_position).get("product_name").toString());




			Glide.with(getContext())
					.load(Uri.parse(results.get((int)_position).get("product_image").toString()))
					.error(R.drawable.pyramids)
					.placeholder(R.drawable.pyramids)
					.thumbnail(0.01f)
					.into(product_image);


			wish_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
//			product_desc = results.get((int)_position).get("product_desc").toString();
			wish_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {

					wish_btn.setImageResource(R.drawable.red_heart);
					Util.showMessage(getContext(), "Wishlist Added");
				}
			});


			return _view;
		}
	}

	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
}
