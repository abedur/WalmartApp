package com.miu.walmartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miu.walmartapp.databinding.ActivityMainBinding
import com.miu.walmartapp.databinding.ActivityShoppingCategoryActovityBinding

class ShoppingCategoryActovity : AppCompatActivity() {
    lateinit var categoryBinding: ActivityShoppingCategoryActovityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryBinding= ActivityShoppingCategoryActovityBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)

        val reacivedIntent = intent
        val rcvEmail = reacivedIntent.getStringExtra("email");

        categoryBinding.welcome.text = "Welcome $rcvEmail";

        categoryBinding.imageViewElectronics.setOnClickListener {
            Toast.makeText(this, "Electronics category chosen.",Toast.LENGTH_LONG).show()
        }
        categoryBinding.imageViewClothing.setOnClickListener {
            Toast.makeText(this, "Clothing category chosen.", Toast.LENGTH_LONG).show()
        }
        categoryBinding.imageViewBeauty.setOnClickListener {
            Toast.makeText(this, "Beauty category chosen.", Toast.LENGTH_LONG).show()
        }
        categoryBinding.imageViewFood.setOnClickListener {
            Toast.makeText(this, "Food category chosen.", Toast.LENGTH_LONG).show()
        }

    }
}