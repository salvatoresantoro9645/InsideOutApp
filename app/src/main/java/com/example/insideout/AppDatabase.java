package com.example.insideout;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static AppDatabase DB_INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(DB_INSTANCE == null){
            DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_INSIDEOUT")
                            .allowMainThreadQueries()
                            //.fallbackToDestructiveMigration() //Used only during test
                            .build();
        }
        return DB_INSTANCE;
    }
}
