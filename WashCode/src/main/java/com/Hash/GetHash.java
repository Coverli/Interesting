package com.Hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Coverli
 */
public class GetHash {
    /**
     * 获取文件哈希校验值主要分为三个步骤，
     * 第一步获取文件的 byte 信息，
     * 第二步通过 MessageDigest 类进行哈希校验加密，
     * 第三步转换成 16 进制的哈希校验码值。
     * @param filePath String, 文件路径
     * @param command int, 哈希校验值输出指令，
     *                值为 1 时输出为大写，
     *                值为 0 时输出为小写
     * @return String, 指定文件的哈希校验值
     * */
    public String getHash (String filePath, int command, String hashType) {
        BigInteger bi = null;
        try {
            //这里是分多次将一个文件读入，对于大型文件而言，占用内存比较少。
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance(hashType);
            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (command == 1) {
            return bi.toString(16).toUpperCase();
        } else {
            return bi.toString(16);
        }
    }
}
