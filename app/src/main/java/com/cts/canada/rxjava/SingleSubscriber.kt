package au.com.suncorp.marketplace.application.rx

import io.reactivex.Single
import io.reactivex.disposables.Disposables

/**
 * Created by Ramesh.P on 5/9/18.
 */
class SingleSubscriber<T>(private val onSuccess: (T) -> Unit,
                          private val onError: (Throwable) -> Unit) : Subscriber<Single<T>> {

    private var observable: Single<T>? = null
    private var subscription = Disposables.disposed()

    override fun dispatch(observable: Single<T>) {
        if (this.observable == null) {
            this.observable = observable.doAfterTerminate {
                dispose()
                clear()
            }
        }

        subscribe()
    }

    override fun subscribe() {
        observable?.let {
            if (subscription.isDisposed) {
                subscription = it.subscribe(onSuccess, onError)
            }
        }
    }

    override fun dispose() = subscription.dispose()

    override fun clear() {
        observable = null
    }
}