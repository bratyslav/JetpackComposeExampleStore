package com.example.jetpackexamplestore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.test.platform.app.InstrumentationRegistry
import com.example.jetpackexamplestore.cache.RamCache
import com.example.jetpackexamplestore.ui.MainActivity
import org.junit.Assert.*
import org.junit.Test

@OptIn(
    ExperimentalFoundationApi::class,
    androidx.compose.ui.unit.ExperimentalUnitApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    coil.annotation.ExperimentalCoilApi::class
)
class RamCacheInstrumentedTest {

    private val cache = RamCache()

    init {
//        MainActivity.mainContext = InstrumentationRegistry.getInstrumentation().targetContext

    }

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