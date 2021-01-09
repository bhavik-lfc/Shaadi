package com.shaadi.assignment.ui.inbox

import android.os.Bundle
import com.shaadi.assignment.R
import com.shaadi.assignment.databinding.ActivityInboxBinding
import com.shaadi.assignment.di.component.ActivityComponent
import com.shaadi.assignment.ui.base.BaseActivity

class InboxActivity : BaseActivity<ActivityInboxBinding, InboxViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_inbox

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setViewModel(binding: ActivityInboxBinding) {
        binding.viewModel = viewModel
    }

}