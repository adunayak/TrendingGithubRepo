package co.adarsh.domain

import co.adarsh.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

/**
 * An abstract use case class that allows to build and execute the observable.
 * Built use case return completable type
 *
 * @param PostExecutionThread : interface which is implemented to provide UI thread
 */
abstract class CompletableUseCase<in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    /**
     * Sub classes override this method to create the completable
     *
     * @param Params : input parameter to process the use case
     *
     * @return Completable
     */
    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    /**
     * Build and observe the completable and add to CompositeDisposable
     *
     * @param DisposableObserver<T> : [Observer]
     * @param Params : Input to use case building
     */
    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(observer))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    /**
     * Remember to dispose the the disposable when use case finishes the task or task has to terminated
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}