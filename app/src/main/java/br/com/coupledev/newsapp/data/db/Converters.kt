package br.com.coupledev.newsapp.data.db

import androidx.room.TypeConverter
import br.com.coupledev.newsapp.data.models.SourceModel

class Converters {

    @TypeConverter
    fun fromSource(source: SourceModel): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): SourceModel {
        return SourceModel(name, name)
    }
}