package co.com.ceiba.mobile.pruebadeingreso.db

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Executor {
    fun iOThread(t: Runnable?) {
        val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()
        IO_EXECUTOR.execute(t)
    }
}