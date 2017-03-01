package fr.bourgmapper.tub.data.entity;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = CacheDatabase.NAME, version = CacheDatabase.VERSION)
public class CacheDatabase {
    public static final String NAME = "CacheDatabase";

    public static final int VERSION = 1;
}
