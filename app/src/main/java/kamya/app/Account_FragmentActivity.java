package kamya.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Account_FragmentActivity extends  Fragment  { 
	
	
	private ScrollView vscroll1;
	private LinearLayout bg;
	private LinearLayout linear3;
	private LinearLayout profile;
	private LinearLayout linear5;
	private LinearLayout my_orders;
	private LinearLayout linear7;
	private LinearLayout my_addres;
	private LinearLayout linear9;
	private LinearLayout notification;
	private LinearLayout linear11;
	private LinearLayout terms;
	private LinearLayout linear13;
	private LinearLayout help;
	private LinearLayout linear15;
	private LinearLayout logout;
	private LinearLayout linear17;
	private ImageView imageview1;
	private ImageView imageview2;
	private TextView textview1;
	private ImageView imageview3;
	private TextView textview2;
	private ImageView imageview4;
	private TextView textview3;
	private ImageView imageview5;
	private TextView textview4;
	private TextView notification_count;
	private ImageView imageview6;
	private TextView textview5;
	private ImageView imageview7;
	private TextView textview7;
	private ImageView imageview8;
	private TextView textview6;
	
	private Intent i = new Intent();
	private Intent wp = new Intent();
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.account__fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		vscroll1 = (ScrollView) _view.findViewById(R.id.vscroll1);
		bg = (LinearLayout) _view.findViewById(R.id.bg);
		linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
		profile = (LinearLayout) _view.findViewById(R.id.profile);
		linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
		my_orders = (LinearLayout) _view.findViewById(R.id.my_orders);
		linear7 = (LinearLayout) _view.findViewById(R.id.linear7);
		my_addres = (LinearLayout) _view.findViewById(R.id.my_addres);
		linear9 = (LinearLayout) _view.findViewById(R.id.linear9);
		notification = (LinearLayout) _view.findViewById(R.id.notification);
		linear11 = (LinearLayout) _view.findViewById(R.id.linear11);
		terms = (LinearLayout) _view.findViewById(R.id.terms);
		linear13 = (LinearLayout) _view.findViewById(R.id.linear13);
		help = (LinearLayout) _view.findViewById(R.id.help);
		linear15 = (LinearLayout) _view.findViewById(R.id.linear15);
		logout = (LinearLayout) _view.findViewById(R.id.logout);
		linear17 = (LinearLayout) _view.findViewById(R.id.linear17);
		imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
		imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
		textview1 = (TextView) _view.findViewById(R.id.textview1);
		imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		imageview4 = (ImageView) _view.findViewById(R.id.imageview4);
		textview3 = (TextView) _view.findViewById(R.id.textview3);
		imageview5 = (ImageView) _view.findViewById(R.id.imageview5);
		textview4 = (TextView) _view.findViewById(R.id.textview4);
		notification_count = (TextView) _view.findViewById(R.id.notification_count);
		imageview6 = (ImageView) _view.findViewById(R.id.imageview6);
		textview5 = (TextView) _view.findViewById(R.id.textview5);
		imageview7 = (ImageView) _view.findViewById(R.id.imageview7);
		textview7 = (TextView) _view.findViewById(R.id.textview7);
		imageview8 = (ImageView) _view.findViewById(R.id.imageview8);
		textview6 = (TextView) _view.findViewById(R.id.textview6);
		
		profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext(), ProfileActivity.class);
				startActivity(i);
			}
		});
		
		my_orders.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext(), MyOrdersActivity.class);
				startActivity(i);
			}
		});
		
		my_addres.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext(), MyAddressActivity.class);
				startActivity(i);
			}
		});
		
		notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext(), NotificationActivity.class);
				startActivity(i);
			}
		});
		
		terms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext(), TermsActivity.class);
				startActivity(i);
			}
		});
		
		help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				wp.setAction(Intent.ACTION_VIEW);
				wp.setData(Uri.parse("https://wa.me/919668551168?text=Please help me !"));
				startActivity(wp);
			}
		});
		
		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Util.showMessage(getContext(), "logout");
			}
		});
	}
	
	private void initializeLogic() {
		_font_n(textview1);
		_font_n(textview2);
		_font_n(textview3);
		_font_n(textview4);
		_font_n(textview5);
		_font_n(textview6);
		_font_n(textview7);
		_font_n(notification_count);
		notification_count.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFF44336));
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _font_n (final TextView _textview) {
		_textview.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/google_sans_medium.ttf"), 0);
	}
	
	
	
}
