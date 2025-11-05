package id.my.mufidz.apicalling.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.apicalling.data.UserRepository
import id.my.mufidz.apicalling.screen.home.HomeUseCase
import id.my.mufidz.apicalling.screen.register.RegisterUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        @IODispatcher dispatcher: CoroutineDispatcher,
        userRepository: UserRepository
    ) = RegisterUseCase(dispatcher, userRepository)

    @Provides
    @Singleton
    fun provideHomeUseCase(
        @IODispatcher dispatcher: CoroutineDispatcher,
        userRepository: UserRepository
    ) = HomeUseCase(dispatcher, userRepository)

}