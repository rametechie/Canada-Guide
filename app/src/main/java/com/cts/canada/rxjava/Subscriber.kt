package au.com.suncorp.marketplace.application.rx

/**
 * An abstraction to contain subscription logic for Rx observables.
 *
 * Implementations must provide the capacity to subscribe to observables, as
 * well as the ability to dispose, resubscribe, or clear the subscription entirely.
 * Consumers decide where these events are called; typically in the screen's lifecycle.
 *
 * Created by Ramesh.P on 5/9/18.
 *
 * @param T
 *      The type of observable that this [Subscriber] can subscribe to.
 *      Implementations should use [Observable][io.reactivex.Observable],
 *      [Single][io.reactivex.Single], or [Completable][io.reactivex.Completable].
 *      Since there is no parent type to these objects, we can't guarantee that
 *      implementations have used these types at compile time.
 *
 * @see [/docs/rx-patterns.md]
 *
 */
interface Subscriber<in T> {

    /**
     * Dispatches the observable and subscribes to it.
     *
     * Invoke this when you want to initiate the asynchronous process (eg. fire an API request,
     * request a database read/write). Often called onClick of a button, or onStart of a screen.
     *
     * Handles the cleanup of the subscription once the event has terminated.
     *
     * @param observable
     *      The observable to subscribe to. Should be a [Observable][io.reactivex.Observable],
     *      [Single][io.reactivex.Single], or [Completable][io.reactivex.Completable].
     */
    fun dispatch(observable: T)

    /**
     * Resubscribes to the observable.
     *
     * The observable must be [dispatched][dispatch] before attempting to resubscribe. If there is no
     * observable dispatched, nothing happens.
     *
     * This operation is idempotent. If a subscription already exists, this will not subscribe again.
     *
     * Typically used in `onResume`.
     *
     */
    fun subscribe()

    /**
     * Disposes the subscription to the observable.
     *
     * The observable still exists, so after this operation, it is still possible to resubscribe.
     *
     * Typically used in `onPause`.
     *
     */
    fun dispose()

    /**
     * Clears the observable.
     *
     * Once cleared, there will be no reference to the observable. It is not possible to re-subscribe.
     *
     * Typically used in `onStop`.
     *
     */
    fun clear()
}