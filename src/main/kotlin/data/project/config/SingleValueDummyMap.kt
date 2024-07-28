package data.project.config

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer


data class SingleValueDummyMap(val dummies: SnapshotStateList<SingleValueDummy> = mutableStateListOf(SingleValueDummy())) {

    fun get(value: Int): String {
        for (dummy in dummies) {
            if (dummy.key.value == value) {
                return dummy.value.value
            }
        }
        return ""
    }

    fun add() {
        dummies.add(SingleValueDummy())
    }

    fun remove(dummy: SingleValueDummy) {
        dummies.remove(dummy)
    }

    companion object {
        val serializer = JsonSerializer<SingleValueDummyMap> { value, _, ctx ->
            val obj = JsonObject()
            obj.add("dummies", ctx.serialize(value.dummies))
            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject
            val dummies = mutableStateListOf<SingleValueDummy>()
            for (dummy in obj.get("dummies").asJsonArray) {
                val dummyEntry = ctx.deserialize<SingleValueDummy>(dummy, SingleValueDummy::class.java)
                dummies.add(dummyEntry)
            }
            SingleValueDummyMap(dummies)
        }
    }
}