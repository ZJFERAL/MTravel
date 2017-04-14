package com.zjf.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SDCardUtils {

    /**
     * 获取sdcard挂载状态
     * 
     * @return 是否挂载
     */
    public static boolean isMount() {

	return Environment.getExternalStorageState().equals(
		Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取sdcard所在的路径
     */
    public static String getSDCardPath() {

	if (isMount()) {
	    return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	return "";
    }

    /**
     * 获取文件的尺寸大小
     * 
     * @param file
     *            要获取大小的文件
     * @return 返回大小 单位是 MB
     */
    public static long getFileSize(File file) {

	// 判断当前使用此应用的手机的系统版本
	boolean is = Build.VERSION.SDK_INT > 18;

	if (is) {
	    return file.getTotalSpace() / 1024 / 1024; // 直接获取大小
	} else {
	    // 获取对应路径文件的信息
	    StatFs statFs = new StatFs(file.getAbsolutePath());
	    // statFs.getBlockCount(); //获取有多个块
	    // statFs.getBlockSize(); //每块的大小
	    return statFs.getBlockCount() * statFs.getBlockSize() / 1024 / 1024;
	}
    }

    /**
     * 获取可用的大小
     * 
     * @param file
     * @return
     */
    public static long getFileFreeSize(File file) {

	boolean is = Build.VERSION.SDK_INT > 18;
	if (is) {
	    return file.getFreeSpace() / 1024 / 1024;
	} else {
	    StatFs statFs = new StatFs(file.getAbsolutePath());
	    return statFs.getFreeBlocks() * statFs.getBlockSize() / 1024 / 1024;
	}
    }

    /**
     * 给你一个 byte[]数据 保存到sdcard指定文件夹中
     * 
     * @param data
     *            要保存的数据
     * @param dir
     *            目标文件夹
     * @param fileName
     *            目标文件名
     * @return 是否保存成功
     */
    public static boolean saveByteArrayFileIntoExternalStorage(byte[] data,
	    String dir, String fileName) {
	File targetFile = getFileByDirAndFileName(dir, fileName);
	return saveData(data, targetFile);
    }

    /**
     * 读取sdcard指定文件夹中的文件 返回byte[]
     * 
     * @param dir
     *            目标文件夹
     * @param fileName
     *            目标文件名
     * @return 目标文件的byte[]
     */
    public static byte[] loadByteArrayFileFromExternalStorage(String dir,
	    String fileName) {
	File targetFile = getFileByDirAndFileName(dir, fileName);
	return loadData(targetFile);
    }

    /**
     * 将一个byte[]保存到sdcard 指定类型的公有目录文件夹中
     * 
     * @param data
     *            要保存的数据
     * @param type
     *            文件夹类型
     * @param fileName
     *            文件名
     * @return 是否保存成功
     */
    public static boolean saveByteArrayFileToExternalStoragePublicDir(
	    byte[] data, String type, String fileName) {

	File targetFile = getPublicFileByTypeAndFileName(type, fileName);
	return saveData(data, targetFile);

    }

    /**
     * 读取sdcard指定类型公有文件夹中的文件
     * 
     * @param type
     *            目标文件的文件夹类型
     * @param fileName
     *            文件名
     * @return 返回byte[]
     */
    public static byte[] loadByteArrayFileFromExternalStoragePublicDir(
	    String type, String fileName) {
	File targetFile = getPublicFileByTypeAndFileName(type, fileName);
	return loadData(targetFile);
    }

    /**
     * 将一个 byte[]数据 保存到sdcard 指定类型的私有目录文件夹中
     * 
     * @param data
     *            要保存的数据
     * @param type
     *            外置存储中私有文件夹类型
     * @param fileName
     *            目标文件名
     * @param context
     * @return 是否存储成功
     */
    public static boolean saveByteArrayFileToExternalFiles(byte[] data,
	    String type, String fileName, Context context) {
	File targetFile = getPrivateFileByTypeAndFileName(type, fileName,
		context);
	return saveData(data, targetFile);

    }

    /**
     * 读取sdcard指定类型私有文件夹中的文件
     * 
     * @param type
     *            外置存储中私有文件夹类型
     * @param fileName
     *            目标文件名
     * @param context
     * @return 返回byte[]
     */
    public static byte[] loadByteArrayFileToExternalFiles(String type,
	    String fileName, Context context) {
	File targetFile = getPrivateFileByTypeAndFileName(type, fileName,
		context);
	return loadData(targetFile);
    }

    /**
     * 根据外置私有文件夹类型及文件名获取一个File
     * 
     * @param type
     *            外置私有文件夹类型
     * @param fileName
     *            目标文件名
     * @param context
     * @return 目标文件
     */
    public static File getPrivateFileByTypeAndFileName(String type,
	    String fileName, Context context) {
	File targetDir = context.getExternalFilesDir(type);
	File targetFile = new File(targetDir, fileName);
	return targetFile;
    }

    /**
     * 根据公共文件夹类型及文件名获取一个File
     * 
     * @param type
     *            目标文件夹
     * @param fileName
     *            目标文件名
     * @return 目标文件
     */
    public static File getPublicFileByTypeAndFileName(String type,
	    String fileName) {
	File targetDir = Environment.getExternalStoragePublicDirectory(type);
	File targetFile = new File(targetDir, fileName);
	return targetFile;
    }

    /**
     * 根据文件夹及文件名获取一个File
     * 
     * @param dir
     *            目标文件夹
     * @param fileName
     *            目标文件名
     * @return 目标文件
     */
    public static File getFileByDirAndFileName(String dir, String fileName) {
	File targetDir = new File(getSDCardPath(), dir);
	if (!targetDir.exists()) {
	    targetDir.mkdirs();
	}
	File targetFile = new File(targetDir, fileName);
	return targetFile;
    }

    /**
     * 存数据
     * 
     * @param data
     *            要存的数据
     * @param targetFile
     *            目标文件夹
     * @return 是否成功
     */
    public static boolean saveData(byte[] data, File targetFile) {
	if (isMount()) {
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    try {
		fos = new FileOutputStream(targetFile);
		bos = new BufferedOutputStream(fos);
		bos.write(data);
		bos.flush();
		return true;
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		try {
		    if (bos != null) {
			bos.close();
		    }
		    if (fos != null) {
			fos.close();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }
	    return false;
	}
	return false;
    }

    /**
     * 取数据
     * 
     * @param targetFile
     *            目标文件
     * @return 返回的byte数组
     */
    public static byte[] loadData(File targetFile) {
	if (isMount()) {
	    FileInputStream fis = null;
	    ByteArrayOutputStream baos = null;
	    try {
		fis = new FileInputStream(targetFile);
		baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[8 * 1024];
		while ((len = fis.read(buffer)) != -1) {
		    baos.write(buffer, 0, len);
		    baos.flush();
		}
		return baos.toByteArray();
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		try {
		    if (fis != null) {
			fis.close();
		    }
		    if (baos != null) {
			baos.close();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }
	    return null;
	}
	return null;
    }
}
