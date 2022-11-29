package kamya.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class MyOrdersActivity extends  AppCompatActivity  {


	private TimerTask time;
	private final Timer _timer = new Timer();
	private final HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> api_map = new HashMap<>();
	private String list = "";

	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();






	int qty_count=1;

	private RequestNetwork req_orders;
	private RequestNetwork.RequestListener _req_orders_listener;

	private RecyclerView recyclerview4;
	private LottieAnimationView loading;


	String user_id;

	private TextView cart_count;
	private TextView textview1;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.my_order);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

		recyclerview4 = findViewById(R.id.recyclerview4);
		req_orders = new RequestNetwork(this);
		loading = findViewById(R.id.lottie_loading);
		textview1 = findViewById(R.id.textview1);

		_req_orders_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;

				loading.setVisibility(View.GONE);

				showMessage(_response);

				_show_response(_response);
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;

				Toast.makeText(getApplicationContext(), "No Internet !", Toast.LENGTH_SHORT).show();
			}
		};


	}

	/** Called when the user touches the button */
	public void close(View view)
	{
		finish();
	}

	private void initializeLogic() {

		SharedPreferences sh = Objects.requireNonNull(getSharedPreferences("MySharedPref", MODE_PRIVATE));
		user_id = sh.getString("user_id", "");
		//Toast.makeText(getContext(), user_id, Toast.LENGTH_SHORT).show();
		_api_request(user_id);

		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);

		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);



	}


	public void _api_request (String user_id) {

		loading.setVisibility(View.VISIBLE);
		map.clear();
		results.clear();
		map.put("method", "myorders");
		map.put("user_id", "291");
		req_orders.setParams(map, RequestNetworkController.REQUEST_PARAM);
		req_orders.startRequestNetwork(RequestNetworkController.GET,
				"https://kkkamya.in/index.php/Api_request/api_list?",
				"",
				_req_orders_listener);
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

				//Toast.makeText(this, _response, Toast.LENGTH_SHORT).show();

				if(results.size()>0){
					recyclerview4.setAdapter(new Recyclerview4Adapter(results));
					recyclerview4.setLayoutManager(new LinearLayoutManager(this));

				}else {

					Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show();
				}



			}

		} catch(Exception e) {
			Util.showMessage(getApplicationContext(), "Error on show response \n"+e);
		}
	}

	/*public void _reftesh () {

			recyclerview4.setLayoutManager(new LinearLayoutManager(this));
	}*/



	public class Recyclerview4Adapter extends RecyclerView.Adapter<Recyclerview4Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.my_order_cus, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}

		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;

			final ImageView product_img = _view.findViewById(R.id.product_img);

			final TextView pname = _view.findViewById(R.id.pname);
			final TextView status = _view.findViewById(R.id.status);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final TextView order_date = _view.findViewById(R.id.order_date);
			final TextView view = _view.findViewById(R.id.view);

			try {

				pname.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				status.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
				view.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
				order_date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);

				Glide.with(getApplicationContext())
						.load(Uri.parse(Objects.requireNonNull(results.get(_position).get("products")).toString()))
						.error(R.drawable.pyramids)
						.placeholder(R.drawable.pyramids)
						.thumbnail(0.01f)
						.into(product_img);

				pname.setText("Order on ".concat(results.get(_position).get("order_time").toString()));



				if(results.get(_position).get("delivery_status")!=null)
				status.setText(Objects.requireNonNull(results.get(_position).get("delivery_status")).toString());



				order_date.setText(Objects.requireNonNull(results.get(_position).get("product_name")).toString());

				view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View _view) {

						Toast.makeText(MyOrdersActivity.this, "coming soon..", Toast.LENGTH_SHORT).show();

					}
				});


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






	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

}
