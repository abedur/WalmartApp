package com.miu.walmartapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.miu.walmartapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var userList : ArrayList<UserAccount> = arrayListOf<UserAccount>()
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList.add(UserAccount(1, "abedur", "rahman", "abedur@gmail.com", "123"))
        userList.add(UserAccount(2, "adyan", "rahman", "adyan.eit@gmail.com", "123"))
        userList.add(UserAccount(3, "abedur", "panna", "panna@gmail.com", "123"))
        userList.add(UserAccount(4, "Abedur", "Rahman", "mdrahman@miu.edu", "123"))
        userList.add(UserAccount(5, "admin", "admin", "admin@admin.com", "admin"))

        binding.buttonLogin.setOnClickListener {
            val tmpEmail : String = binding.email.text.toString().trim()
            val tmpPass : String = binding.password.text.toString().trim()

            if(tmpEmail.isNullOrEmpty()){
                binding.email.error = "Please enter a valid email"
            }
            if(tmpPass.isNullOrEmpty()){
                binding.password.error = "Please enter a valid password"
            }
            if(!verifyUser(tmpEmail, tmpPass)){
                Toast.makeText(this, "User not found.", Toast.LENGTH_LONG).show()
            }else{
                binding.email.text.clear()
                binding.password.text.clear()

                var intent = Intent(this, ShoppingCategoryActovity::class.java )
                intent.putExtra("email", tmpEmail)
                startActivity(intent)
            }
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
                    if(result.resultCode == Activity.RESULT_OK){
                        val tempUser = result.data?.getSerializableExtra("user")
                        var userAccount = tempUser as UserAccount
                        if(userAccount != null){
                            userList.add(userAccount)
                            Toast.makeText(this, "User created successfully", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this, "User creation faild", Toast.LENGTH_LONG).show()
                        }
                    }
        }
        binding.buttonCreate.setOnClickListener {
            var  intent = Intent(this, CreateAccountActivity::class.java)
            //startActivity(intent)
            resultContracts.launch(intent)
        }

        binding.textViewForget.setOnClickListener {
            var intent = Intent(this, ForgetPasswordActivity::class.java)
            intent.putExtra("users",userList)
            startActivity(intent)
        }

    }
    fun verifyUser(email: String, password:String):Boolean{
        return userList.contains(UserAccount(0,"","",email, password))
    }
}