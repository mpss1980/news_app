package br.com.coupledev.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.coupledev.newsapp.data.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract val articleDao: ArticleDao

    companion object {
        const val DATABASE_NAME = "article_db"
    }
}