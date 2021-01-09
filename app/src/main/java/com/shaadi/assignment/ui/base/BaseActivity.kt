package com.shaadi.assignment.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.shaadi.assignment.ShaadiApplication
import com.shaadi.assignment.di.component.ActivityComponent
import com.shaadi.assignment.di.component.DaggerActivityComponent
import com.shaadi.assignment.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        binding.lifecycleOwner = this
        setViewModel(binding)
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, {
            it.data?.run { showMessage(this) }
        })
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as ShaadiApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setViewModel(binding: B)

}