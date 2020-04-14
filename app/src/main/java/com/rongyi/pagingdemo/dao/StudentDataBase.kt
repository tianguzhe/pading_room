package com.rongyi.pagingdemo.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDataBase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        private var INSTANCE: StudentDataBase? = null

        fun getDataBase(context: Context): StudentDataBase {
            INSTANCE = INSTANCE
                ?: Room
                    .databaseBuilder(
                        context.applicationContext,
                        StudentDataBase::class.java,
                        "students_database"
                    )
                    //破坏式迁移
//                .fallbackToDestructiveMigration()
//                .addMigrations(object : Migration(2, 3) {
//                    override fun migrate(database: SupportSQLiteDatabase) {
//                        database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1")
//                    }
//                })
//                    .addMigrations(object : Migration(3, 4) {
//                        override fun migrate(database: SupportSQLiteDatabase) {
//                            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, english_world TEXT, chinese_meaning TEXT)")
//                            database.execSQL("INSERT INTO word_temp (id,english_world,chinese_meaning) SELECT id,english_world,chinese_meaning FROM word")
//                            database.execSQL("DROP TABLE word")
//                            database.execSQL("ALTER TABLE word_temp RENAME TO word")
//                        }
//                    })
                    .build()
            return INSTANCE!!
        }

    }

}