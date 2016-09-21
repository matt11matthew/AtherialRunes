package me.matt11matthew.atherialrunes.file;

import me.matt11matthew.atherialrunes.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AtherialFile implements IAtherialFile {

    private File file;
    private String name;
    private String contents;

    private AtherialFile(File file) {
        this.file = file;
        this.name = file.getName();
        try {
            load(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        file.delete();
    }

    @Override
    public void load(FileReader reader) {
        this.contents = FileUtils.getTextOfFile(reader);
    }

    @Override
    public Section getSection(String section) {
        return Section.select(section, this, contents);
    }

    @Override
    public Object get(String section, String name, Object type) {
        Section section1 = getSection(section);
        return section1.get(name, type);
    }

    public static AtherialFile loadFile(File file) {
        return new AtherialFile(file);
    }
}
