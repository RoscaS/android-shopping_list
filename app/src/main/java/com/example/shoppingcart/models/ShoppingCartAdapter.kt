package com.example.shoppingcart.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.product_row_item.view.product_image
import kotlinx.android.synthetic.main.product_row_item.view.product_name
import kotlinx.android.synthetic.main.product_row_item.view.product_price

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
        RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

  /*------------------------------------------------------------------*\
  |*							                HOOKS
  \*------------------------------------------------------------------*/
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layout =
      LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)
    return ViewHolder(layout)
  }

  override fun getItemCount(): Int = cartItems.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(cartItems[position])
  }

  /*------------------------------------------------------------------*\
  |*							                INNER CLASSES
  \*------------------------------------------------------------------*/

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(cartItem: CartItem) {
      Picasso.get()
              .load(cartItem.product.photos[0].filename)
              .fit()
              .into(itemView.product_image)
      itemView.product_name.text = cartItem.product.name
      itemView.product_price.text = "${cartItem.product.price}"
      itemView.product_quantity.text = cartItem.quantity.toString()
    }
  }
}