package com.Hash;

import java.io.*;

/**
 * @author Coverli
 */
public class SplitMerge {
    /**
     * 分割文件
     * @param filePath 文件路径
     * @param partSize 每部分文件大小
     * @return 分割后的文件名列表
     * @throws Exception FileNotFoundException, IOException
     * */
    public static String[] splitFile (String filePath, long partSize) {
        DataInputStream dateIn = null;
        String[] fileNames = null;
        try {
            File file = new File(filePath);
            fileNames = getPartitionFileNames(filePath, partSize);
            dateIn = new DataInputStream(new FileInputStream(file));
            byte[] buff = new byte[1024];
            DataOutputStream dataOut = null;
            for (String fileName : fileNames) {
                dataOut = new DataOutputStream(new FileOutputStream(new File(fileName)));
                long size = partSize;
                while (size > 0) {
                    if (dateIn.read(buff) != -1) {
                        dataOut.write(buff, 0, buff.length);
                    }
                    size = size- buff.length;
                }
                dataOut.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dateIn != null) {
                    dateIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }

    /**
     * 合并文件
     * @param fileNames 文件名列表
     * @param newFile 新文件名
     * @throws Exception FileNotFoundException, IOException
     * */
    public static void mergeFile (String[] fileNames, String newFile, String filePath) {
        DataOutputStream dateOut = null;
        try {
            dateOut = new DataOutputStream(new FileOutputStream(new File(newFile)));
            for (String fileName : fileNames) {
                FileInputStream fileIn = new FileInputStream(new File(fileName));
                byte[] buff = new byte[1024];
                while ((fileIn.read(buff)) != -1) {
                    dateOut.write(buff, 0, buff.length);
                }
                fileIn.close();
                deleteOldFile(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dateOut != null) {
                    dateOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        modifyFile(newFile,filePath);
    }

    /**
     * 计算分割文件后的文件名列表
     * @param filePath 文件路径
     * @param partSize 每部分文件大小
     * @return 文件名列表
     * */
    public static String[] getPartitionFileNames(String filePath, long partSize) {
        File file = new File(filePath);
        long size = file.length();
        String prefixName = file.getName();
        double num = (size/(double)partSize);
        num = Math.ceil(num);
        String[] fileNames = new String[(int)num];
        for (int i = 0; i < num; i++) {
            fileNames[i] = getPrefixName(filePath) + "_" + i + getSuffixName(filePath);
        }
        return fileNames;
    }

    /**
     * 计算文件分割数
     * */
    public static long countFiles (String filepath) {
        long fileSize = new File(filepath).length();
        long count = fileSize/8;
        return count;
    }

    /**
     * 获取文件名，去除后缀名
     * @param filePath 文件路径
     * @return 文件名（无后缀名）
     * */
    public static String getPrefixName(String filePath) {
        String name = new File(filePath).getName();
        int suffixNameLength = getSuffixName(filePath).length();
        return name.substring(0,(name.length()-suffixNameLength));
    }

    /**
     * 获取文件名后缀名
     * @param filePath 文件路径
     * @return 文件后缀名
     * */
    public static String getSuffixName (String filePath) {
        String name = new File(filePath).getName();
        return name.substring(name.lastIndexOf("."),name.length());
    }

    /**
     * 删除旧文件
     * @param filePath 文件路径
     * */
    public static void deleteOldFile (String filePath) {
        new File(filePath).delete();
    }

    /**
     * 文件移动
     * @param fileName 源文件名
     * @param newPath 移动后路径
     * */
    public static void modifyFile (String fileName, String newPath) {
        File metaFile1 = new File(fileName);
        File metaFile = new File(metaFile1.getAbsolutePath());
        int len1 = newPath.substring(newPath.lastIndexOf("\\")).length();
        int len2 = newPath.length()-len1;
        newPath = newPath.substring(0, len2) + "\\" + fileName;
        File newFile = new File(newPath);
        metaFile.renameTo(newFile);
    }
}
