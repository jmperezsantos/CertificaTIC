package org.certificatic.dependencyinjectionexample.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import org.certificatic.dependencyinjectionexample.services.UserServiceWS
import org.certificatic.dependencyinjectionexample.wsclient.UserWSClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class HiltApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://certificatic-wsexample-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Inject
    fun provideUserWSClient(
        retrofit: Retrofit
    ): UserWSClient {

        return retrofit.create(UserWSClient::class.java)

    }

    @Provides
    @Inject
    fun provideUserService(wsClient: UserWSClient): UserServiceWS {
        return UserServiceWS(wsClient)
    }

}