package me.matt11matthew.atherialrunes.file;

import java.io.FileReader;

public interface IAtherialFile {

    void delete();

    void load(FileReader reader);

    Section getSection(String section);

//    String getString(String section, String name);
//
//    Boolean getBoolean(String section, String name);
//
//    Integer getInteger(String section, String name);
//
//    Long getLong(String section, String name);
//
//    Short getShort(String section, String name);

    Object get(String section, String name, Object type);

}
