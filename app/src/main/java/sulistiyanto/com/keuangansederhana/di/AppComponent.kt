package sulistiyanto.com.keuangansederhana.di

import dagger.Component
import sulistiyanto.com.keuangansederhana.di.subcomponent.ActivityComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {

    fun activityComponent(): ActivityComponent.Builder
}