package com.example.verysimpleclickercounter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.KeyEvent
import com.example.verysimpleclickercounter.databinding.ActivityMainBinding
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs


class MainActivity :
    Activity(),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener
{
    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    private lateinit var mDetector : GestureDetectorCompat
    private val SWIPE_MIN_DISTANCE = 120
    private val SWIPE_THRESHOLD_VELOCITY = 200

    private val DEBUG_TAG = "Gestures"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDetector = GestureDetectorCompat(this, this)
        mDetector.setOnDoubleTapListener(this)
        binding.numberView.text = counter.toString()
        binding.numberView.setOnLongClickListener{resetCounter()}
        binding.numberView.setOnClickListener{countOne()}

        //setContentView(R.layout.activity_main)
        //textNumber.setText("what?")

    }

    private fun resetCounter(): Boolean {
        counter = 0
        binding.numberView.text = counter.toString()
        return true
    }

    private fun countOne(): Boolean {
        counter++
        if (counter >= 1000)
            resetCounter()
        binding.numberView.text = counter.toString()
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        return true
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        try {
            Log.d(DEBUG_TAG, "onFling: $(event1.y) $(event2.y) $velocityY")

            if ((abs(event1.y - event2.y) > SWIPE_MIN_DISTANCE) and (abs(velocityY) > SWIPE_THRESHOLD_VELOCITY))
            {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {

        }

        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onScroll: $event1 $event2")
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onShowPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTap: $event")
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: $event")
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: $event")
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if (event.repeatCount == 0) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.d("button", "$keyCode is pushed")
                    countOne()
                    return true
                } else
                    return super.onKeyDown(keyCode, event)
            } else
                return super.onKeyDown(keyCode, event)
        } else
            return super.onKeyDown(keyCode, event)
    }
}