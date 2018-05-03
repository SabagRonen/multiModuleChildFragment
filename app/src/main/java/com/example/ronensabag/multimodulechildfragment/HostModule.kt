package com.example.ronensabag.multimodulechildfragment

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class HostModule {
  @ContributesAndroidInjector(modules = [HostFragmentModule::class, FirstParentModule::class])
  internal abstract fun bindHostFragment() : HostFragment

  @Module
  abstract class HostFragmentModule {
    @Module
    companion object {
      @JvmStatic
      @Provides
      fun provideHostPresenter(fragment: HostFragment) : HostPresenter {
        val viewModel = ViewModelProviders.of(fragment).get(HostViewModel::class.java)
        return HostPresenter(viewModel)
      }
    }
  }
}

@Module
abstract class FirstParentModule {
  @ContributesAndroidInjector(modules = [FirstChildAsChildModule::class])
  abstract fun bindFirstFragment(): FirstParentFragment
}

@Module
abstract class FirstChildAsChildModule {
  @ContributesAndroidInjector(modules = [])
  abstract fun bindFirstChildAsFragment(): FirstChildFragment
}

@Module
abstract class FirstChildIndependentModule {
  @ContributesAndroidInjector(modules = [])
  abstract fun bindFirstChildFragment(): FirstChildFragment
}