package com.example.jetpackexamplestore.cache

import com.example.jetpackexamplestore.store.Cache

// Fake Cache implementation just for test purposes
class FakeCache: Cache {

    override fun get(key: String): Any? {
        return null
    }

    override fun save(key: String, obj: Any) {}

}