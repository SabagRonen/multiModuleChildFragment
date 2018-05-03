package com.example.ronensabag.multimodulechildfragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
  @ContributesAndroidInjector(modules = [HostModule::class])
  internal abstract fun bindMainActivity() : MainActivity
}