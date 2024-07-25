package com.eclatsol.mynotemvvmmodernkotlincode

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.eclatsol.mynotemvvmmodernkotlincode.databinding.ActivityDataInsertBinding

class DataInsertActivity : AppCompatActivity() {

    lateinit var binding: ActivityDataInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startActivity(Intent(this@DataInsertActivity, MainActivity::class.java))
            }
        })

        intent?.run {
            val type = getStringExtra("type")
            if (type.equals("update")) {

//            setTitle("update");

                binding.add.text = "Note Update"
                binding.tittle.setText(intent.getStringExtra("title"))
                binding.discription.setText(intent.getStringExtra("disc"))
                binding.add.setOnClickListener {
                    var intentActivity = Intent(this@DataInsertActivity, MainActivity::class.java)
                    intentActivity.putExtra("title", binding.tittle.text.toString().trim())
                    intentActivity.putExtra("disc", binding.discription.text.toString().trim())
                    intentActivity.putExtra("id", intent.getStringExtra("id"))
                    intentActivity.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    setResult(RESULT_OK,intentActivity)
                    finish()
                    Toast.makeText(this@DataInsertActivity, "update", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.add.setOnClickListener {
                    var intentActivity = Intent(this@DataInsertActivity, MainActivity::class.java)
                    intentActivity.putExtra("title", binding.tittle.text.toString().trim())
                    intentActivity.putExtra("disc", binding.discription.text.toString().trim())
                    intentActivity.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    setResult(RESULT_OK,intentActivity)
                    finish()
                    Toast.makeText(this@DataInsertActivity, "insert", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }


}
