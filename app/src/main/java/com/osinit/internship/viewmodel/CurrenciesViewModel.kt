package com.osinit.internship.viewmodel

import androidx.lifecycle.*
import com.osinit.internship.data.CurrencyInfo
import com.osinit.internship.repository.CurrencyRepository
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

class CurrenciesViewModel(private val database: CurrencyRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _date = MutableLiveData(Date())
    val date: LiveData<Date> = _date

    private val _currencies = MutableLiveData<List<CurrencyInfo>>()
    val currencies: LiveData<List<CurrencyInfo>> = _currencies

    init {
        date.asFlow()
            .flatMapLatest { date ->
                database.getCurrencies(date)
            }
            .onEach { list ->
                _currencies.postValue(list)
            }
            .launchIn(viewModelScope)
    }

    fun updateCurrencies() {
        val nowDate = date.value ?: return
        _isLoading.value = true
        viewModelScope.launch {
            try {
                database.updateCurrencies(nowDate)
            } catch (e: Exception) {
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onDateChosen(date: Date) {
        _isError.value = false
        _date.value = date
    }
}