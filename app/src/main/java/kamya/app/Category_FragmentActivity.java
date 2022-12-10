package kamya.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Category_FragmentActivity extends  Fragment  {



	private String list = "";

	private final ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private HashMap<String, Object> api_map = new HashMap<>();
	private ArrayList<HashMap<String, Object>> results = new ArrayList<>();
	private final ArrayList<HashMap<String, Object>> map = new ArrayList<>();

	private LinearLayout bg;
	//private LinearLayout title_bg;
	private GridView gridview1;
	private LinearLayout linear2;
	private TextView textview1;
	private ProgressBar progressbar1;

	private RequestNetwork re;
	private RequestNetwork.RequestListener _re_request_listener;

	private LottieAnimationView loading;

@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.category__fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}

	private void initialize(Bundle _savedInstanceState, View _view) {


	    loading = _view.findViewById(R.id.lottie_loading);
		bg = _view.findViewById(R.id.bg);
		//title_bg = _view.findViewById(R.id.title_bg);
		gridview1 = _view.findViewById(R.id.gridview1);
		linear2 = _view.findViewById(R.id.linear2);
		textview1 = _view.findViewById(R.id.textview1);
		progressbar1 = _view.findViewById(R.id.progressbar1);
		re = new RequestNetwork((Activity)getContext());





			_re_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				loading.setVisibility(View.GONE);
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

		//_api_request();
		_api_request();
		gridview1.setVerticalSpacing(0);
		gridview1.setHorizontalSpacing(0);

		//title_bg.setElevation((float)10);

		textview1.setTypeface(Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);


}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

		super.onActivityResult(_requestCode, _resultCode, _data);

	}

	public void _api_request () {
		api_map.clear();
		results.clear();
		api_map.put("method", "allcategories");
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
		         Util.showMessage(getContext(), "Error ");
		}
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
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.catagory_custom, null);
			}

			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final ImageView categori_image = _view.findViewById(R.id.categori_image);
			final TextView name = _view.findViewById(R.id.name);



			Glide.with(getContext())
					.load(Uri.parse(Objects.requireNonNull(results.get(_position).get("category_image")).toString()))
					.error(R.drawable.pyramids)
					.placeholder(R.drawable.pyramids)
					.thumbnail(0.01f)
					.into(categori_image);

			name.setText(Objects.requireNonNull(results.get(_position).get("category_name")).toString());
			android.view.animation.Animation animation = new android.view.animation.ScaleAnimation(0f, 1f, 0, 1f, android.view.animation.Animation.RELATIVE_TO_SELF, 0f, android.view.animation.Animation.RELATIVE_TO_SELF, 1f);
			animation.setFillAfter(true);
			animation.setDuration(300);
			cardview1.setAnimation(animation);
			name.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);

			return _view;
		}
	}


}
