package com.gen;

import android.gen.Drawables;
import android.gen.Layout;
import android.gen.Strings;
import android.gen.Layout.WidgetWrapper;
import com.google.gwt.user.client.ui.*;

import com.example.android.lunarlander.LunarView;
import android.content.Context;
import android.widget.TextView;
import com.example.android.lunarlander.R;



public class CoreInitilizer {
	public static void init() {
		initStrings();
		initDrawables();
	}
	
	public static void initLayout() {
/*-------------------------lunar_layout------------------------------- */
		AbsolutePanel c_m_id_lunar_layout_1 = new AbsolutePanel();
		c_m_id_lunar_layout_1.setSize("100%","100%");
		WidgetWrapper m_id_lunar_layout_1 = new WidgetWrapper(-1,c_m_id_lunar_layout_1);
		Layout.addLayout(R.layout.lunar_layout,m_id_lunar_layout_1);
		LunarView c_m_id_lunar_layout_2 = new LunarView(Context.instance());
		WidgetWrapper m_id_lunar_layout_2 = new WidgetWrapper(R.id.lunar,c_m_id_lunar_layout_2);
		AbsolutePanel c_m_id_lunar_layout_3 = new AbsolutePanel();
		c_m_id_lunar_layout_3.setSize("100%","100%");
		WidgetWrapper m_id_lunar_layout_3 = new WidgetWrapper(-1,c_m_id_lunar_layout_3);
		TextView c_m_id_lunar_layout_4 = new TextView();
		WidgetWrapper m_id_lunar_layout_4 = new WidgetWrapper(R.id.text,c_m_id_lunar_layout_4);
		m_id_lunar_layout_3.add(m_id_lunar_layout_4);
		c_m_id_lunar_layout_3.add(c_m_id_lunar_layout_4);
		m_id_lunar_layout_1.add(m_id_lunar_layout_2);
		c_m_id_lunar_layout_1.add(c_m_id_lunar_layout_2);
		m_id_lunar_layout_1.add(m_id_lunar_layout_3);
		c_m_id_lunar_layout_1.add(c_m_id_lunar_layout_3);

	}
	
	private static void initDrawables() {
		Image id_1 = new Image("lander_plain.png");
		Drawables.addDrawable(R.drawable.lander_plain,id_1);
		Image id_2 = new Image("lander_firing.png");
		Drawables.addDrawable(R.drawable.lander_firing,id_2);
		Image id_3 = new Image("app_lunar_lander.png");
		Drawables.addDrawable(R.drawable.app_lunar_lander,id_3);
		Image id_4 = new Image("lander_crashed.png");
		Drawables.addDrawable(R.drawable.lander_crashed,id_4);
		Image id_5 = new Image("earthrise.png");
		Drawables.addDrawable(R.drawable.earthrise,id_5);
		Image id_6 = new Image("earthrise.png");
		Drawables.addDrawable(R.drawable.earthrise,id_6);

	}
	
	private static void initStrings() {
		Strings.addString(R.string.app_name,"Lunar Lander");
		Strings.addString(R.string.menu_start,"Start");
		Strings.addString(R.string.menu_stop,"Stop");
		Strings.addString(R.string.menu_pause,"Pause");
		Strings.addString(R.string.menu_resume,"Resume");
		Strings.addString(R.string.menu_easy,"Easy");
		Strings.addString(R.string.menu_medium,"Medium");
		Strings.addString(R.string.menu_hard,"Hard");
		Strings.addString(R.string.mode_ready,"Lunar LandernPress Up To Play");
		Strings.addString(R.string.mode_pause,"PausednPress Up To Resume");
		Strings.addString(R.string.mode_lose,"Game OvernPress Up To Play");
		Strings.addString(R.string.mode_win_prefix,"Success!n");
		Strings.addString(R.string.mode_win_suffix,"in a rownPress Up to Play");
		Strings.addString(R.string.message_stopped,"Stopped");
		Strings.addString(R.string.message_off_pad,"Off Landing Pad");
		Strings.addString(R.string.message_too_fast,"Too Fast");
		Strings.addString(R.string.message_bad_angle,"Bad Angle");

	}
	
}
