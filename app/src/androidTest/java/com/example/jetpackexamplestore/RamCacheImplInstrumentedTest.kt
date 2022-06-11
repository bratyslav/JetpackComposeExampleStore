package com.example.jetpackexamplestore

import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.jetpackexamplestore.cache.RamCacheImpl
import org.junit.Assert.*
import org.junit.Test

@OptIn(
    ExperimentalFoundationApi::class,
    androidx.compose.ui.unit.ExperimentalUnitApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    coil.annotation.ExperimentalCoilApi::class
)
class RamCacheImplInstrumentedTest {

    private val cache = RamCacheImpl()

    @Test
    fun get_ReturnNull_when_doesNotSaved() {
        assertNull(cache.get("FakeKey"))
    }

    @Test
    fun saveAndGet_workCorrectly_when_useIt() {
        val obj = "SomeObject"
        val key = "ObjKey"
        cache.save(key, obj)
        assertEquals(obj, cache.get(key))
    }

    @Test
    fun save_workCorrectly_when_memoryOverflow() {
        for (i in 0 until 25) {
            val largeObj = ByteArray(1024 * 1024 * 50) { 0 }
            cache.save("$i", largeObj)
        }
    }

}