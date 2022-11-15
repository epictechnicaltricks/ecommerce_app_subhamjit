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
	private TextView delivery_type;
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
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_product);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
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
	}
	
	private void initializeLogic() {
		delivery_type.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFE8EAF6));
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
		category_name.setText("Category : "+ getIntent().getStringExtra("cat_name"));
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
				Toast.makeText(ViewProductActivity.this, "add to cart clicked", Toast.LENGTH_SHORT).show();
			}
		});

		/*
description_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)3, 0xFF5C6BC0, 0xFFE8EAF6));

*/
	}

	public void _setTransitionName (final View _view, final String _transitionName) {
		_view.setTransitionName(_transitionName);
	}


	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	public void _transparent_satus () {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);}
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
