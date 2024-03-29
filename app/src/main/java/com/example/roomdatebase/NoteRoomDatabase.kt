package com.example.roomdatebase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var noteRoomInstance: NoteRoomDatabase? = null

        internal fun getDatabase(context: Context): NoteRoomDatabase? {
            if (noteRoomInstance == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (noteRoomInstance == null) {
                        noteRoomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java, "note_db")
                            .build()
                    }
                }
            }
            return noteRoomInstance
        }
    }
}