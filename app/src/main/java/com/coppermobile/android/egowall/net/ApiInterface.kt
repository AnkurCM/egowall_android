package com.coppermobile.android.egowall.net


import com.coppermobile.android.egowall.data.URLs
import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by Sunita on 07, February, 2019
 * Api interface class for api call repository
 */
interface ApiInterface {

    @POST(URLs.REGISTER)
    fun userRegistration(@Body registrationRequest: SignupRequest): Observable<SignupResponse>

}
