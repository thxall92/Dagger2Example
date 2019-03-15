package com.eunhye.dagger2example.di.module

import com.eunhye.dagger2example.network.PostApi
import com.eunhye.dagger2example.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Safe here as we are dealing with a Dagger 2 module
 */

@Module
object NetworkModule {

    /**
     * Provides the Post service implementation.
     * @Param retrofit : used to instantiate the service
     * @Return the Post service implementation.
     */

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            // RxJava와 Retrofit2를 연결, retrofit instance를 빌드할 때 Observable이나 Single 타입을 받을 수 있다
            .client(client)
            .build()
    }
}