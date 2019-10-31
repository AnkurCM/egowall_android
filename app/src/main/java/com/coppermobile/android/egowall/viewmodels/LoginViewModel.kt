package com.coppermobile.android.egowall.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coppermobile.android.egowall.data.requests.LoginRequest
import com.coppermobile.android.egowall.data.responses.LoginResponse
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.repositories.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private var loginRepository: LoginRepository) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    var response: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun getLoginResponse(loginRequest: LoginRequest) {
        disposable.add(loginRepository.getLoginResponse(loginRequest)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

                response.value = Resource.loading(LoginResponse())

            }.doOnTerminate {

                response.value = Resource.idle(LoginResponse())

            }
            .subscribe(
                {
                    response.value = Resource.success("", it)
                },
                {
                    response.value = Resource.error(it.message!!, LoginResponse())
                })
        )
    }
}