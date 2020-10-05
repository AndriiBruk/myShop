package com.example.myshop.repositories

import androidx.core.content.contentValuesOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.network.ShopApiService
import com.example.myshop.overview.OverviewViewModel
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {


//    fun userLogin(login: String, password: String) : MutableLiveData<String>{
//        val loginResponse = MutableLiveData<String>()
//
//        ShopApiService().userLogin(login, password).enqueue(object: Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful) {
//                    loginResponse.value = response.body()?.string()
//                    val gson =GsonBuilder().create()
//                    val auth = gson.fromJson(loginResponse.value, Auth::class.java)
//                    loginResponse.value = auth.response.token
//
//                } else {
//                    loginResponse.value = response.errorBody()?.string()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                loginResponse.value = t.message
//            }
//
//        })
//       return loginResponse
//
//    }
fun userLogin(login: String, password: String) : MutableLiveData<String>{

    val responseTest = MutableLiveData<String>()

    ShopApiService().userLogin(login, password).enqueue(object: Callback<ResponseBody>{
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                var loginResponse = response.body()?.string()
                val gson =GsonBuilder().create()
                val auth = gson.fromJson(loginResponse, Auth::class.java)
                responseTest.value = auth.response.token


            } else {
                responseTest.value = response.errorBody()?.string()
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            responseTest.value = t.message
        }

    })
    return responseTest

}

    fun catalogExport(token:String):MutableLiveData<String>{
        val catalogExportResponse = MutableLiveData<String>()

        ShopApiService().catalogExport(token).enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.isSuccessful) {
                    catalogExportResponse.value = response.body()?.string()
                    val gson = GsonBuilder().create()
                    val catalogExport =
                        gson.fromJson(catalogExportResponse.value, Export::class.java)
                        catalogExportResponse.value = catalogExport.response.products[0].article
                }else {
                    catalogExportResponse.value = response.errorBody()?.string()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                catalogExportResponse.value = t.message
           }
        })
        return catalogExportResponse
    }
}

class Auth(val status: String, val response: ResponseToken)
class ResponseToken(val token:String)

class Export(val response:ResponseProduct)
class ResponseProduct(val products: List<Product>)
class Product (val article: String)