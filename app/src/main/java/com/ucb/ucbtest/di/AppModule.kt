package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.*
import com.ucb.data.dog.IDogRemoteDataSource
import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.data.git.ILocalDataSource
import com.ucb.data.movie.IMovieRemoteDataSource
import com.ucb.data.mars.IMarsRemoteDataSource
import com.ucb.framework.dog.DogRemoteDataSource
import com.ucb.framework.github.GithubLocalDataSource
import com.ucb.framework.github.GithubRemoteDataSource
import com.ucb.framework.movie.MovieRemoteDataSource
import com.ucb.framework.mars.MarsRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder
import com.ucb.ucbtest.R
import com.ucb.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // RetrofitBuilder
    @Provides
    @Singleton
    fun providerRetrofitBuilder(@ApplicationContext context: Context): RetrofitBuilder {
        return RetrofitBuilder(context)
    }

    // GitHub
    @Provides
    @Singleton
    fun gitRemoteDataSource(retrofit: RetrofitBuilder): IGitRemoteDataSource {
        return GithubRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): ILocalDataSource {
        return GithubLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun gitRepository(remote: IGitRemoteDataSource, local: ILocalDataSource): GithubRepository {
        return GithubRepository(remote, local)
    }

    @Provides
    @Singleton
    fun provideSaveGitAlias(repository: GithubRepository): SaveGitalias {
        return SaveGitalias(repository)
    }

    @Provides
    @Singleton
    fun provideGitUseCases(repository: GithubRepository): FindGitAlias {
        return FindGitAlias(repository)
    }

    // Movies
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(retrofit: RetrofitBuilder): IMovieRemoteDataSource {
        return MovieRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: IMovieRemoteDataSource): MovieRepository {
        return MovieRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetPopularMovies(movieRepository: MovieRepository, @ApplicationContext context: Context): GetPopularMovies {
        val token = context.getString(R.string.token)
        return GetPopularMovies(movieRepository, token)
    }

    // Dogs
    @Provides
    @Singleton
    fun provideDogRemoteDataSource(retrofit: RetrofitBuilder): IDogRemoteDataSource {
        return DogRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun provideDogRepository(dataSource: IDogRemoteDataSource): DogRepository {
        return DogRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetRandomDog(dogRepository: DogRepository): GetRandomDog {
        return GetRandomDog(dogRepository)
    }

    // Mars
    @Provides
    @Singleton
    fun provideMarsRemoteDataSource(retrofit: RetrofitBuilder): IMarsRemoteDataSource {
        return MarsRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun provideMarsRepository(dataSource: IMarsRemoteDataSource): MarsRepository {
        return MarsRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetMarsPhotos(repository: MarsRepository): GetMarsPhotos {
        return GetMarsPhotos(repository)
    }
}
