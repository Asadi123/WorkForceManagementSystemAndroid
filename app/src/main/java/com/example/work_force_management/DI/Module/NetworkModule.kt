package com.example.work_force_management.DI.Module

import android.app.Application
import okhttp3.Cache
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


//    @Provides
//    @Singleton
//    internal fun provideOkhttpClient(cache: Cache, networkInterceptor: NetworkInterceptor): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//        httpClient.cache(cache)
//        httpClient.addInterceptor(networkInterceptor)
//        httpClient.addInterceptor(logging)
//        httpClient.addNetworkInterceptor(RequestInterceptor())
//        httpClient.connectTimeout(30, TimeUnit.SECONDS)
//        httpClient.readTimeout(30, TimeUnit.SECONDS)
//        return httpClient.build()
//    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("")
            .client(okHttpClient)
            .build()
    }
}