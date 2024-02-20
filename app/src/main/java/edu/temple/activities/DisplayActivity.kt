package edu.temple.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Retrieve the transferred text size value from the intent
        val selectedTextSize = intent.getIntExtra("SELECTED_TEXT_SIZE", 12)

        // Use the retrieved value to set the text size of lyricsDisplayTextView
        with(findViewById<TextView>(R.id.lyricsDisplayTextView)) {
            textSize = selectedTextSize.toFloat()
        }
    }
}
