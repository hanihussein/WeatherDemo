package com.hani.weatherdemo.core.di

import com.hani.weatherdemo.core.Configuration
import com.hani.weatherdemo.core.utils.LiveDataCallAdapterFactory
import com.hani.weatherdemo.data.WeatherRepositoryImpl
import com.hani.weatherdemo.data.remote.RemoteDataSource
import com.hani.weatherdemo.domain.repository.WeatherRepository
import com.hani.weatherdemo.presentation.WeatherViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val repoModule = module {

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}


val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}


val networkModule = module {

    fun provideRemoteData(retrofit: Retrofit): RemoteDataSource {

        return retrofit.create(RemoteDataSource::class.java)
    }

    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder().client(client)
            .baseUrl(Configuration.REMOTE_ENDPOINT)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    fun provideClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient().newBuilder().addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }


    fun provideInterceptor(): Interceptor {

        return Interceptor.invoke {

            val url =
                it.request().url.newBuilder().build()
            val request = it.request()
                .newBuilder()
                .url(url)
                .build()
            it.proceed(request)

        }
    }
    single { provideRemoteData(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideClient(get(), get()) }
    single { provideInterceptor() }
    single { provideGsonConverterFactory() }
    single { provideHttpLoggingInterceptor() }
}

