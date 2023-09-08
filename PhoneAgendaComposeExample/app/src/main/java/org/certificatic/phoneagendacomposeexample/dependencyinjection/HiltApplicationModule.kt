package org.certificatic.phoneagendacomposeexample.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import org.certificatic.phoneagendacomposeexample.services.UserService
import org.certificatic.phoneagendacomposeexample.wsclient.UserWSClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class HiltApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://residencial-vivaldi-default-rtdb.firebaseio.com/")
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
    fun provideUserService(wsClient: UserWSClient): UserService {
        return UserService(wsClient)
    }

}