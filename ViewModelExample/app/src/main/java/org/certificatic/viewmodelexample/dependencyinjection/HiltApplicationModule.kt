package org.certificatic.viewmodelexample.dependencyinjection

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import org.certificatic.viewmodelexample.services.UserServiceWS
import org.certificatic.viewmodelexample.viewmodel.UserListViewModel
import org.certificatic.viewmodelexample.wsclient.UserWSClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
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