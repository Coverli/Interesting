## Windows系统

1. 获取文件 MD5 值

```shell
certutil -hashfile filename MD5
```

2. 获取文件 SHA1 值

```shell
certutil -hashfile filename SHA1
```

3. 获取文件 SHA256 值

```shell
certutil -hashfile filename SHA256
```

## Linux系统

1. 获取文件 MD5 值

```shell
$ md5sum filename
```

2. 比对两个文件内容是否完全相同

```shell
$ md5sum file1 file2
```

3. 比对两个文件内容不同的地方

```shell
$ diff file1 file2
```

