package com.bahaa.haseel.screen.checkOut

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CheckOutViewModelImp(val context: Application) : AndroidViewModel(context),
    CheckOutViewModel {

    val progressLiveData = MutableLiveData<Boolean>()
    val showAlertLiveData = MutableLiveData<String>()


}