package com.demo.med.home.presentation.ui.fragments


import android.os.Bundle
import android.view.View
import com.demo.med.R
import com.demo.med.common.BaseFragment
import com.demo.med.common.HEALTH
import com.demo.med.common.extensions.noCrash
import com.demo.med.database.entites.HealthData
import kotlinx.android.synthetic.main.fragment_home_details.toolbar
import kotlinx.android.synthetic.main.list_item_problems.tvMedicationDose
import kotlinx.android.synthetic.main.list_item_problems.tvMedicationName
import kotlinx.android.synthetic.main.list_item_problems.tvProblemName
import kotlinx.android.synthetic.main.list_item_problems.tvStrength

class HomeDetailsFragment : BaseFragment() {

    override fun layoutId(): Int {
        return R.layout.fragment_home_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noCrash {
            // Set the back button listener
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            val item = arguments?.getParcelable<HealthData>(HEALTH)!!
            applyHealthDataOnViews(item)
        }
    }

    private fun applyHealthDataOnViews(item: HealthData) {
        tvMedicationDose.text = item.medicationDose
        tvProblemName.text = item.problemName
        tvStrength.text = item.medicationStrength
        tvMedicationName.text = item.medicationName
    }

}
