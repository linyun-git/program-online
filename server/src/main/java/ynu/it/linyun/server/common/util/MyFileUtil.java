package ynu.it.linyun.server.common.util;

import java.io.File;
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
}
