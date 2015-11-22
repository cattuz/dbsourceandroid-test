package com.devexed.dbsourceandroidtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import com.devexed.dbsource.Connection;
import com.devexed.dbsource.DatabaseTestCase;
import com.devexed.dbsourceandroid.AndroidSQLiteConnection;

import java.io.File;

public final class AndroidSQLiteDatabaseTest extends DatabaseTestCase {

    File file;

    /*private static void runPragma(SQLiteDatabase connection, String pragma) {
        // Runs a pragma on the database. Apparently needs to be a query whose cursor is actually used for the pragma to
        // take effect.
        Cursor cursor = connection.rawQuery(pragma, new String[0]);
        cursor.moveToFirst();
        cursor.close();
    }*/

    Context getContext() throws Exception {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Override
    public Connection createConnection() {
        try {
            file = File.createTempFile("test.android-sqlite", ".db", getContext().getCacheDir());
            return new AndroidSQLiteConnection(file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroyConnection() {
        if (!file.delete()) throw new RuntimeException("Failed to delete database file " + file.getAbsolutePath());
    }

}
