package me.matt11matthew.atherialrunes.file;

public class Section {

    private AtherialFile file;
    private String sectionName;
    private String contents;

    public Section(AtherialFile file, String section, String content) {
        this.sectionName = section;
        this.file = file;
        StringBuilder sb = new StringBuilder();
        String newContends = content.split("[" + section + "]")[1].split("\\[")[0];
        this.contents = newContends;
    }

    public static Section select(String section, AtherialFile file, String content) {
        return new Section(file, section, content);
    }

    public Object get(String name, Object type) {
        Object object = null;
        String[] con = contents.split("\n");
        for (int i = 0; i < con.length; i++) {
            if (con[i].contains(name + "=")) {
                object = con[i].split("=".replaceAll(" ", ""));
            }
        }
        return object;
    }
}
