package com.example.jetpackexamplestore.store

interface Cache { // TODO: rename to CacheRepository?

    // return an object by key or null, if there is no matching key
    fun get(key: String): Any?

    // save new or overwrite existing object by key
    fun save(key: String, obj: Any)

}