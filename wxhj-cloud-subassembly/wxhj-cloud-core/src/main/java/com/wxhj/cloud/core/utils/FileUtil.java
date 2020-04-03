package com.wxhj.cloud.core.utils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	private static final int BUFFER_SIZE = 2 * 1024;

	public static String generateFile(String suffix)
	{
		return UUID.randomUUID().toString().concat(".").concat(suffix);
	}

	/**
	 * 将字节数组信息写入图片文件
	 *
	 * @param data		字节数组
	 * @param fileName	图片文件的名称
	 */
	public static void byte2image(byte[] data, String fileName) {
		if (data.length < 3 || fileName == null || "".equals(fileName)) {
			return;
		}
		try {
			File file = createFile(fileName);
			FileImageOutputStream imageOutput = new FileImageOutputStream(file);
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("Make Picture success,Please find image in " + file.getPath());
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}

	/**
	 * 将内存中的数据写入文件
	 *
	 * @param fileName	目的文件
	 * @param str		要写入的字符串数据
	 * @param append 	是否追加，默认为false，直接覆盖
	 * @return
	 * @throws IOException
	 */
	public static File writeToFile(String fileName, String str, boolean append) throws IOException {
		File file = createFile(fileName);
		FileOutputStream fos = null;
		try {

			fos = new FileOutputStream(file, append);
			FileChannel channel = fos.getChannel();
			ByteBuffer src = Charset.forName("utf8").encode(str);
			// 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
			System.out.println("初始化容量和limit：" + src.capacity() + ","
					+ src.limit());
			int length = 0;

			while ((length = channel.write(src)) != 0) {
				/*
				 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
				 */
				System.out.println("写入长度:" + length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 去掉字符串中的换行符
	 * @param str
	 * @return
	 */
	public static String replaceLineFeed(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 将内存中的数据写入文件
	 *
	 * @param fileName	目的文件
	 * @param str		要写入的字符串数据
	 * @return
	 * @throws IOException
	 */
	public static File writeToFile(String fileName, String str) throws IOException {
		return writeToFile(fileName, str, false);
	}

	/**
	 * 压缩成ZIP 方法1
	 *
	 * @param srcDir           压缩文件夹路径
	 * @param dest             压缩文件输出路径
	 * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
	 *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */
	public static void toZip(String srcDir, String dest, boolean KeepDirStructure)
			throws RuntimeException {
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(dest));
			zos = new ZipOutputStream(fos);
			File sourceFile = new File(srcDir);
			compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
			long end = System.currentTimeMillis();
			System.out.println("压缩完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils", e);
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
	 * 压缩成ZIP 方法2
	 *
	 * @param srcFiles 需要压缩的文件列表
	 * @param out      压缩文件输出流
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */
	public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(out);
			for (File srcFile : srcFiles) {
				byte[] buf = new byte[BUFFER_SIZE];
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				zos.closeEntry();
				in.close();
			}
			long end = System.currentTimeMillis();
			System.out.println("压缩完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils", e);
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
	 * 递归压缩方法
	 *
	 * @param sourceFile       源文件
	 * @param zos              zip输出流
	 * @param name             压缩后的名称
	 * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
	 *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws Exception
	 */
	private static void compress(File sourceFile, ZipOutputStream zos, String name,
								 boolean KeepDirStructure) throws Exception {
		byte[] buf = new byte[BUFFER_SIZE];
		if (sourceFile.isFile()) {
			// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			zos.putNextEntry(new ZipEntry(name));
			// copy文件到zip输出流中
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1) {
				zos.write(buf, 0, len);
			}
			// Complete the entry
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();
			if (listFiles == null || listFiles.length == 0) {
				// 需要保留原来的文件结构时,需要对空文件夹进行处理
				if (KeepDirStructure) {
					// 空文件夹的处理
					zos.putNextEntry(new ZipEntry(name + "/"));
					// 没有文件，不需要文件的copy
					zos.closeEntry();
				}

			} else {
				for (File file : listFiles) {
					// 判断是否需要保留原来的文件结构
					if (KeepDirStructure) {
						// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
						// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
						compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
					} else {
						compress(file, zos, file.getName(), KeepDirStructure);
					}

				}
			}
		}
	}

	/**
	 * 根据路径创建文件，如果不存在目录，则创建
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String filePath) throws IOException {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 删除文件
	 * 如果是文件夹，则删除子文件
	 *
	 * @param file
	 */
	public static void deleteFile(File file) {
		// 判断文件不为null或文件目录存在
		if (file == null || !file.exists()) {
			System.out.println("文件删除失败,请检查文件路径是否正确");
			return;
		}

		if (file.isFile()) {
			// 如果是文件则直接删除
			file.delete();
			return;
		}

		// 取得这个目录下的所有子文件对象
		File[] files = file.listFiles();
		// 遍历该目录下的文件对象
		for (File f : files) {
			// 打印文件名
			String name = file.getName();
			System.out.println(name);
			// 判断子目录是否存在子目录,如果是文件则删除
			if (f.isDirectory()) {
				deleteFile(f);
			} else {
				f.delete();
			}
		}
		// 删除空文件夹  for循环已经把上一层节点的目录清空。
		file.delete();
	}
}
