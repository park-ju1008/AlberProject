package org.themeselves.alberandroid.data.source

import io.reactivex.Single
import org.themeselves.alberandroid.data.model.ResponseContent
import org.themeselves.alberandroid.data.model.UserJoin

interface UserRepository {

    fun joinEmailUser(userJoin: UserJoin): Single<ResponseContent<String>>
}