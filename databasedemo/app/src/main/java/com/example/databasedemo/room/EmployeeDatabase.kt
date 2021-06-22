package com.example.databasedemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* Serves as the database holder an is the main access point to your relational data. It has to be
annotated with @Database and extents the RoomDatabase. It also contains and returns the Dao. */
@Database(
    entities = [Employee::class],
    version = EmployeeDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private const val DATABASE_NAME = "employee.db"
        const val DATABASE_VERSION = 1

        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(context: Context): EmployeeDatabase {
            // If the INSTANCE is not null, then return it, if it is, then create the database.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance

                // Return the instance.
                instance
            }
        }
    }
}