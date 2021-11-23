package photos.picsum.mlbdproject

import android.app.Activity
import androidx.test.espresso.intent.Intents
import androidx.test.rule.ActivityTestRule



@Deprecated(
    """Use {@link androidx.test.espresso.intent.Intents.init()}, in conjunction with {@link
 *     androidx.test.core.app.ActivityScenario} or {@link
 *     androidx.test.ext.junit.rules.ActivityScenarioRule} instead."""
)
class IntentsTestRule<T : Activity?> : ActivityTestRule<T> {
    private var isInitialized = false

    constructor(activityClass: Class<T>?) : super(activityClass) {}
    constructor(activityClass: Class<T>?, initialTouchMode: Boolean) : super(
        activityClass,
        initialTouchMode
    ) {
    }

    constructor(
        activityClass: Class<T>?,
        initialTouchMode: Boolean,
        launchActivity: Boolean
    ) : super(activityClass, initialTouchMode, launchActivity) {
    }

     override fun afterActivityLaunched() {
        Intents.init()
        isInitialized = true
        super.afterActivityLaunched()
    }

     override fun afterActivityFinished() {
        super.afterActivityFinished()
        if (isInitialized) {
            // Otherwise will throw a NPE if Intents.init() wasn't called.
            Intents.release()
            isInitialized = false
        }
    }
}