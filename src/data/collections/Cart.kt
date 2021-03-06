package com.kevin.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Cart(
    @BsonId
    val id:String = ObjectId().toString(),
    val title:String,
    val price:Float,
    val date: Long,
    val size:Int
)
