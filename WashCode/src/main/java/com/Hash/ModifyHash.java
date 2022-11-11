package com.Hash;

/**
 * @author Coverli
 */
public class ModifyHash {
    /**
     * 修改哈希校验值
     * @param filePath 文件路径
     * @param command 删除指令，值为 1 时删除旧文件，其他值为不删除
     * */
    public static void modifyHash (String filePath, int command) {
        String filePrefixName = SplitMerge.getPrefixName(filePath)+"_new";
        String fileSuffixName = SplitMerge.getSuffixName(filePath);
        String fileName = filePrefixName + fileSuffixName;
        long count = SplitMerge.countFiles(filePath);
        SplitMerge.mergeFile(SplitMerge.splitFile(filePath, count),fileName, filePath);
        if (command == 1) {
            SplitMerge.deleteOldFile(filePath);
        }
    }
}
