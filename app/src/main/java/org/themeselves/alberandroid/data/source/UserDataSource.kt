package org.themeselves.alberandroid.data.source

import io.reactivex.Single
import org.themeselves.alberandroid.data.model.LoginInfo
import org.themeselves.alberandroid.data.model.ResponseContent
import org.themeselves.alberandroid.data.model.User
import org.themeselves.alberandroid.data.model.UserJoin

interface UserDataSource {

    fun joinEmailUser(userJoin:UserJoin
    ): Single<ResponseContent<String>>

    fun login(loginInfo: LoginInfo):Single<ResponseContent<String>>

    fun getUserInfoDetail(): Single<ResponseContent<User>>

    fun deleteUser(): Single<ResponseContent<User>>

    fun updateImage() //파일 형식 확인.


}