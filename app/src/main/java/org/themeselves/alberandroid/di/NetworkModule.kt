package org.themeselves.alberandroid.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.themeselves.alberandroid.BuildConfig
import org.themeselves.alberandroid.util.ErrorInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "http://ec2-15-164-93-24.ap-northeast-2.compute.amazonaws.com:8080/"
private const val CONNECT_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideJsonParser(): JsonParser =
        JsonParser()

    @Singleton
    @Provides
    fun provideCharset(): Charset =
        Charset.forName("UTF-8")

    @Singleton
    @Provides
    fun provideErrorInterceptor(jsonParser: JsonParser, charset: Charset): ErrorInterceptor =
        ErrorInterceptor(jsonParser, charset)

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, errorInterceptor: ErrorInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(cache)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(errorInterceptor)
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

}