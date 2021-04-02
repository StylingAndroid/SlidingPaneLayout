package com.stylingandroid.slidingpanelayout

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(val listItems: List<String>) : ViewModel()
