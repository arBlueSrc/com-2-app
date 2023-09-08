package ir.bluesource.test1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ir.bluesource.test1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding

    var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.button2.setOnClickListener {

            counter += 1

            binding.textView.text = counter.toString()

        }




        binding.button.setOnClickListener {

            try {

                val launchIntent = this@MainActivity2.packageManager.getLaunchIntentForPackage("ir.bluesource.test2")
                launchIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                launchIntent?.putExtra("package", packageName)
                launchIntent?.putExtra("act", "MainActivity2")

                if (launchIntent != null){
                    this@MainActivity2.startActivity(launchIntent)
                }else{
                    Log.i("TAG", "onCreate: there is not any app")
                }

            }catch (e : Exception){
                Log.i("TAG", "onCreate: $e")
            }

        }

    }


    override fun onResume() {
        super.onResume()

        val result = intent.getStringExtra("result") ?: "none"

        if (result.equals("successful")){
            binding.title.text = "successful"
            intent.extras?.clear()
        }

    }

}