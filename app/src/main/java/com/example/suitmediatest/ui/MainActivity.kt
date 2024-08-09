package com.example.suitmediatest.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val text = binding.inputPalindrome.text.toString().trim()

            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
            } else {
                if (isPalindrome(text)) {
                    showDialog("isPalindrome")
                } else {
                    showDialog("not palindrome")
                }
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.inputName.text.toString().trim()

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanedText = text.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanedText == cleanedText.reversed()
    }

    private fun showDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        val alert = dialogBuilder.create()
        alert.setTitle("Palindrome Check")
        alert.show()
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}