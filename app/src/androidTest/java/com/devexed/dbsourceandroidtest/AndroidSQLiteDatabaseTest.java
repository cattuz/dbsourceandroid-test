package com.devexed.dbsourceandroidtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.devexed.dalwit.Connection;
import com.devexed.dalwit.DatabaseTestCase;
import com.devexed.dalwitandroid.AndroidSQLiteConnection;

import java.io.File;

public final class AndroidSQLiteDatabaseTest extends DatabaseTestCase {

    File file;

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
