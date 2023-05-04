package com.demo.med.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.med.R
import com.demo.med.database.entites.HealthData
import kotlinx.android.synthetic.main.list_item_problems.view.tvMedicationDose
import kotlinx.android.synthetic.main.list_item_problems.view.tvMedicationName
import kotlinx.android.synthetic.main.list_item_problems.view.tvProblemName
import kotlinx.android.synthetic.main.list_item_problems.view.tvStrength

internal class ProblemsAdapter :
    RecyclerView.Adapter<ProblemsAdapter.BookingsViewHolder>() {

    private var list = mutableListOf<HealthData>()

    private var detailsFunction: ((post: HealthData, pos: Int) -> Unit)? = null

    fun onClickListener(detailsFunction: (HealthData, Int) -> Unit) {
        this.detailsFunction = detailsFunction
    }

    fun updateList(list: MutableList<HealthData>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_problems, parent, false)
        return BookingsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holderSuggested: BookingsViewHolder, position: Int) {
        return holderSuggested.bind(list[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class BookingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: HealthData, position: Int) {
            with(itemView) {

                tvMedicationDose.text = item.medicationDose
                tvProblemName.text = item.problemName
                tvStrength.text = item.medicationStrength
                tvMedicationName.text = item.medicationName

                itemView.setOnClickListener {
                    detailsFunction?.invoke(item, position)
                }

            }
        }
    }

}

