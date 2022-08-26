package com.jsh.kr.alltestkt

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.jsh.kr.alltestkt.constant.AllTestDirectory

class AllTestApplication : Application() {

    companion object {
        var application: AllTestApplication? = null
        var isLoginSuccess = false
        private val lifecycleCallbacks: Application.ActivityLifecycleCallbacks? = null
    }

    override fun onCreate() {
        super.onCreate()
        appConfig()
        registerActivityLifecycleCallbacks(ActivityLifecycleHandler())
    }

    private fun appConfig() {
        application = this
        AllTestDirectory.init(this)
    }

    // App 종료 시 Tizen foreBindServiceClose 종료를 위해 추가(foreground foreBindServiceClose 사용 시 보안 상 notification 표시로 인하여 추가됨)
    inner class ActivityLifecycleHandler : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {

        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {

        }

    }
}