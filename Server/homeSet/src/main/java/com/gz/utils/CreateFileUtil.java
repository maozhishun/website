package com.gz.utils;

import java.io.File;

import com.alibaba.druid.util.StringUtils;

public class CreateFileUtil {

	/**
	 * 新建文件.
	 * 
	 * @param path
	 *            文件路径
	 * @throws Exception
	 */
	public static void createFile(String path) throws Exception {
		if (StringUtils.isEmpty(path)) {
			return;
		}
		try {
			// 获得文件对象
			File f = new File(path);
			if (f.exists()) {
				return;
			}
			// 如果路径不存在,则创建
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
