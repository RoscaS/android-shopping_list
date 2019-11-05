package com.example.shoppingcart.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.controllers.MainActivity
import com.example.shoppingcart.models.CartItem
import com.example.shoppingcart.models.Product
import com.example.shoppingcart.models.ShoppingCart
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_row_item.view.*


class ProductAdapter(
        var context: Context,
        var products: List<Product> = arrayListOf()
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

  /*------------------------------------------------------------------*\
  |*							                HOOKS
  \*------------------------------------------------------------------*/

  override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
    val view = LayoutInflater
            .from(context)
            .inflate(R.layout.product_row_item, null)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.bindProduct(products[position])
  }

  override fun getItemCount() = products.size

  /*------------------------------------------------------------------*\
  |*							            INNER CLASSES
  \*------------------------------------------------------------------*/

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("CheckResult")
    fun bindProduct(product: Product) {
      itemView.product_name.text = product.name
      itemView.product_price.text = "${product.price.toString()} chf"
      Picasso.get()
              .load(product.photos[0].filename)
              .fit()
              .into(itemView.product_image)


      val coordinator = (itemView.context as MainActivity).coordinator
      val snackbar = { text: String, name: String ->
        Snackbar.make(coordinator, "$name $text your cart", Snackbar.LENGTH_LONG).show()
      }

      Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

        itemView.addToCart.setOnClickListener { view ->
          val item = CartItem(product)
          ShoppingCart.addItem(item)
          snackbar("added to", product.name!!)
          it.onNext(ShoppingCart.getCart())
        }

        itemView.removeItem.setOnClickListener { view ->
          val item = CartItem(product)
          ShoppingCart.removeItem(item, itemView.context)
          snackbar("removed from", product.name!!)
          it.onNext(ShoppingCart.getCart())
        }

      }).subscribe { cart ->
        var quantity = 0
        cart.forEach { cartItem: CartItem -> quantity += cartItem.quantity }

        (itemView.context as MainActivity).cart_size.text = quantity.toString()
        Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
      }

    }
  }
}


