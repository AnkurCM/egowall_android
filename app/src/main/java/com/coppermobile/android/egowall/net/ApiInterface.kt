package com.coppermobile.android.egowall.net


import com.coppermobile.android.egowall.data.URLs
import com.coppermobile.android.egowall.data.responses.SignupResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by Sunita on 07, February, 2019
 * Api interface class for api call repository
 */
interface ApiInterface {
    @POST(URLs.REGISTER)
    fun userRegistration(@Body registrationRequest: RequestBody): Observable<SignupResponse>

}
