package com.example.shoppingcart.models

import android.content.Context
import io.paperdb.Paper

class ShoppingCart {

  companion object {

    fun addItem(cartItem: CartItem) {
      val cart = getCart()
      val targetItem =
        cart.singleOrNull { it.product.id == cartItem.product.id }

      if (targetItem == null) {
        cartItem.quantity++
        cart.add(cartItem)
      } else {
        targetItem.quantity++
      }

      saveCart(cart)
    }

    fun removeItem(cartItem: CartItem, context: Context) {
      val cart = getCart()
      val targetItem =
        cart.singleOrNull { it.product.id == cartItem.product.id }

      if (targetItem != null) {
        if (targetItem.quantity > 0) {
          targetItem.quantity--
        } else {
          cart.remove(targetItem)
        }
      }
      saveCart(cart)
    }

    fun saveCart(cart: MutableList<CartItem>) = Paper.book().write("cart", cart)
    fun getCart(): MutableList<CartItem> = Paper.book().read("cart", mutableListOf())
    fun getShoppingCartSize(): Int = getCart().sumBy { it.quantity }

  }

}