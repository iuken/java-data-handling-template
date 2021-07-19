package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long counter = 0;
        //adding "src/main/resources/" to the path, because file not found in resources folder. May be some project setting is wrong... don't know.
        File file = new File("src/main/resources/" + path);
        File[] fileList = file.listFiles();
        for (File interFile : fileList) {
            if (interFile.isDirectory()) {
                counter += countFilesInDirectory(path + File.separator + interFile.getName());
            } else {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long counter = 1;
        File file = new File("src/main/resources/" + path);
        File[] fileList = file.listFiles();
        for (File interFile : fileList) {
            if (interFile.isDirectory()) {
                counter += countDirsInDirectory(path + File.separator + interFile.getName());
            }
        }
        return counter;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File dirFrom = new File(from).getParentFile();
        File dirTo = new File(to).getParentFile();
        File[] files = dirFrom.listFiles();
        if (files == null) {
            return;
        }
        if (!dirTo.exists()) {
            dirTo.mkdir();
        }
        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                try {
                    Files.copy(file.toPath(), new File(dirTo + File.separator + file.getName()).toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File dir = new File(getClass().getResource("/").getPath() + path);
        File file = new File(dir.getPath() + File.separator + name);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder line = new StringBuilder();
        int readBytes;
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/" + fileName)) {
            while ((readBytes = fileInputStream.read()) != -1) {
                line.append((char) readBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(line);
    }
}
