package ca.ualberta.cs.lonelytwitter.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity;

/*
 * generate this class with new.. JUnit Test Case
 * set superclass to ActivityInstrumentationTestCase2
 */
public class LonelyTwitterActivityUITest extends
		ActivityInstrumentationTestCase2<LonelyTwitterActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	public LonelyTwitterActivityUITest() {
		super(LonelyTwitterActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();

		textInput = ((EditText) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.body));
	}
	
	/*
	 * fills in the input text field and clicks the 'save'
	 * button for the activity under test
	 */
	private void makeTweet(String text) throws Throwable {	
		final String text2 = text;
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				textInput.setText(text2);
				((Button) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save)).performClick();
			}
		});
		
		assertNotNull(activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save));
	}
	
	public void testAddTweet() throws Throwable{
		Intent intent = new Intent();
		makeTweet("a beautiful tweet");
		setActivityIntent(intent);
		LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
		
		
		assertNotNull(activity.getListView().getItemAtPosition(0));
		
		//assertTrue(activity.getListView().getItemAtPosition(0).type(NormalTweetModel));
		//assertEquals("a beautiful tweet", activity.getListView().getItemAtPosition(0).getText());
	}
}
