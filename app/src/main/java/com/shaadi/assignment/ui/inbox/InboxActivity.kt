package com.shaadi.assignment.ui.inbox

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import com.shaadi.assignment.R
import com.shaadi.assignment.data.local.db.typeconverters.InvitationStatus
import com.shaadi.assignment.databinding.ActivityInboxBinding
import com.shaadi.assignment.di.component.ActivityComponent
import com.shaadi.assignment.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_inbox.*

class InboxActivity : BaseActivity<ActivityInboxBinding, InboxViewModel>() {

    lateinit var inboxAdapter: InboxAdapter

    override fun provideLayoutId(): Int = R.layout.activity_inbox

    override fun setupView(savedInstanceState: Bundle?) {
        inboxAdapter = InboxAdapter(onInvitationClick)

        rv_inbox_list.apply {
            itemAnimator = null
            adapter = inboxAdapter
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.inboxUser.observe(this, {
            inboxAdapter.submitList(it)
        })

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setViewModel(binding: ActivityInboxBinding) {
        binding.viewModel = viewModel
    }

    private val onInvitationClick: (Long, InvitationStatus) -> Unit =
        { id: Long, invitationStatus: InvitationStatus ->
            viewModel.updateInvitationStatus(invitationStatus, id)
        }


}