package com.codebase.interfaces

interface AppListener {
    fun onItemClick(position: Int, type: String)


    interface ResultListener {
        fun onResult(result: Any)
    }

    interface DataListener {
        fun onResult(data1: Any, data2: Any, data3: Float)
    }
}