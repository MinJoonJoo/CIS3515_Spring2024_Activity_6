package edu.temple.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSizes = Array(20){(it + 1) * 5}

        Log.d("Array values", textSizes.contentToString())

        with(findViewById<RecyclerView>(R.id.textSizeSelectorRecyclerView)) {
            // Initialize the adapter with textSizes array and a lambda function as the callback
            adapter = TextSizeAdapter(textSizes) { selectedTextSize ->
                // Intent to start DetailActivity
                val intent = Intent(this@MainActivity, DisplayActivity::class.java).apply {
                    // Put the selected text size as an extra
                    putExtra("SELECTED_TEXT_SIZE", selectedTextSize)
                }
                // Start DetailActivity with the intent
                this@MainActivity.startActivity(intent)
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }




    }
}


/* Convert to RecyclerView.Adapter */
class TextSizeAdapter (private val textSizes: Array<Int>, private val callback: (Int)->Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {


    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        init {
            textView.setOnClickListener {
                // Get the adapter position of the clicked item
                val position = adapterPosition
                // Check if the position is valid
                if (position != RecyclerView.NO_POSITION) {
                    // Fetch the text size using the position
                    val textSize = textSizes[position]
                    // Call the callback function with the selected text size
                    callback(textSize)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply {
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()
        }
    }

    override fun getItemCount(): Int {
        return textSizes.size
    }

}








