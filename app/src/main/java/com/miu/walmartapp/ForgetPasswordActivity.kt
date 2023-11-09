package com.miu.walmartapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miu.walmartapp.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)

        forgetPasswordBinding.sendButton.setOnClickListener {

            var userEmail: String = forgetPasswordBinding.editTextEmail.text.toString().trim();
            val userList: ArrayList<UserAccount> = intent.getSerializableExtra("users") as ArrayList<UserAccount>

            var userPassword = findPasswordByEmail(userEmail, userList)
            if(userPassword != null){
                sendPasswordToEmail(userEmail, userPassword)
            }else{
                Toast.makeText(this, "Email not found.", Toast.LENGTH_LONG).show()
            }

        }

    }
    fun findPasswordByEmail(email: String, users:ArrayList<UserAccount>): String? {
        val user =  users.find { it.userEmail == email }
        return user?.password
    }
    private fun sendPasswordToEmail(email: String, password: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your Forgotten Password")
        intent.putExtra(Intent.EXTRA_TEXT, "Your password is: $password")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }
}