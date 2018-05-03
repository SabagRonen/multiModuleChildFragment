package com.example.ronensabag.multimodulechildfragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class HostPresenter(private val viewModel: HostViewModel) : IHostViewModel by viewModel  {

  fun buttonClicked() {
    if (viewModel.toggle) {
      viewModel.updateShowFragmentEvent(ShowChildFragmentState)
    } else {
      viewModel.updateShowFragmentEvent(ShowParentFragmentState)
    }
    viewModel.toggle = !viewModel.toggle
  }

}

sealed class ShowFragmentState
object ShowParentFragmentState : ShowFragmentState()
object ShowChildFragmentState : ShowFragmentState()

interface IHostViewModel {
  fun observeShowFragmentEvents(lifecycle: Lifecycle, observer: (ShowFragmentState) -> Unit)
}

class HostViewModel : ViewModel(), IHostViewModel {
  private val fragmentStateLiveData = MutableLiveData<ShowFragmentState>()
  var toggle = false

  fun updateShowFragmentEvent(showFragmentState: ShowFragmentState) {
    fragmentStateLiveData.value = showFragmentState
  }

  override fun observeShowFragmentEvents(lifecycle: Lifecycle, observer: (ShowFragmentState) -> Unit) {
    fragmentStateLiveData.observe({lifecycle}){
      it?.let(observer)
    }
  }
}