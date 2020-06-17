package com.seletivo.promobit.di.module

import android.app.Application
import androidx.room.Room
import com.seletivo.promobit.BuildConfig
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.gateway.WebService
import com.seletivo.promobit.gateway.database.ContactDb
import com.seletivo.promobit.gateway.database.dao.ContactDao
import com.seletivo.promobit.util.livedata.LiveDataCallAdapter
import com.seletivo.promobit.util.livedata.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideWebService(): WebService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build().create(WebService::class.java)

    }

    @Provides
    fun provideContactObservable(): ContactObservable {
        return ContactObservable()
    }

    @Singleton
    @Provides
    fun provideContactDb(app: Application): ContactDb {
        return Room
            .databaseBuilder(app, ContactDb::class.java, "contact.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContactDao(db: ContactDb): ContactDao {
        return db.contactDao()
    }

}