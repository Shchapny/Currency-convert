package com.osinit.internship.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.osinit.internship.R
import com.osinit.internship.adapter.CurrenciesAdapter
import com.osinit.internship.data.CurrencyInfo
import com.osinit.internship.databinding.FragmentCurrenciesBinding
import com.osinit.internship.util.selectDate
import com.osinit.internship.viewmodel.CurrenciesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {

    private var _binding: FragmentCurrenciesBinding? = null
    private val binding get() = _binding!!
    private var adapter: CurrenciesAdapter? = null
    private val viewModel by viewModel<CurrenciesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            _binding = FragmentCurrenciesBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.isVisible = state
        }

        viewModel.isError.observe(viewLifecycleOwner) { state ->
            if (state) {
                Snackbar.make(binding.root, R.string.warning, Snackbar.LENGTH_LONG)
                    .setAction(R.string.select) { showDatePickerDialog() }.show()
            }
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.date.observe(viewLifecycleOwner) { date ->
            binding.selectDate.text = date.selectDate()
            viewModel.updateCurrencies()
        }

        viewModel.currencies.observe(viewLifecycleOwner) { currencies ->
            binding.swipeRefresh.isRefreshing = false
            adapter = CurrenciesAdapter(currencies)
            binding.recyclerItems.adapter = adapter
            adapter?.listener = ::onCurrencyClicked
        }

        binding.selectDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.updateCurrencies()
        }
    }

    private fun onCurrencyClicked(currencyInfo: CurrencyInfo) {
        val action = CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetailsFragment(
            currencyInfo
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.select_date)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePickerDialog.addOnPositiveButtonClickListener {
            calendar.timeInMillis = it
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val resultDate = GregorianCalendar(year, month, day).time
            viewModel.onDateChosen(resultDate)
        }
        datePickerDialog.show(childFragmentManager, "show")
    }
}