package edu.xfolex.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.xfolex.weatherapp.data.remote.api.WeatherApi
import edu.xfolex.weatherapp.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()

    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(app: Application): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(app)


}