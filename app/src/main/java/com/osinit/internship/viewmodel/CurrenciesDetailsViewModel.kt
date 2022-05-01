package com.osinit.internship.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osinit.internship.data.CurrencyInfo
import kotlinx.coroutines.launch

class CurrenciesDetailsViewModel(currencyInfo: CurrencyInfo) : ViewModel() {

    private val _currencyCalc = MutableLiveData<Double>()
    val currencyCalc: LiveData<Double> = _currencyCalc

    val currencyValue = currencyInfo.value
    val currencyTitle = currencyInfo.name
    val currencyCode = currencyInfo.charCode

    fun getCalculationValues(count: String) {
        val number = if (count.isEmpty()) 0.0 else count.toDouble()
        viewModelScope.launch {
            val result = number * currencyValue
            _currencyCalc.postValue(result)
        }
    }
}