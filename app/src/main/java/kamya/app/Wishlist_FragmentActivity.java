package kamya.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Wishlist_FragmentActivity extends  Fragment  {


	private TextView t1, t2, t3;
WebView wb;

	/*private RelativeLayout linear1;
	private SwipeRefreshLayout swiperefreshlayout1;
	private LinearLayout no_internet;
	private LinearLayout progress;
	private WebView webview2;
	private LinearLayout linear2;

	private Button button1;
	private LottieAnimationView lottie1;

	private RequestNetwork internet;
	private RequestNetwork.RequestListener _internet_request_listener;*/

@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.wishlist__fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {

	//t1 = _view.findViewById(R.id.a);
		t2 = _view.findViewById(R.id.b);
		t3 = _view.findViewById(R.id.c);

		wb = _view.findViewById(R.id.wb);
		//t1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		t2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		t3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);


	/*	linear1 = _view.findViewById(R.id.linear1);
		swiperefreshlayout1 = _view.findViewById(R.id.swiperefreshlayout1);
		no_internet = _view.findViewById(R.id.no_internet);
		progress = _view.findViewById(R.id.progress);
		webview2 = _view.findViewById(R.id.webview2);
		webview2.getSettings().setJavaScriptEnabled(true);
		webview2.getSettings().setSupportZoom(true);
		linear2 = _view.findViewById(R.id.linear2);
		textview3 = _view.findViewById(R.id.textview3);
		textview1 = _view.findViewById(R.id.textview1);
		button1 = _view.findViewById(R.id.button1);
		lottie1 = _view.findViewById(R.id.lottie1);
		internet = new RequestNetwork(getActivity());

		webview2.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;

				if(_url.contains("kkkamya.com"))
				{
					progress.setVisibility(View.VISIBLE);
					no_internet.setVisibility(View.GONE);
					internet.startRequestNetwork(RequestNetworkController.GET, _url, "null", _internet_request_listener);

				}else {

					try {
						webview2.goBack();
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_url));
						startActivity( intent );

					}catch (Exception e) {

						Util.showMessage(getContext(),e.toString());
					}
						}
					super.onPageStarted(_param1, _param2, _param3);
			}

			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				progress.setVisibility(View.GONE);
				if (Util.isConnected(getActivity())) {
					swiperefreshlayout1.setVisibility(View.VISIBLE);
				}
				super.onPageFinished(_param1, _param2);
			}
		});

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview2.loadUrl(webview2.getUrl());
			}
		});

		_internet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				no_internet.setVisibility(View.GONE);
			}

			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				no_internet.setVisibility(View.VISIBLE);
				textview1.setText(_message);
				swiperefreshlayout1.setVisibility(View.GONE);
			}
		};
*/

	}
	
	private void initializeLogic() {



		wb.getSettings().setLoadWithOverviewMode(true);
		wb.getSettings().setUseWideViewPort(true);
		final WebSettings webSettings = wb.getSettings();
		final String newUserAgent;
		newUserAgent = "Mozilla/5.0 (Android) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
		webSettings.setUserAgentString(newUserAgent);



	wb.getSettings().setJavaScriptEnabled(true);
		wb.getSettings().setSupportZoom(true);
		wb.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;

				/*	if (!_url.contains("data:text/html ,<html><html><body><iframe width=\"600\" height=\"180\" src=\"https://www.youtube.com/embed/RK0IokOKO1g\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe><html>")) {
						Intent i = new Intent();
						i.setAction(Intent.ACTION_VIEW);
						i.setData(Uri.parse(_url));
						startActivity(i);
						//wb.loadUrl("webview1.loadUrl(\"data:text/html ,<html>\".concat(\"<html><body><iframe width=\\\"600\\\" height=\\\"180\\\" src=\\\"https://www.youtube.com/embed/RK0IokOKO1g\\\" title=\\\"YouTube video player\\\" frameborder=\\\"0\\\" allow=\\\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\\\" allowfullscreen></iframe>\".concat(\"<html>\")));");
					}*/

				super.onPageStarted(_param1, _param2, _param3); }

			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;

				super.onPageFinished(_param1, _param2);
			}
		});

		String video_code = "<iframe width=\"1280\" height=\"770\" src=\"https://www.youtube.com/embed/8R5Z3b-cfUE\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		wb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		wb.loadUrl("data:text/html ,<html>".concat(video_code.concat("<html>")));


	/*





		webview2.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webview2.loadUrl("https://www.kkkamya.com/about");
		no_internet.setVisibility(View.GONE);
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				webview2.loadUrl(webview2.getUrl());
				swiperefreshlayout1.setRefreshing(false);
			}
		});
		_WebView(true, true, true, true, true, webview2);
*/

	}



	public void _WebView (final boolean _js, final boolean _zoom, final boolean _download, final boolean _html, final boolean _cookies, final WebView _view) {
		_view.getSettings().setJavaScriptEnabled(_js); //Made by XenonDry
		CookieManager.getInstance().setAcceptCookie(_cookies);
		WebSettings webSettings = _view.getSettings();
		webSettings.setJavaScriptEnabled(_html);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(_html);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
			webSettings.setAllowFileAccessFromFileURLs(_html);
			webSettings.setAllowUniversalAccessFromFileURLs(_html);
		}
		if(_zoom == true){
			_view.getSettings().setBuiltInZoomControls(true);_view.getSettings().setDisplayZoomControls(false);
		}
		else if(_zoom == false){
			_view.getSettings().setBuiltInZoomControls(false);_view.getSettings().setDisplayZoomControls(true);
		}
		if(_download == true){


		}
		else if(_download == false){
			Util.showMessage(getContext(),"Downloads disabled!");
		}
	}




	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	
}
