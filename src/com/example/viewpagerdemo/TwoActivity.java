package com.example.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class TwoActivity extends Activity implements OnPageChangeListener{
	/**
	 * ViewPager
	 */
	private ViewPager viewPager;
	
	private ImageView[] tips;
	private ImageView[] mImageViews;
	private int[] imgIdArray ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
//				R.drawable.item05,R.drawable.item06, R.drawable.item07, R.drawable.item08};
		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02};
		
		
		tips = new ImageView[imgIdArray.length];
		for(int i=0; i<tips.length; i++){
			ImageView imageView = new ImageView(this);
	    	imageView.setLayoutParams(new LayoutParams(10,10));
	    	tips[i] = imageView;
	    	if(i == 0){
	    		tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
	    	}else{
	    		tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
	    	}
	    	
	    	 group.addView(imageView);
		}
		
		
//		mImageViews = new ImageView[imgIdArray.length];
//		for(int i=0; i<mImageViews.length; i++){
//			ImageView imageView = new ImageView(this);
//			mImageViews[i] = imageView;
//			imageView.setBackgroundResource(imgIdArray[i]);
//		}
		
		if (imgIdArray.length == 1) {  
            mImageViews = new ImageView[2];  
            for (int i = 0; i < (mImageViews.length); i++) {  
                ImageView imageView = new ImageView(this);  
                mImageViews[i] = imageView;  
                imageView.setBackgroundResource(imgIdArray[0]);    
            }  
            group.setVisibility(View.GONE);  
            viewPager.setOnTouchListener(new View.OnTouchListener() {  
                  
                @Override  
                public boolean onTouch(View arg0, MotionEvent arg1) {  
                    // TODO Auto-generated method stub  
                     return true;   
                }  
            });  
        }else if (imgIdArray.length == 2 || imgIdArray.length == 3) {  
            mImageViews = new ImageView[imgIdArray.length * 2];  
            for (int i = 0; i < (mImageViews.length); i++) {  
                ImageView imageView = new ImageView(this);  
                mImageViews[i] = imageView;  
                imageView.setBackgroundResource(imgIdArray[(i > (imgIdArray.length-1)) ? i -imgIdArray.length  : i]);    
            }  
        } else {  
            mImageViews = new ImageView[imgIdArray.length];  
            for (int i = 0; i < mImageViews.length; i++) {  
                ImageView imageView = new ImageView(this);  
                mImageViews[i] = imageView;  
                imageView.setBackgroundResource(imgIdArray[i]);    
            }  
        }     
		
		viewPager.setAdapter(new MyAdapter());
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem((mImageViews.length) * 100);
		
	}
	
	/**
	 * 
	 * @author xiaanming
	 *
	 */
	public class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			Log.e("", "______________________________:"+mImageViews[position % mImageViews.length]);
			((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
			
		}

		@Override
		public Object instantiateItem(View container, int position) {
			Log.e("", "__________sdfdsf____________________:"+position % mImageViews.length);
			((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
			return mImageViews[position % mImageViews.length];
		}
		
		
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		setImageBackground(arg0 % mImageViews.length);
	}
	
	private void setImageBackground(int selectItems){
		for(int i=0; i<tips.length; i++){
			if(i == selectItems){
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			}else{
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

}
