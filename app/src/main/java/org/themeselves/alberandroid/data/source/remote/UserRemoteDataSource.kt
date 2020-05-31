package org.themeselves.alberandroid.data.source.remote

import io.reactivex.Single
import org.themeselves.alberandroid.data.model.LoginInfo
import org.themeselves.alberandroid.data.model.ResponseContent
import org.themeselves.alberandroid.data.model.User
import org.themeselves.alberandroid.data.model.UserJoin
import org.themeselves.alberandroid.data.source.UserDataSource

class UserRemoteDataSource internal constructor(
    private val userService: UserService
) : UserDataSource {

    override fun joinEmailUser(userJoin: UserJoin): Single<ResponseContent<String>> =
        userService.joinEmail(userJoin)


    override fun login(loginInfo: LoginInfo): Single<ResponseContent<String>> =
        userService.login(loginInfo)


    override fun getUserInfoDetail(): Single<ResponseContent<User>> {
        TODO("Not yet implemented")
    }

    override fun deleteUser(): Single<ResponseContent<User>> {
        TODO("Not yet implemented")
    }

    override fun updateImage() {
        TODO("Not yet implemented")
    }
}