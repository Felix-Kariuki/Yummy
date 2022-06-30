package com.flexcode.yummy.di

import android.app.Application
import androidx.room.Room
import com.flexcode.yummy.data.local.MealsDatabase
import com.flexcode.yummy.data.remote.ApiService
import com.flexcode.yummy.data.repository.CategoriesRepositoryImpl
import com.flexcode.yummy.data.repository.MealsRepositoryImpl
import com.flexcode.yummy.domain.repository.CategoriesRepository
import com.flexcode.yummy.domain.repository.MealsRepository
import com.flexcode.yummy.domain.use_case.GetCategoriesUseCase
import com.flexcode.yummy.domain.use_case.GetMealsUseCase
import com.flexcode.yummy.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient): ApiService {
        val  retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun providesGetMealsUseCase(repository: MealsRepository): GetMealsUseCase{
        return GetMealsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetCategoriesUseCase(repository: CategoriesRepository): GetCategoriesUseCase{
        return GetCategoriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesMealsRepository(
        db : MealsDatabase,
        apiService: ApiService
    ) : MealsRepository {
        return MealsRepositoryImpl(
            apiService = apiService,
            dao = db.dao
        )
    }

    @Provides
    @Singleton
    fun providesCategoriesRepository(
        db : MealsDatabase,
        apiService: ApiService
    ) : CategoriesRepository {
        return CategoriesRepositoryImpl(
            apiService = apiService,
            dao = db.dao
        )
    }

    @Provides
    @Singleton
    fun providesMealsDatabase(app: Application): MealsDatabase {
        return Room.databaseBuilder(
            app,
            MealsDatabase::class.java,
            "meals_db.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}