package com.example.jetpackexamplestore.cache

import android.util.Log
import com.example.jetpackexamplestore.BuildConfig
import com.example.jetpackexamplestore.app.Cache

class RamCacheImpl: Cache {

    private var maxMemory: Long? = null
    private val cache = mutableListOf<Pair<String, Any>>()
//    private val

    override fun get(key: String): Any? {
        val pair = cache.find { it.first == key }
        return pair?.second
    }

    override fun save(key: String, obj: Any) {
        // get free memory amount at this moment
        val freeMemory = Runtime.getRuntime().freeMemory()
        // save free memory amount at first call
        if (maxMemory == null) {
            maxMemory = freeMemory
        }

        cache.add(Pair(key, obj))
        if (BuildConfig.DEBUG) {
            Log.d("RamCache", "freeMemory: $freeMemory, maxMemory: $maxMemory")
        }
        // TODO: do it async???
        while(freeMemory < maxMemory!! / 2 && cache.isNotEmpty()) {
            deleteOldest()
        }
    }

    private fun deleteOldest() {
        cache.removeFirstOrNull()
    }

}