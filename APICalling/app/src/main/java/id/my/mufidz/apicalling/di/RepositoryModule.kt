package id.my.mufidz.apicalling.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.apicalling.data.UserRepository
import id.my.mufidz.apicalling.data.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}