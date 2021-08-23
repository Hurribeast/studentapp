package be.leeroy.studentapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.leeroy.studentapp.dataaccess.UserDataAccess
import be.leeroy.studentapp.dataaccess.mappers.UserMapper
import be.leeroy.studentapp.exceptions.NoConnectivityException
import be.leeroy.studentapp.models.errors.Errors
import be.leeroy.studentapp.services.RetrofitConfigurationService
import be.leeroy.studentapp.utils.ApiUtils
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val _error : MutableLiveData<Errors?> = MutableLiveData<Errors?>()
    val error : LiveData<Errors?> = _error

    private var _changed : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val changed : LiveData<Boolean> = _changed

    private var mapper : UserMapper = UserMapper.getInstance()
    private var dataAccess : UserDataAccess = RetrofitConfigurationService.getInstance(application).userDataAccess()

    fun changePassword(token: String, password : String, oldPassword : String) {
        val bodyParams : HashMap<String, Any> = HashMap<String, Any>()
        bodyParams["newPassword"] = password
        bodyParams["oldPassword"] = oldPassword

        val requestBody : RequestBody = ApiUtils.ToRequestBody(bodyParams)

        dataAccess.changePassword(token, requestBody).enqueue(object : Callback<RequestBody> {
            override fun onResponse(call: Call<RequestBody>, response: Response<RequestBody>) {
                if(response.isSuccessful) {
                    _changed.value = true
                    _error.value = null
                } else if (response.code() == 403) {
                    _changed.value = false
                    _error.value = Errors.PASSWORD_INCORRECT
                } else {
                    _changed.value = false
                    _error.value = Errors.TECHNICAL_ERROR
                }
            }

            override fun onFailure(call: Call<RequestBody>, t: Throwable) {
                if(t is NoConnectivityException) {
                    _error.value = Errors.NO_CONNECTION
                } else {
                    _error.value = Errors.TECHNICAL_ERROR
                }
            }
        })
    }
}