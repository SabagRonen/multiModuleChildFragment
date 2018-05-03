package com.example.ronensabag.multimodulechildfragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Main2Module {
  @ContributesAndroidInjector(modules = [FirstChildAsChildModule::class])
  abstract fun bindMain2Activity() : Main2Activity
}