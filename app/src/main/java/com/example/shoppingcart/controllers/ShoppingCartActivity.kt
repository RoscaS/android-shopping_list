package com.example.shoppingcart.controllers

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart.R
import com.example.shoppingcart.models.ShoppingCart
import com.example.shoppingcart.models.ShoppingCartAdapter
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {

  lateinit var adapter: ShoppingCartAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_shopping_cart)
    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    // val upArrow = ContextCompat.getDrawable(this, R.drawable.)

    adapter = ShoppingCartAdapter(this, ShoppingCart.getCart())
    adapter.notifyDataSetChanged()

    shopping_cart_recyclerView.adapter = adapter

    shopping_cart_recyclerView.layoutManager = LinearLayoutManager(this)

    val totalPrice = ShoppingCart.getCart().fold(0.toDouble()) { acc, cartItem ->
      acc + cartItem.quantity.times(cartItem.product.price!!.toDouble())
    }

    total_price.text = "${totalPrice} chf"
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> { onBackPressed() }
    }
    return super.onOptionsItemSelected(item)
  }
}