package com.coppermobile.android.egowall.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.repositories.SignupRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignupViewModel(private var signupRepository: SignupRepository) : ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    var response: MutableLiveData<Resource<SignupResponse>> = MutableLiveData()

    fun getRegistrationResponse(signupRequest: SignupRequest) {
        disposable.add(signupRepository.getRegisterResponse(signupRequest)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

                response.value = Resource.loading(SignupResponse())

            }.doOnTerminate {

                response.value = Resource.idle(SignupResponse())

            }
            .subscribe(
                {
                    response.value = Resource.success(it.errorMessage?.msg!!, it)
                },
                {
                    response.value = Resource.error(it.message!!, SignupResponse())
                })
        )
    }
}