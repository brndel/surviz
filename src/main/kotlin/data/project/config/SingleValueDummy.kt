package data.project.config

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

data class SingleValueDummy(val key: MutableState<Int>, val value: MutableState<String>) {

    constructor() : this(mutableStateOf(0), mutableStateOf(""))

    companion object {
        val serializer = JsonSerializer<SingleValueDummy> { value, _, _ ->
           val obj = JsonObject()

            obj.addProperty("key", value.key.value)
            obj.addProperty("value", value.value.value)

            obj
       }

        val deserializer = JsonDeserializer { json, _, _ ->
            val obj = json.asJsonObject

            SingleValueDummy(
                mutableStateOf(obj.get("key").asInt),
                mutableStateOf(obj.get("value").asString)
            )
        }
    }
}