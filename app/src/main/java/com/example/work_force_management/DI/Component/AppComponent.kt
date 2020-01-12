package com.example.work_force_management.DI.Component

import android.app.Application
import com.example.work_force_management.DI.Module.ActivityModule
import com.example.work_force_management.DI.Module.NetworkModule
import com.example.work_force_management.WorkForceManagement
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityModule::class, NetworkModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(workForceManagement: WorkForceManagement)

}