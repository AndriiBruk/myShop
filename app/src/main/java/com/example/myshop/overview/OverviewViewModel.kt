package com.example.myshop.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myshop.repositories.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    private var _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    val login = "test"
    val password = "12345"

    var loginResponse  = UserRepository().userLogin(login!!,password!!)
    var token = loginResponse
    var token1:String = loginResponse.toString()
    var token2:String = loginResponse.value.toString()
    var token3:String? = loginResponse.value



val test = "OK"
  // val catalogExportResponse = UserRepository().catalogExport(loginResponse)

    init {
        getMinicards()
    }

    private fun getMinicards() {

        _response.value = "I'm response"
    }



}