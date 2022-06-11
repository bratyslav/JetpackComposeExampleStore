package com.example.jetpackexamplestore.cache

import com.example.jetpackexamplestore.app.Cache

// Fake Cache implementation just for test purposes
class FakeCacheImpl: Cache {

    override fun get(key: String): Any? {
        return null
    }

    override fun save(key: String, obj: Any) {}

}