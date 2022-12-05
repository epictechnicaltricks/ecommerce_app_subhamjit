package kamya.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class Cart_FragmentActivity extends  Fragment  {

	private TimerTask time;
	private final Timer _timer = new Timer();
	private final HashMap<String, Object> map = new HashMap<>();

	private final HashMap<String, Object> map2 = new HashMap<>();

	private HashMap<String, Object> api_map = new HashMap<>();
	private String list = "";
	
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();
	//private final ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private RecyclerView recyclerview4;
	private ProgressBar progressbar1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private MaterialButton checkout;
	private TextView subtotal;
	private TextView total_price;
	
	private RequestNetwork re;
	private RequestNetwork.RequestListener _re_request_listener;


	private LottieAnimationView loading;


	int qty_count=1;

	private RequestNetwork req_add_to_cart;
	private RequestNetwork.RequestListener _req_add_to_cart_listener;

	private RequestNetwork req_delete_cart;
	private RequestNetwork.RequestListener _req_delete_cart_listener;

	String user_id;

	private SwipeRefreshLayout swiperefreshlayout1;

	private TextView cart_count;

	Button checkout_1;

	private SharedPreferences sh;

	long total_price_of_cart_;

	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.cart__fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();

		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {

		swiperefreshlayout1 = (SwipeRefreshLayout) _view.findViewById(R.id.swiperefreshlayout1);

		cart_count = _view.findViewById(R.id._cart_count);


		sh = getActivity().getSharedPreferences("sh", Activity.MODE_PRIVATE);

		loading = _view.findViewById(R.id.lottie_loading);
		linear1 = _view.findViewById(R.id.linear1);
		textview1 = _view.findViewById(R.id.textview1);
		recyclerview4 = _view.findViewById(R.id.recyclerview4);
		progressbar1 = _view.findViewById(R.id.progressbar1);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		checkout_1 = _view.findViewById(R.id.checkout_);
		subtotal = _view.findViewById(R.id.subtotal);
		total_price = _view.findViewById(R.id.total_price);

		re = new RequestNetwork((Activity)getContext());
		req_add_to_cart = new RequestNetwork(getActivity());
		req_delete_cart= new RequestNetwork(getActivity());



		_re_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;

				loading.setVisibility(View.GONE);
				swiperefreshlayout1.setRefreshing(false);
				_show_response(_response);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

				Toast.makeText(getActivity(), "No Internet !", Toast.LENGTH_SHORT).show();
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

				Toast.makeText(getContext(), "Cart not updated, No Internet !\n\n"+_message, Toast.LENGTH_LONG).show();

			}
		};

		_req_delete_cart_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _response = _param2;

				Toast.makeText(getContext(), "Removed Successfully", Toast.LENGTH_SHORT).show();
				_api_request(user_id);
				// THIS REQUEST FOR UPDATE CART VALUE

			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _message = _param2;

				Toast.makeText(getContext(), "Cart not updated, No Internet !\n\n"+_message, Toast.LENGTH_LONG).show();

			}
		};


		checkout_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

                startActivity(new Intent(getContext(),MyAddressActivity.class));
			}
		});


	}
	
	private void initializeLogic() {


		SharedPreferences sh = Objects.requireNonNull(getActivity()).getSharedPreferences("MySharedPref", 0);
		user_id = sh.getString("user_id", "");
		//Toast.makeText(getContext(), user_id, Toast.LENGTH_SHORT).show();

		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				_api_request(user_id);
				swiperefreshlayout1.setRefreshing(false);
			}
		});

		cart_count.setTypeface(Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		checkout_1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		total_price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		subtotal.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
	}

	@Override
	public void onResume() {
		super.onResume();
		_api_request(user_id);
	}

	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	public void _api_request (String user_id) {

		swiperefreshlayout1.setRefreshing(true);
		loading.setVisibility(View.VISIBLE);
		//map.clear();
		results.clear();
		map.put("method", "cart_itemlist");
		map.put("user_id", user_id);
		re.setParams(map, RequestNetworkController.REQUEST_PARAM);
		re.startRequestNetwork(RequestNetworkController.GET,
				"https://kkkamya.in/index.php/Api_request/api_list?",
				"", _re_request_listener);
	}



	public void _api_request_delete_cart_item (String _cart_id) {
		map.clear();
		results.clear();
		map.put("method", "deletecartitem");
		map.put("cart_id", _cart_id );
		req_delete_cart.setParams(map, RequestNetworkController.REQUEST_PARAM);
		req_delete_cart.startRequestNetwork(RequestNetworkController.GET,
				"https://kkkamya.in/index.php/Api_request/api_list?",
				"", _req_delete_cart_listener);
	}
	




	public void _show_response (final String _response) {
		try {
			api_map.clear();
			results.clear();


				if (_response.contains("200")) {


				//api_map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				//// must add resultSet
				////" list " is a String datatype
				//list = (new Gson()).toJson(api_map.get("res"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());




					//Collections.reverse(listmap);

					//recyclerview1.setAdapter(new Recyclerview1Adapter(listmap));
					//recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));


					results = new Gson().fromJson(sh.getString("cart_data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					// refresh the list or recycle or grid

					if(results.size()==0){
						cart_count.setVisibility(View.GONE);
					}else{
						if(results.size()==1){

							cart_count.setText(results.size()+" item available");

						}else {

							cart_count.setText("Total items: "+ results.size());

						}

					}

					total_price_of_cart_ = 0; // dont remove

					total_price.setText("₹"+ calculate_cart_total_price());

					_reftesh();




			}else {
					cart_count.setVisibility(View.GONE);
					Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
				}



		} catch(Exception e) {

			cart_count.setVisibility(View.GONE);
			Util.showMessage(getContext(), "Error on cart \n\n"+_response);
			e.printStackTrace();
		}
	}


	private long calculate_cart_total_price()
	{

		for(int x=0; results.size()>x; x++)
		{
			long value = (long)(Double.parseDouble(results.get(x).get("quantity").toString()) * Double.parseDouble(Objects.requireNonNull(results.get(x).get("product_price")).toString()));
			//results.get(_position).put("total_price",value);

			total_price_of_cart_ = value +  total_price_of_cart_;

		}
		return total_price_of_cart_;
	}
	public void _reftesh () {
		recyclerview4.setAdapter(new Recyclerview4Adapter(results));
		recyclerview4.setLayoutManager(new LinearLayoutManager(getContext()));
	}




/*
	public void _api_request_update_to_cart (String _product_id, String _qty, String _attrVals) {


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
*/



	public void _api_request_add_to_cart (String _product_id, String _qty, String _attrVals) {


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
				RequestNetworkController.POST,
				"https://kkkamya.in/index.php/Api_request/api_list?"
				, ""
				, _req_add_to_cart_listener);
	}



	public class Recyclerview4Adapter extends Adapter<Recyclerview4Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.cart_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
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
				name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				pro_price_and_qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				product_attr_val.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				total_price.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				qty.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				varient.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

				//Glide.with(getContext()).load(Uri.parse("https://kkkamya.in/uploads/product/".concat(results.get(_position).get("product_image").toString()))).into(product_img);

				Glide.with(getContext()).load(Objects.requireNonNull(results.get(_position).get("product_image")).toString()).into(product_img);



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
							results.get(_position).put("quantity",qty.getText().toString());

							String value = String.valueOf((long)(Double.parseDouble(qty.getText().toString()) * Double.parseDouble(Objects.requireNonNull(results.get(_position).get("product_price")).toString())));
							results.get(_position).put("total_price",value);

							sh.edit().putString("cart_data", new Gson().toJson(results)).apply();
							request_add_cart(Objects.requireNonNull(results.get(_position).get("product_id")).toString(),qty.getText().toString(),"");

						}

					}
				});
				minus.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View _view) {
						qty_count = Integer.parseInt(qty.getText().toString());

						if(qty_count>1){

							qty.setText(String.valueOf(--qty_count));
							results.get(_position).put("quantity",qty.getText().toString());

							String value = String.valueOf((long)(Double.parseDouble(qty.getText().toString()) * Double.parseDouble(Objects.requireNonNull(results.get(_position).get("product_price")).toString())));
							results.get(_position).put("total_price",value);


							sh.edit().putString("cart_data", new Gson().toJson(results)).apply();


							request_add_cart(Objects.requireNonNull(results.get(_position).get("product_id")).toString(),qty.getText().toString(),"");

						}

					}
				});
				delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View _view) {

						try{

							results.remove(_position);
							sh.edit().putString("cart_data", new Gson().toJson(results)).apply();
							_api_request_delete_cart_item(Objects.requireNonNull(results.get(_position).get("cart_id")).toString());



						}catch (Exception e){

						}


						//Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
							}
				});

				delete.setColorFilter(0xFFD50000);

			} catch(Exception e) {

				Util.showMessage(getContext(), "Error on parameters \n\n" +e);
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



	@Override
	public void onPause() {
		super.onPause();
		//Toast.makeText(getContext(), "Cart on pause", Toast.LENGTH_SHORT).show();

		if(time!=null)
		{
			//Toast.makeText(getContext(), "Cart time cancel", Toast.LENGTH_SHORT).show();

			time.cancel();
			// dont remove this line
		}
	}



	private void request_add_cart(final String _produbt_id, final String _qty, final String _attrVal) {
		if(time!=null)
		{
			time.cancel();
			// dont remove this line
		}

		time = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {



						_api_request_add_to_cart(_produbt_id,_qty,_attrVal);

						time.cancel();
					}
				});
			}
		};
		_timer.schedule(time, 1600);
	}


}
