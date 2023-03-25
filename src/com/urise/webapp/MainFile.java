package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void main(String[] args) {
        String filePath = "/Users/Kirdan/basejava/.gitignore";
        File file = new File(filePath);

        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("/Users/Kirdan/basejava/src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(filePath);
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        list(dir);
    }

    public static void list(File dir) {
        File[] files = dir.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files, "Directory read error").length; i++) {
            if (files[i].isFile()) {
                System.out.println("File: " + files[i].getAbsolutePath());
            } else if (files[i].isDirectory()) {
                System.out.println("Directory: " + files[i].getAbsolutePath());
                list(files[i]);
            }
        }
    }
}
