/* -*- Mode: java; tab-width: 8; indent-tabs-mode: t; c-basic-offset: 8 -*- */
//
// MoonPhase.java:
// Calculate the phase of the moon.
//    Copyright 2014 by Audrius Meskauskas
// You may use or distribute this code under the terms of the GPLv3
//
package akk.astro.droid.moonphase;

import android.app.Activity;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import java.util.Date;
import java.util.Calendar;
import android.widget.TextView;

public class MoonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);

		// Pass the date text to the moon view,
		// so the MoonView can keep the date updated.
		MoonView moonview = (MoonView)findViewById(R.id.moonView);
		TextView dateText = (TextView)findViewById(R.id.dateText);
		moonview.setDateTextWidget(dateText);
	}

	// Main menu, which allows changing the date:
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.choosedate:
			setDate();
			return true;
		}
		return false;
	}

	// Pop up a date picker dialog:
	public void setDate() {
		final OnDateSetListener listener = new OnDateSetListener() {
			@Override public void onDateSet(DatePicker view,
							int year, int month,
							int day) {
				MoonView moonview = (MoonView)findViewById(R.id.moonView);
				moonview.setDate(year, month, day);
			}
		};

		MoonView moonview = (MoonView)findViewById(R.id.moonView);
		Date curdate = moonview.getDate();

		// Date's getYear(), getMonth() and getDay() are not only
		// deprecated, they give random and bogus values. Sigh.
		Calendar cal = Calendar.getInstance();
		cal.setTime(curdate);

		new DatePickerDialog(this, listener,
				     cal.get(Calendar.YEAR),
				     cal.get(Calendar.MONTH),
				     cal.get(Calendar.DAY_OF_MONTH)).show();
	}
}
