package org.themeselves.alberandroid.data.source.remote

import io.reactivex.Single
import org.themeselves.alberandroid.data.model.LoginInfo
import org.themeselves.alberandroid.data.model.ResponseContent
import org.themeselves.alberandroid.data.model.UserJoin
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("users")
    fun joinEmail(@Body userJoin:UserJoin ): Single<ResponseContent<String>>

    @POST("session")
    fun login(@Body login:LoginInfo):Single<ResponseContent<String>>
}