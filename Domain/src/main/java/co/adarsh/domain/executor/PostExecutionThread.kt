package co.adarsh.domain.executor

import io.reactivex.Scheduler

/**
 * interface which is implemented to provide UI thread
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}
