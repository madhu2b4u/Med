package com.demo.med.home.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.demo.med.R
import com.demo.med.common.BaseFragment
import com.demo.med.common.extensions.noCrash
import com.demo.med.common.support.hide
import com.demo.med.common.support.show
import com.demo.med.database.entites.HealthData
import com.demo.med.home.presentation.ui.adapter.ProblemsAdapter
import com.demo.med.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.rvDrugs
import kotlinx.android.synthetic.main.layout_loader.loader

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    private val adapter = ProblemsAdapter()

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noCrash {
            observeOnViewModel()
        }
    }

    private fun observeOnViewModel() = with(viewModel) {
        lifecycle.addObserver(this)

        data.observe(viewLifecycleOwner) {
            contentView(it)
        }

        showLoader.observe(viewLifecycleOwner) {
            loaderView()
        }

    }

    private fun loaderView() {
        rvDrugs.hide()
        loader.show()
    }

    private fun contentView(problems: MutableList<HealthData>) {
        rvDrugs.show()
        loader.hide()
        adapter.updateList(problems)
        rvDrugs.adapter = adapter
        adapter.onClickListener { healthData, i ->

        }
    }

}
