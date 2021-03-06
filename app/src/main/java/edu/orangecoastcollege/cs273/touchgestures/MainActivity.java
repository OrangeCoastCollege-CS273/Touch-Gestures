package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private GestureDetector mGestureDetector;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    /**
     * Called on activity start.  Connects the views to UI
     * Instantiates the {@link GestureDetector}
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /**
     * Sends the touch event to all the children in ViewGroup:
     * e.g. ScrollView down to the TextView
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * Handles a single-tap gesture. No part of double-tap
     * @param motionEvent The motion event triggering the gesture
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapConfirmed");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /**
     * Responds to a double-tap gesture. Double-tap is the succession of two single tap gestures
     * within a duration
     * @param motionEvent The motion event triggering the gesture
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTapUp touch event");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * During a double-tap, another event occurs
     * @param motionEvent The motion event triggering the gesture
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return true;
    }

    /**
     * User made initial contact with device.
     * Every gesture begins with this method.
     * @param motionEvent The motion event triggering the gesture
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return true;
    }

    /**
     * Down event where user does not let go, short duration of time.
     * @param motionEvent The motion event triggering the gesture
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    /**
     * Similar to onSingleTapConfirmed, but it could be part of a double-tap.
     * @param motionEvent The motion event triggering the gesture
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event");
        return true;
    }

    /**
     * Down event, followed by a press and lateral movement,
     * without relinquishing contact with device.
     * @param motionEvent The event where scroll started.
     * @param motionEvent1 The event where the scroll stopped.
     * @param distanceX The distance in X direction (pixels).
     * @param distanceY The distance in Y direction (pixels).
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\nonScroll: distanceX is " + distanceX + ", distanceY is " + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * Down event, followed by a long hold.
     * @param motionEvent The motion event triggering the gesture
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonLongPress touch event");
        longPressTextView.setText(String.valueOf(++longPresses));
    }

    /**
     * Similar to scroll, with faster velocity and user releases contact with device
     * @param motionEvent The event where fling started.
     * @param motionEvent1 The event where the fling stopped.
     * @param v Initial velocity (pixels/second)
     * @param v1 Terminal velocity (pixels/second)
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nonFling: velocityX is " + v + ", velocityY is " + v1);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    /**
     * Resets all {@link TextView}s and counters
     * @param view Button used to call method
     */
    public void clearAll(View view) {
        gesturesLogTextView.setText("");
        singleTapTextView.setText(getString(R.string.zero));
        doubleTapTextView.setText(getString(R.string.zero));
        longPressTextView.setText(getString(R.string.zero));
        scrollTextView.setText(getString(R.string.zero));
        flingTextView.setText(getString(R.string.zero));

        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings  = 0;
    }
}
