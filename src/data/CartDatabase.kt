package com.kevin.data

import com.kevin.data.collections.Cart
import com.kevin.data.collections.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("CartDataBase")
private val users = database.getCollection<User>()
private val carts = database.getCollection<Cart>()

suspend fun registerUser(user: User):Boolean{
        return users.insertOne(user).wasAcknowledged()
}

suspend fun checkIfUserExists(email:String):Boolean{
    return users.findOne(User::email eq email) !=null
}

suspend fun checkPasswordForEmail(email: String,passwordToCheck: String):Boolean{
    val actualPassword = users.findOne(User::email eq email)?.password ?: return false
    return actualPassword == passwordToCheck
}

suspend fun saveCart(cart: Cart):Boolean{
    val cartExists = carts.findOne(cart.id) != null
    return if (cartExists){
        carts.updateOneById(cart.id, cart).wasAcknowledged()
} else{
        carts.insertOne(cart).wasAcknowledged()
 }}


suspend fun deleteCartFromUser(cartId:String):Boolean{
    return carts.deleteOneById(cartId).wasAcknowledged()
}





















