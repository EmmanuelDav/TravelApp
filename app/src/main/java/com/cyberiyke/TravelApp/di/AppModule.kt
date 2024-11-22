package com.cyberiyke.TravelApp.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.cyberiyke.TravelApp.data.local.AppDatabase
import com.cyberiyke.TravelApp.data.local.MIGRATION_1_2
import com.cyberiyke.TravelApp.data.network.ApiService
import com.google.firebase.crashlytics.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://ca9e5bccd99332d54d8a.free.beeceptor.com/api/traveldetail/"


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext  context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME )
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase) = database.getTripDao()


    @Provides
    @Singleton
    fun provideOkHttpsClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor{
                chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", com.cyberiyke.TravelApp.BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(com.cyberiyke.TravelApp.di.AppModule.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics {
        return  FirebaseCrashlytics.getInstance()
    }

    @Provides
    @Singleton
    fun provideTimberTree(): Timber.Tree{
        return if(BuildConfig.DEBUG){
            Timber.DebugTree()

        }else{
            com.cyberiyke.TravelApp.di.AppModule.CrashReportingTree()
        }
    }

    // Custom Timber tree for release builds to log to Crashlytics
    class CrashReportingTree: Timber.Tree(){
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

            if (priority == Log.VERBOSE || priority == Log.DEBUG) return

            // Log to Crashlytics for warning, error, and assert priorities
            FirebaseCrashlytics.getInstance().log(message)

            if (t != null){
                Timber.d(t)
                FirebaseCrashlytics.getInstance().recordException(t)
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): com.cyberiyke.TravelApp.data.network.ApiService {
        return retrofit.create(com.cyberiyke.TravelApp.data.network.ApiService::class.java)
    }
}