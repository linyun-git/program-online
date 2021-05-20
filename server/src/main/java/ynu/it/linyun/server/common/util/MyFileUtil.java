package ynu.it.linyun.server.common.util;

import java.io.*;
import java.util.ArrayList;

/**
 * @author linyun
 * @since 2021-04-25-20:32
 */
public class MyFileUtil {
    /**
     * 新建目录
     *
     * @param folderPath String 如 c:/fqf
     */
    public static void newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹,包括里面的文件
     *
     * @param folderPath 文件夹路径字符串
     */
    public static void deleteFolder(String folderPath) {
        try {
            File myFilePath = new File(folderPath);
            if (myFilePath.exists()) {
                deleteAllFile(folderPath, true); // 删除完里面所有内容
                myFilePath.delete(); // 删除空文件夹
            }
            // log4j.info("ok!Delete folder success: " + folderPath);
        } catch (Exception e) {
            e.printStackTrace();
            // log4j.error("Delete folder fail: " + folderPath);
        }
    }

    /**
     * 获得某一文件夹下的所有目录的集合
     *
     * @param filePath 文件夹路径
     * @return ArrayList，目录名的集合
     */
    public static ArrayList<String> getFolderNameFromFolder(String filePath) {
        ArrayList<String> folders = new ArrayList<>();
        File rootFolder = new File(filePath);
        try {
            File[] files = rootFolder.listFiles();
            if (null == files) {
                return folders;
            }
            for (var file : files) {
                if (file.isDirectory()) {
                    folders.add(file.getName());
                }
            }
        } catch (Exception e) {
            // fileNames.add("尚无文件到达！");
            // e.printStackTrace();
            // log4j.info("Can not find files!"+e.getMessage());
        }
        return folders;
    }

    /**
     * 获得某一文件夹下的所有目录的集合
     *
     * @param filePath 文件夹路径
     * @return ArrayList，目录名的集合
     */
    public static ArrayList<String> getFileNameFromFolder(String filePath) {
        ArrayList<String> fileNames = new ArrayList<>();
        File rootFolder = new File(filePath);
        try {
            File[] files = rootFolder.listFiles();
            if (null == files) {
                return fileNames;
            }
            for (var file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        } catch (Exception e) {
            // fileNames.add("尚无文件到达！");
            // e.printStackTrace();
            // log4j.info("Can not find files!"+e.getMessage());
        }
        return fileNames;
    }

    /**
     * 递归删除指定目录中所有文件和子文件夹
     *
     * @param path           某一目录的路径,如"c:\cs"
     * @param ifDeleteFolder boolean值,如果传true,则删除目录下所有文件和文件夹;如果传false,则只删除目录下所有文件,子文件夹将保留
     */
    public static void deleteAllFile(String path, boolean ifDeleteFolder) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        String temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith("\\") || path.endsWith("/"))
                temp = path + tempList[i];
            else
                temp = path + File.separator + tempList[i];
            if ((new File(temp)).isFile()) {
                deleteFile(temp);
            } else if ((new File(temp)).isDirectory() && ifDeleteFolder) {
                deleteAllFile(path + File.separator + tempList[i], ifDeleteFolder);// 先删除文件夹里面的文件
                deleteFolder(path + File.separator + tempList[i]);// 再删除空文件夹
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePathAndName 要删除文件名及路径
     * @return boolean 删除成功返回true,删除失败返回false
     */
    public static boolean deleteFile(String filePathAndName) {
        try {
            File myDelFile = new File(filePathAndName);
            if (myDelFile.exists()) {
                myDelFile.delete();
                // log4j.info("File：" + filePathAndName +
                // " has been deleted!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // log4j.error("Error delete file：" + filePathAndName);
            return false;
        }
        return true;
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件路径
     * @return 文本文件内容
     */
    public static String readFile(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuilder sbf = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr).append('\n');
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 按路径建立文件，如已有相同路径的文件则不建立。
     *
     * @param filePath 要建立文件的路径。
     * @return 表示此文件的File对象。
     * @throws IOException 如路径是目录或建文件时出错抛异常。
     */
    public static File createFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile())
            return file;
        if (filePath.endsWith("/") || filePath.endsWith("\\"))
            try {
                throw new IOException(filePath + " is a directory");
            } catch (IOException e) {
                e.printStackTrace();
            }

        String dirPath = extractDirPath(filePath); // 文件所在目录的路径

        if (dirPath != null) { // 如文件所在目录不存在则先建目录
            createFolder(dirPath);
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // log4j.info("Folder has been created: " + filePath);
        // System.out.println("文件已创建: " + filePath);
        return file;
    }

    /**
     * 从文件路径中提取目录路径，如果文件路径不含目录返回null。
     *
     * @param filePath 文件路径。
     * @return 目录路径，不以'/'或操作系统的文件分隔符结尾。
     */
    public static String extractDirPath(String filePath) {
        int separatePos = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')); // 分隔目录和文件名的位置
        return separatePos == -1 ? null : filePath.substring(0, separatePos);
    }

    /**
     * 新建目录,支持建立多级目录
     *
     * @param folderPath 新建目录的路径字符串
     * @return boolean, 如果目录创建成功返回true, 否则返回false
     */
    public static boolean createFolder(String folderPath) {
        try {
            File myFilePath = new File(folderPath);
            if (!myFilePath.exists()) {
                myFilePath.mkdirs();
                // System.out.println("新建目录为：" + folderPath);
                // log4j.info("Create new folder：" + folderPath);
            } else {
                // System.out.println("目录已经存在: " + folderPath);
                // log4j.info("Folder is existed：" + folderPath);
            }
        } catch (Exception e) {
            // System.out.println("新建目录操作出错");
            e.printStackTrace();
            // log4j.error("Create new folder error: " + folderPath);
            return false;
        }
        return true;
    }

    /**
     * 用所给内容覆盖文件原有内容
     *
     * @param filePath 文件路径
     * @param content  新的文件内容
     */
    public static void writeFile(String filePath, String content) {
        writeFile(filePath, content, false);
    }

    /**
     * 向文件中写入内容
     *
     * @param filePath 文件路径
     * @param content  要写入的内容
     * @param append   是否保留原有内容
     */
    public static void writeFile(String filePath, String content, boolean append) {
        File file = new File(filePath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, append));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
