package com.example.jetpackexamplestore.store

interface Observable {

    val observers: ArrayList<Observer>

    fun addObserver(observer: Observer) {
        // TODO: Memory leaks?
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun sendUpdateEvent() {
        observers.forEach { it.update() }
    }

}