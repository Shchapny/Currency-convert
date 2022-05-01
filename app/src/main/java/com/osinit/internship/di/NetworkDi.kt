package com.osinit.internship.di

import com.osinit.internship.api.CurrencyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    factory { provideLogging() }
    factory { provideOkHttpClient(loggingInterceptor = get()) }
    single { provideRetrofit(okHttpClient = get()) }
    factory { provideCurrencyApi(retrofit = get()) }
}

private const val BASE_URL = "https://www.cbr-xml-daily.ru/archive/"
private const val TIMEOUT = 20L

fun provideLogging(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideCurrencyApi(retrofit: Retrofit): CurrencyApi {
    return retrofit.create(CurrencyApi::class.java)
}