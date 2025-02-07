package com.app.myapistructure.di

import android.app.Application
import android.content.Context
import com.app.myapistructure.network.domain.ApiInterface
import com.app.myapistructure.network.domain.ApiObject.ApiHeaderKey.X_API_KEY
import com.app.myapistructure.network.domain.ApiObject.ApiHeaderValue.X_API_KEY_VALUE
import com.app.myapistructure.network.utility.CheckValidation
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        client.connectTimeout(120, TimeUnit.SECONDS)
        client.writeTimeout(120, TimeUnit.SECONDS)
        client.readTimeout(120, TimeUnit.SECONDS)

        client.addInterceptor { chain ->
            // Retrieve the token from the data store using runBlocking as it's a suspend function
            //val token = runBlocking { dataStore.getStringData(dataStore.token).first() }

            // Build the request with the additional headers
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header(X_API_KEY, X_API_KEY_VALUE)
         /*   if (token.isNotEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }*/

            // Proceed with the modified request
            chain.proceed(requestBuilder.build())
        }

        return Retrofit.Builder()
            .baseUrl("http://192.168.5.208:8000/api/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

  /*  @Provides
    @Singleton
    fun provideSocket(): Socket {
        return IO.socket(BuildConfig.BASE_SOCKET_URL)
    }

    @Provides
    @Singleton
    fun provideMyDataStore(context: Context): MyDataStore {
        return MyDataStore(context)
    }*/


    @Provides
    @Singleton
    fun checkValidation(context: Context): CheckValidation {
        return CheckValidation(context)
    }
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}