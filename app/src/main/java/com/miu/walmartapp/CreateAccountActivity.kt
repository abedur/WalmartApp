package com.miu.walmartapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miu.walmartapp.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    lateinit var createAccountBinding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAccountBinding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(createAccountBinding.root)

        createAccountBinding.createButton.setOnClickListener {

            var msg:String = ""

            if(createAccountBinding.editTextFName.text.toString().isNullOrEmpty()){
                if(msg!="") msg +=  ", First Name"
                else msg += "First Name"
            }
            if(createAccountBinding.editTextLName.text.toString().isNullOrEmpty()){
                if(msg!="") msg += ", Last Name"
                else msg += "Last Name"
            }
            if(createAccountBinding.editTextEmail.text.toString().isNullOrEmpty()){
                if(msg!="") msg += ", Email address"
                else  msg += "Email address"
            }
            if(createAccountBinding.editTextPassword .text.toString().isNullOrEmpty()){
                if(msg!="") msg += ", Password"
                else msg += "Password"
            }
            if(msg!=""){
                Toast.makeText(this,"The following fields are required:\n"+msg,Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var userAccount:UserAccount = UserAccount(
                0,
                createAccountBinding.editTextFName.text?.toString(),
                createAccountBinding.editTextLName.text?.toString(),
                createAccountBinding.editTextEmail.text?.toString(),
                createAccountBinding.editTextPassword.text?.toString())

            var rintent = intent
            rintent.putExtra("user", userAccount)
            setResult(Activity.RESULT_OK, rintent)
            finish()
        }


    }
}