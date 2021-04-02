package fr.train.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.train.domain.Customer
import java.lang.reflect.Type

val customersType: Type = object : TypeToken<List<Customer>>() {}.type

/**
 * Extension function for a [GsonBuilder] that appends an optional adapter (Serializer or Deserializer) and a set of extensions before
 * building the [Gson] object.
 */
fun GsonBuilder.withExtensions(typeAdapter: Any?, vararg extensions: GsonBuilder.() -> GsonBuilder): Gson = this
    .registerTypeAdapter(customersType, typeAdapter)
    .apply { extensions.forEach { this.it() } }
    .create()