package com.osinit.internship.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.osinit.internship.R
import com.osinit.internship.databinding.FragmentCurrencyDetailsBinding
import com.osinit.internship.util.Keyboard
import com.osinit.internship.util.afterTextChanged
import com.osinit.internship.util.replaceToDot
import com.osinit.internship.viewmodel.CurrenciesDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CurrencyDetailsFragment : Fragment(R.layout.fragment_currency_details) {

    private val currencyArgs by navArgs<CurrencyDetailsFragmentArgs>()
    private var _binding: FragmentCurrencyDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<CurrenciesDetailsViewModel> {
        parametersOf(currencyArgs.detailsCurrency)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            _binding = FragmentCurrencyDetailsBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarConvert.apply {
            title = viewModel.currencyCode
            setOnClickListener {
                findNavController().navigateUp()
                Keyboard.hideKeyboard(requireView())
            }
        }

        binding.apply {
            textValue.text = getString(R.string.symbol, viewModel.currencyValue).replaceToDot()
            textCountLayout.hint = viewModel.currencyCode
            nameCurrency.text = viewModel.currencyTitle
        }

        viewModel.currencyCalc.observe(viewLifecycleOwner) {
            binding.textCalculation.setText(String.format("%.2f", it).replaceToDot())
        }

        binding.textCount.afterTextChanged(viewModel::getCalculationValues)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}