package sulistiyanto.com.keuangansederhana.application

import android.app.Application
import sulistiyanto.com.keuangansederhana.di.AppComponent
import sulistiyanto.com.keuangansederhana.di.AppModule
import sulistiyanto.com.keuangansederhana.di.DaggerAppComponent

class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}