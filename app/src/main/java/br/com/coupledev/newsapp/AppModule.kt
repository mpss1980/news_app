package br.com.coupledev.newsapp

import android.app.Application
import androidx.room.Room
import br.com.coupledev.newsapp.data.api.NewsApi
import br.com.coupledev.newsapp.data.db.ArticleDatabase
import br.com.coupledev.newsapp.data.repositories.NewsRepositoryImpl
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import br.com.coupledev.newsapp.domain.usecases.*
import br.com.coupledev.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            ArticleDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(db: ArticleDatabase, api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi = api, db = db.articleDao)
    }

    @Provides
    @Singleton
    fun providesDeleteArticleUseCase(repository: NewsRepository): DeleteArticleUseCase {
        return DeleteArticleUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetSavedArticlesUseCase(repository: NewsRepository): GetSavedArticlesUseCase {
        return GetSavedArticlesUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetBreakingNewsUsecase(repository: NewsRepository): GetBreakingNewsUseCase {
        return GetBreakingNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSearchNewsUsecase(repository: NewsRepository): SearchNewsUseCase {
        return SearchNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSaveArticleUseCase(repository: NewsRepository): SaveArticleUseCase {
        return SaveArticleUseCase(repository)
    }
}