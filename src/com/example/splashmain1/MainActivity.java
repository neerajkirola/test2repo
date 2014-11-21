package com.example.splashmain1;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv, tv2;
	Animation animRightOut, animLeftIn;
	OutAnimThread out;
	TextView tv_temp;
	ArrayList<String> list;
	int i = 0;
	boolean flag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);

		tv2 = (TextView) findViewById(R.id.textView2);

		list = new ArrayList<String>();
		list.add("jitinfasdffsfsfsdfsfsfsfasdsdfsffsdfssfsff");
		list.add("nitinfasdffsfsfsdfsfsfssdfsdfsfsfsfsdfasdf");
		list.add("karanfasdffsfsfsdgdfgdgsdsdgdgfsfsfsfasdf");
		list.add("neerajfasdffsfsfsdfsfsdfsdfsfsfsdfsdfsdfsfsfasdf");
		animRightOut = AnimationUtils.loadAnimation(this, R.anim.anim_right_out);

		animLeftIn = AnimationUtils.loadAnimation(this, R.anim.anim_left_in);
		
		animRightOut.setAnimationListener(an);
		
		animLeftIn.setAnimationListener(an2);
		tv.setText(list.get(i));
		out = new OutAnimThread();
		out.start();

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			tv.startAnimation(animRightOut);
		}
	};

	Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			tv2.startAnimation(animRightOut);
		}
	};

	AnimationListener an = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			if (flag) {
				String str = tv2.getText().toString();
				tv.setText(str);
			} else {
				flag = true;
			}
			tv2.startAnimation(animLeftIn);
			InAnimThread LefInAnim = new InAnimThread();
			LefInAnim.start();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {

		}
	};
	AnimationListener an2 = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			i++;
			if (i < list.size())
				tv2.setText(list.get(i));
			else {
				i = 0;
				tv2.setText(list.get(i));
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {

		}
	};

	class OutAnimThread extends Thread {
		public void run() {
			try {
				sleep(3000);
				Message msg = new Message();
				msg.arg1 = 5;
				handler.sendMessage(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class InAnimThread extends Thread {
		public void run() {
			try {
				sleep(3000);
				Message msg = new Message();
				msg.arg2 = 5;
				handler1.sendMessage(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
