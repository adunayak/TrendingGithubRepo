package co.adarsh.domain

import co.adarsh.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * An abstract use case class that allows to build and execute the observable.
 * Built use case returns Observable type
 *
 * @param PostExecutionThread : interface which is implemented to provide UI thread
 */
abstract class ObservableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    /**
     * Sub classes override this method to create the observable
     *
     * @param Params : input parameter to process the use case
     *
     * @return Observable<T>
     */
    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    /**
     * Build and observe the observable and add to CompositeDisposable
     *
     * @param DisposableObserver<T> : [Observer]
     * @param Params : Input to use case building
     */
    open fun execute(singleObserver: DisposableObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(singleObserver))
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