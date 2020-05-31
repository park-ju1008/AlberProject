package org.themeselves.alberandroid.data.source

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.themeselves.alberandroid.data.model.ResponseContent
import org.themeselves.alberandroid.data.model.UserJoin
import org.themeselves.alberandroid.di.RepositoryModule.RemoteDataSource
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    @RemoteDataSource private val userRemoteDataSource: UserDataSource
) : UserRepository {

    override fun joinEmailUser(userJoin: UserJoin): Single<ResponseContent<String>> =
        userRemoteDataSource.joinEmailUser(userJoin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}