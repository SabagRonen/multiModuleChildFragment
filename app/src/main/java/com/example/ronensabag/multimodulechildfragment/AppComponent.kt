package com.example.ronensabag.multimodulechildfragment

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
  MainModule::class, Main2Module::class])
interface AppComponent {
  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent

  }

  fun inject(app: MyApp)
}