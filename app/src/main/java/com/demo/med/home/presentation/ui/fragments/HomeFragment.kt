package com.demo.med.home.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.demo.med.R
import com.demo.med.common.BaseFragment
import com.demo.med.common.HEALTH
import com.demo.med.common.PRESCRIPTION_LOADED
import com.demo.med.common.extensions.noCrash
import com.demo.med.common.support.hide
import com.demo.med.common.support.show
import com.demo.med.database.entites.HealthData
import com.demo.med.home.presentation.ui.adapter.ProblemsAdapter
import com.demo.med.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.dataLayout
import kotlinx.android.synthetic.main.fragment_home.rvDrugs
import kotlinx.android.synthetic.main.fragment_home.tvDate
import kotlinx.android.synthetic.main.fragment_home.tvTime
import kotlinx.android.synthetic.main.fragment_home.tvUserName
import kotlinx.android.synthetic.main.layout_loader.loader
import java.util.Locale

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
            val isPrescriptionLoaded = sharedPrefsHelpers?.getBoolean(PRESCRIPTION_LOADED, false)
            if (isPrescriptionLoaded != null) {
                viewModel.getProblemsList(isPrescriptionLoaded)
            }

            tvDate.text = getTodayDate()
            tvTime.text = getTime()
            tvUserName.text = getString(R.string.hello_user, userName?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })

            observeOnViewModel()
        }
    }

    private fun observeOnViewModel() = with(viewModel) {
        lifecycle.addObserver(this)

        data.observe(viewLifecycleOwner) {
            contentView(it)
        }

        healthData.observe(viewLifecycleOwner) {

        }

        showLoader.observe(viewLifecycleOwner) {
            loaderView()
        }
    }

    private fun loaderView() {
        dataLayout.hide()
        loader.show()
    }

    private fun contentView(problems: MutableList<HealthData>) {
        dataLayout.show()
        loader.hide()
        sharedPrefsHelpers?.putBoolean(PRESCRIPTION_LOADED, true)
        adapter.updateList(problems)
        rvDrugs.adapter = adapter
        adapter.onClickListener { healthData, i ->
            navigateToDetails(healthData)
        }
    }

    private fun navigateToDetails(healthData: HealthData) {
        val bundle = Bundle()
        bundle.putParcelable(HEALTH, healthData)
        arguments = bundle
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigate_details, bundle)
        }
    }

}
