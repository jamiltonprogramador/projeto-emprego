package com.jamiltondamasceno.testeempregopetz.di

import com.jamiltondamasceno.testeempregopetz.data.api.BaseInterceptor
import com.jamiltondamasceno.testeempregopetz.data.api.HearthstoneService
import com.jamiltondamasceno.testeempregopetz.data.repository.CartaRepository
import com.jamiltondamasceno.testeempregopetz.data.repository.CartaRepositoryImpl
import com.jamiltondamasceno.testeempregopetz.utils.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit( okHttpClient: OkHttpClient ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl( Constantes.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .client( okHttpClient )
            .build()
    }

    @Provides
    fun provideOkHttp() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor( BaseInterceptor() )
            .build()
    }

    @Provides
    fun provideHearthstoneService(
        retrofit: Retrofit
    ) : HearthstoneService {
        return retrofit.create( HearthstoneService::class.java )
    }

    @Provides
    fun provideCartaRepository(
        hearthstoneService: HearthstoneService
    ) : CartaRepository {
        return CartaRepositoryImpl( hearthstoneService )
    }

}