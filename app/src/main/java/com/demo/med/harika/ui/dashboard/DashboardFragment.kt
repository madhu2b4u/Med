package com.demo.med.harika.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.med.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    // Variable to store the last visible item position
    private var lastVisibleItemPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val root: View = binding.root


        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter and set it to the RecyclerView
        val dataSet = generateDummyData()
        adapter = MyAdapter(dataSet)
        recyclerView.adapter = adapter

        return root
    }

    override fun onResume() {
        super.onResume()
        // Fetching the stored data from the SharedPreference
        val sharedPref = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val lastPosition = sharedPref?.getInt("position", 0)!!
        recyclerView.layoutManager?.scrollToPosition(lastPosition)
    }


    override fun onPause() {
        super.onPause()
        // Save the last visible item position
        lastVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val sharedPref = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val myEdit = sharedPref?.edit()
        myEdit?.putInt("position", lastVisibleItemPosition)
        myEdit?.apply()
        _binding = null
    }

    private fun generateDummyData(): List<String> {
        val data = mutableListOf<String>()
        for (i in 1..100) {
            data.add("Item $i")
        }
        return data
    }

}
