package foo

import com.google.gson.GsonBuilder

actual fun serialize(data: MutableMap<String, MutableMap<String, MutableList<String>>>): String {
    return GsonBuilder().setPrettyPrinting().create().toJson(data)
}