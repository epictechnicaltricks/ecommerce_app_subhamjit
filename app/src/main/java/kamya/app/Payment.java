package kamya.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.cashfree.pg.CFPaymentService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;


public class Payment extends  AppCompatActivity  {

	int qty_count=1;
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private final String typeace = "";
	
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();
	private final HashMap<String, Object> map = new HashMap<>();

	private final HashMap<String, Object> map2 = new HashMap<>();

	private HashMap<String, Object> api_map = new HashMap<>();

	String list="";

Button place_order;

	private TextView textview4;

	String user_id;


	private RequestNetwork token_api;
	private RequestNetwork.RequestListener _token_api_listener;

    String token_key="v79JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.s79BTM0AjNwUDN1EjOiAHelJCLiIlTJJiOik3YuVmcyV3QyVGZy9mIsEjOiQnb19WbBJXZkJ3biwiIxADMwIXZkJ3TiojIklkclRmcvJye.K3NKICVS5DcEzXm2VQUO_ZagtWMIKKXzYOqPZ4x0r2P_N3-PRu2mowm-8UXoyqAgsG";
    String amt = "1";
    String order_id = "order001";

    String app_id= "";
    String secret_key="";
    String msg="";

	/** https://docs.cashfree.com/docs/android-sdk **/

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.payment);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {

	//	loading = findViewById(R.id.lottie_loading);


		token_api = new RequestNetwork(this);


		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);

		place_order = findViewById(R.id.place_order_);



		textview4 = findViewById(R.id.textview4);





_token_api_listener = new RequestNetwork.RequestListener() {
	@Override
	public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

		if(response.contains("OK")){

			HashMap<String,Object> map = new HashMap<>();
			map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
			//token_key = Objects.requireNonNull(map.get("cftoken")).toString();
            token_key = "v79JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.s79BTM0AjNwUDN1EjOiAHelJCLiIlTJJiOik3YuVmcyV3QyVGZy9mIsEjOiQnb19WbBJXZkJ3biwiIxADMwIXZkJ3TiojIklkclRmcvJye.K3NKICVS5DcEzXm2VQUO_ZagtWMIKKXzYOqPZ4x0r2P_N3-PRu2mowm-8UXoyqAgsG";
			doPayment(order_id,amt,token_key,"TEST");
			// this is test  mode
		} else {

			// remove this project by shubhamjit
			// dt 29 nov 2022 9.44pm 3rd sem btech raajdhani college bbsr, odisha
			doPayment(order_id,amt,token_key,"TEST");

		}
	}

	@Override
	public void onErrorResponse(String tag, String message) {
		showMessage("No internet!");

	}};



     place_order.setOnClickListener(new View.OnClickListener() {
	 @Override
	 public void onClick(View view) {

		// doPayment(order_id,amt,token_key,"TEST");
		 // "TEST" or "PROD"
		TokenGenerate(order_id,amt);

	}
});

	}

	public void close(View view)
	{
		finish();
	}


	public void doPayment(String _order_id, String _amt, String _cftoken, String _stage) {

		SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

		try {
			String _phone = sh.getString("mobile", "");
			String _fullname = sh.getString("fullname", "");
			String _email = sh.getString("email", "");
			String _id = sh.getString("user_id", "");
			//String _bill = sh.getString("bill_id", "");

			HashMap<String, String> params = new HashMap<>();
			params.put(CFPaymentService.PARAM_APP_ID, app_id);
			params.put(CFPaymentService.PARAM_ORDER_ID,  _order_id);
			params.put(CFPaymentService.PARAM_CUSTOMER_EMAIL,  _email);
			params.put(CFPaymentService.PARAM_ORDER_AMOUNT, _amt);
			params.put(CFPaymentService.PARAM_CUSTOMER_NAME, _fullname);
			params.put(CFPaymentService.PARAM_CUSTOMER_PHONE, _phone);
			params.put(CFPaymentService.PARAM_NOTIFY_URL, "https://kkkamya.in/index.php/Api_request/api_list?method=payment_response");

			CFPaymentService.getCFPaymentServiceInstance().doPayment(Payment.this , params, _cftoken,_stage);




		}catch (Exception e){

			showMessage(e.toString());
		}


	}



	public void TokenGenerate(String _order_id, String _amt) {


		HashMap<String, Object> header = new HashMap<>();
		header.put("x-client-id",app_id);
		header.put("x-client-secret",secret_key);

		HashMap<String, Object> order_param = new HashMap<>();
		order_param.put("orderId",_order_id);
		order_param.put("orderAmount",_amt);
		order_param.put("orderCurrency","INR");

		token_api.setHeaders(header);
		token_api.setParams(order_param, RequestNetworkController.REQUEST_BODY);
		token_api.startRequestNetwork(RequestNetworkController.POST,
				"https://test.cashfree.com/api/v2/cftoken/order",
				"", _token_api_listener);




	}

	private void initializeLogic() {

		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_changeActivityFont("google_sans_medium");
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);

		SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
		user_id = sh.getString("user_id", "");



	}

	@Override
	protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//Same request code for all payment APIs.
		Log.d("cashfree", "ReqCode : " + CFPaymentService.REQ_CODE);
		Log.d("cashfree", "API Response : ");

		msg = "";
		//Prints all extras. Replace with app logic.
		if (requestCode == CFPaymentService.REQ_CODE && data != null) {
			Bundle bundle = data.getExtras();
			if (bundle != null) {

				for (String  key  :  bundle.keySet()) {
					if (bundle.getString(key) != null) {


						msg = key + " : " + bundle.getString(key)+"\n"+msg;

						//Log.d("csfree", key + " : " + bundle.getString(key));
					}

				}

				AlertDialog.Builder builder1 = new AlertDialog.Builder(Payment.this);
				builder1.setTitle("Payment response");
				builder1.setMessage(msg);
				builder1.setCancelable(false);

				builder1.setPositiveButton(
						"Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								startActivity(new Intent(getApplicationContext(),ThankyouActivity.class));
								//finish();
							}
						});

				AlertDialog alert11 = builder1.create();
				alert11.show();



				//	Toast.makeText(this, bundle.toString(), Toast.LENGTH_SHORT).show();

			}
		}

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




	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

}
