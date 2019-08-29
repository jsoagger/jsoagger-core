/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */


package io.github.jsoagger.core.utils;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class FileUtils {

  /**
   * @param file
   * @return
   */
  public static byte[] toByteArray(File file) {
    final byte[] bFile = new byte[(int) file.length()];

    try (FileInputStream fileInputStream = new FileInputStream(file)) {
      fileInputStream.read(bFile);
    } catch (final IOException e) {
      e.printStackTrace();
    }

    return bFile;
  }


  /**
   * @param path
   * @return
   */
  public static byte[] toByteArray(String path) {
    final File file = new File(path);
    return toByteArray(file);
  }


  /**
   * The final result will be a file nammed as sourcedir in the same level as it.
   *
   * @param sourceDir
   */
  public static String zipDir(File sourceDir) {
    String resultFormat2 = sourceDir.getAbsolutePath() + "_%d.zip";
    String resultFormat = sourceDir.getAbsolutePath() + ".zip";

    File defaultName = new File(resultFormat);
    if (!defaultName.exists() || defaultName.isDirectory()) {
      return resultFormat;
    }

    for (int i = 1; i < Integer.MAX_VALUE; i++) {
      File resultFormat2File = new File(String.format(resultFormat2, i));
      if (!resultFormat2File.exists() || resultFormat2File.isDirectory()) {
        return String.format(resultFormat2, i);
      }
    }

    return null;
  }


  /**
   * Zip the source of the given directory
   *
   * @param sourceDir
   * @param destFilePath
   */
  public static void zipDir(File sourceDir, String destFilePath) {

    ZipOutputStream zos = null;

    try {
      // create a ZipOutputStream to zip the data to
      zos = new ZipOutputStream(new FileOutputStream(destFilePath));

      // assuming that there is a directory named inFolder (If there
      // isn't create one) in the same directory as the one the code
      // call the zipDir method
      zipDir(sourceDir.getAbsolutePath(), zos);

      // close the stream
      zos.close();
    } catch (Exception e) {
      // handle exception
    } finally {
      if (zos != null) {
        try {
          zos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 
   * @param dir2zip
   * @param zos
   * @throws Exception
   */
  public static void zipDir(String dir2zip, ZipOutputStream zos) throws Exception {
    try {
      // create a new File object based on the directory we
      File zipDir = new File(dir2zip);
      // get a listing of the directory content
      String[] dirList = zipDir.list();
      byte[] readBuffer = new byte[2156];
      int bytesIn = 0;
      // loop through dirList, and zip the files
      for (int i = 0; i < dirList.length; i++) {
        File f = new File(zipDir, dirList[i]);
        if (f.isDirectory()) {
          // if the File object is a directory, call this
          // function again to add its content recursively
          String filePath = f.getPath();
          zipDir(filePath, zos);
          // loop again
          continue;
        }

        FileInputStream fis = null;
        try {
          // if we reached here, the File object f was not
          // create a FileInputStream on top of f
          fis = new FileInputStream(f);
          ZipEntry anEntry = new ZipEntry(f.getPath());
          // place the zip entry in the ZipOutputStream object
          zos.putNextEntry(anEntry);
          // now write the content of the file to the ZipOutputStream
          while ((bytesIn = fis.read(readBuffer)) != -1) {
            zos.write(readBuffer, 0, bytesIn);
          }
          // close the Stream
          fis.close();
        } finally {
          if (fis != null) {
            fis.close();
          }
        }
      }
    } catch (Exception e) {
      throw e;
    }
  }


  /**
   * Search a file in classpath, if not found in the local file system 
   * @param clazz
   * @param path
   * @return
   */
  public static InputStream getStreamOf(Class<?> clazz, String path) {
    InputStream inputStream = clazz.getResourceAsStream(path);
    if (inputStream == null) {
      try {
        File file = new File(path);
        inputStream = new FileInputStream(file);
      } catch (FileNotFoundException e) {
        inputStream = null;
      }
    }
    return inputStream;
  }


  /**
   * Recursivly delete directory
   *
   * @param path
   * @return
   */
  public static boolean deleteDirectory(File path) {
    if (path.exists()) {
      File[] files = path.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          deleteDirectory(files[i]);
        } else {
          try {
            files[i].delete();
          } catch (Exception e) {
          }
        }
      }
    }
    return (path.delete());
  }


  public static void writeByteArrayToFile(File file, byte[] bytes) {
    // TODO Auto-generated method stub
  }


  public static List<String> readAllLines(String path){
    return readAllLines(path, true);
  }

  public static List<String> readAllLines(String path, boolean addBlank){
    List<String> lines = new ArrayList<>();
    Scanner scanner = new Scanner(FileUtils.class.getResourceAsStream(path));
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      boolean blankLine = line == null || line.trim().length() == 0;

      if(!blankLine) {
        lines.add(line);
      }
      else {
        if(addBlank) {
          lines.add(line);
        }
      }
    }

    return lines;
  }


  /**
   * Extracts a zip file specified by the zipFilePath to a directory specified by
   * destDirectory (will be created if does not exists)
   * @param zipFilePath
   * @param destDirectory
   * @throws IOException
   */
  public static void unzip(String zipFilePath, String destDirectory) throws IOException {
    File destDir = new File(destDirectory);
    if (!destDir.exists()) {
      destDir.mkdir();
    }

    try(ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))){
      ZipEntry entry = zipIn.getNextEntry();
      // iterates over entries in the zip file
      while (entry != null) {
        String filePath = destDirectory + File.separator + entry.getName();
        if (!entry.isDirectory()) {
          // if the entry is a file, extracts it
          extractFile(zipIn, filePath);
        } else {
          // if the entry is a directory, make the directory
          File dir = new File(filePath);
          dir.mkdir();
        }
        zipIn.closeEntry();
        entry = zipIn.getNextEntry();
      }
    }
  }
  /**
   * Extracts a zip entry (file entry)
   * @param zipIn
   * @param filePath
   * @throws IOException
   */
  private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
    try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))){
      byte[] bytesIn = new byte[4096];
      int read = 0;
      while ((read = zipIn.read(bytesIn)) != -1) {
        bos.write(bytesIn, 0, read);
      }
    }
  }
}
