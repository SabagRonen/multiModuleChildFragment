package com.example.ronensabag.multimodulechildfragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.fragment_host.fab
import kotlinx.android.synthetic.main.fragment_host.hostContainer
import javax.inject.Inject

class HostFragment : Fragment(), HasSupportFragmentInjector {

  @Inject lateinit var presenter: HostPresenter
  @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = fragmentInjector

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_host, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.observeShowFragmentEvents(lifecycle) {
      when (it) {
        ShowParentFragmentState -> showFirstParentFragment()
        ShowChildFragmentState -> showFirstChildFragment()
      }
    }
    fab.setOnClickListener {
      presenter.buttonClicked()
    }
  }

  private fun showFirstParentFragment() {
    childFragmentManager
        .beginTransaction()
        .add(R.id.hostContainer, FirstParentFragment(), FirstParentFragment::class.java.simpleName)
        .commit()
  }

  private fun showFirstChildFragment() {
    startActivity(Intent(context, Main2Activity::class.java))
//    childFragmentManager
//        .beginTransaction()
//        .add(R.id.hostContainer, FirstChildFragment(), FirstChildFragment::class.java.simpleName)
//        .commit()
  }
}

