package com.example.design_patterns.singleton;

public class DBConfig {

    private static DBConfig dbConfig;

    static DBConfig getInstance(){

        if (dbConfig == null){
            dbConfig = new DBConfig();
        }
        return dbConfig;
    }

    private DBConfig(){}
}
