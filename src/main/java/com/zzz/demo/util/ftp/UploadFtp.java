
package com.zzz.demo.util.ftp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class UploadFtp implements Serializable {

        private static final Logger log = LoggerFactory.getLogger(UploadFtp.class);

        private String ftpPath = "/";
        private String encoding = "utf-8";
        private String ip = "vmhost";
        private String username = "zzz";
        private String passwd = "zzz";
        private Integer port = 21;

        private final String spt = "/";

        public String storeByExt(String path, String ext, InputStream in) {
                String filename =path +ext;
                store(filename, in);
                return filename;
        }

        public String storeByExtWithTime(String path, String ext,String time,InputStream in) {
                String filename = path + ext;
                store(filename, in);
                return filename;
        }


        public void storeByExt(String path, InputStream in) throws IOException {
                store(path, in);
        }


        public String storeByFilename(String filename, InputStream in) throws IOException {
                store(filename, in);
                return filename;
        }


        public File retrieve(String name, String fileName) throws IOException {
                String path = System.getProperty("java.io.tmpdir");
                if(StringUtils.isNotBlank(fileName)){
                        File file = new File(path, fileName);
                        file = getUniqueFile(file);
                        FTPClient ftp = getClient();
                        if (ftp != null) {
                                OutputStream output = new FileOutputStream(file);
                                ftp.retrieveFile(ftpPath + name, output);
                                output.close();
                                ftp.logout();
                                ftp.disconnect();
                        }
                        return file;
                }
                return null;
        }

        /**
         * 删除文件
         *
         * @param fileName
         *                文件路径
         * @Title: deleteFile
         * @return: boolean
         */

        public boolean deleteFile(String fileName) {
                boolean flag = true;
                try {
                        FTPClient ftp = getClient();
                        if (ftp != null && StringUtils.isNotBlank(fileName)) {
                                flag = ftp.deleteFile(fileName);
                        }
                } catch (IOException e) {
                        flag = false;
                        log.error("ftp delete error", e);
                }
                return flag;
        }

        /**
         * 存储文件
         *
         * @param name
         *                文件名
         * @param file
         *                文件
         * @throws IOException
         *                 IOException
         * @Title: restore
         * @return: boolean
         */

        public boolean restore(String name, File file) throws IOException {
                store(name, FileUtils.openInputStream(file));
                file.deleteOnExit();
                return true;
        }


        private int store(String remote, InputStream in) {
                try {
                        //获取FTP客户端
                        FTPClient ftp = getClient();
                        if (ftp != null) {
                                //getFtpPath() +
                                String filename =  remote;
                                System.err.println("存储FTP前拼接前名字"+filename);
                                //String name = FilenameUtils.getName(filename);
                                String name = FilenameUtils.getName(filename);
                                System.err.println("存储FTP前拼接后名字"+name);
                                String path = FilenameUtils.getFullPath(filename);
                                if (!ftp.changeWorkingDirectory(path)) {
                                        String[] ps = StringUtils.split(path, spt);
                                        String p = spt;
                                        ftp.changeWorkingDirectory(p);
                                        for (String s : ps) {
                                                p += s + spt;
                                                if (!ftp.changeWorkingDirectory(p)) {
                                                        ftp.makeDirectory(s);
                                                        ftp.changeWorkingDirectory(p);
                                                }
                                        }
                                }
                                ftp.storeFile(name, in);
                                ftp.logout();
                                ftp.disconnect();
                        }
                        return 0;
                } catch (SocketException e) {
                        log.error("ftp store error", e);
                        return 3;
                } catch (IOException e) {
                        log.error("ftp store error", e);
                        return 4;
                } finally {
                        try {
                                if (null != in) {
                                        in.close();
                                }
                        } catch (IOException e) {
                                log.error(e.getMessage());
                        }
                }
        }

        /**
         * 存储文件夹
         *
         * @param folder
         *                文件夹
         * @param rootPath
         *                根路径
         * @Title: storeByFloder
         * @return: int
         */
        public int storeByFolder(String folder, String rootPath) {
                String fileAbsolutePath;
                String fileRelativePath;
                try {
                        FTPClient ftp = getClient();
                        if (ftp != null) {
                                List<File> files = getFiles(new File(folder));
                                for (File file : files) {
                                        String filename = ftpPath + file.getName();
                                        String name = FilenameUtils.getName(filename);
                                        String path = FilenameUtils.getFullPath(filename);
                                        fileAbsolutePath = file.getAbsolutePath();
                                        fileRelativePath = fileAbsolutePath.substring(rootPath.length() + 1,
                                                        fileAbsolutePath.indexOf(name));
                                        path += fileRelativePath.replace("\\", spt);
                                        if (!ftp.changeWorkingDirectory(path)) {
                                                String[] ps = StringUtils.split(path, spt);
                                                String p = spt;
                                                ftp.changeWorkingDirectory(p);
                                                for (String s : ps) {
                                                        p += s + spt;
                                                        if (!ftp.changeWorkingDirectory(p)) {
                                                                ftp.makeDirectory(s);
                                                                ftp.changeWorkingDirectory(p);
                                                        }
                                                }
                                        }
                                        // 解决中文乱码问题
                                        name = new String(name.getBytes(encoding), "iso-8859-1");
                                        if (!file.isFile()) {
                                                ftp.makeDirectory(name);
                                        } else {
                                                InputStream in = new FileInputStream(file.getAbsolutePath());
                                                ftp.storeFile(name, in);
                                                in.close();
                                        }
                                }
                                ftp.logout();
                                ftp.disconnect();
                        }
                        return 0;
                } catch (SocketException e) {
                        log.error("ftp store error", e);
                        return 3;
                } catch (IOException e) {
                        log.error("ftp store error", e);
                        return 4;
                }
        }


        private FTPClient getClient() throws SocketException, IOException {
                FTPClient ftp = new FTPClient();
                ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
                ftp.setDefaultPort(port);
                ftp.connect(ip);
                int reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                        log.error("FTP server refused connection: {}", ip);
                        ftp.disconnect();
                        return null;
                }
//                String password = DesUtil.decrypt(passwd, ContentSecurityConstants.DES_KEY,
//                                ContentSecurityConstants.DES_IV);
                if (!ftp.login(username, passwd)) {
                        log.error("FTP server refused login: {}, user: {}", ip, username);
                        ftp.logout();
                        ftp.disconnect();
                        return null;
                }
                ftp.setControlEncoding(encoding);
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                ftp.enterLocalPassiveMode();
                return ftp;
        }

        private static final long serialVersionUID = 1L;


	/**
	 * 复制文件夹以及文件夹下的全部内容
	 *
	 * @param sourceDir 源文件夹
	 * @param targetDir 目标文件
	 * @throws IOException 异常
	 */
	public void copyDirectory(String sourceDir, String targetDir) throws IOException {
		FTPClient ftpClient = getClient();
		// 新建目标目录
		if (!existDirectory(targetDir)) {
			createDirectory(targetDir);
		}
		// 获取源文件夹当前下的文件或目录
		// File[] file = (new File(sourceDir)).listFiles();
		FTPFile[] ftpFiles = ftpClient.listFiles(sourceDir);
		for (int i = 0; i < ftpFiles.length; i++) {
			if (ftpFiles[i].isFile()) {
				copyFile(ftpFiles[i].getName(), sourceDir, targetDir);
			} else if (ftpFiles[i].isDirectory()) {
				copyDirectory(sourceDir + File.separatorChar + ftpFiles[i].getName(),
						targetDir + File.separatorChar + ftpFiles[i].getName());
			}
		}
	}

	/**
	 * 判断文件夹是否存在
	 *
	 * @Title: existDirectory
	 * @param targetDir 目标文件夹
	 * @throws IOException 异常
	 * @throws SocketException 异常
	 */
	private boolean existDirectory(String targetDir) throws SocketException, IOException {
		FTPClient ftpClient = getClient();
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(targetDir);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 创建文件夹
	 *
	 * @Title: createDirectory
	 * @param targetDir 目标文件夹
	 * @throws IOException 异常
	 */
	private void createDirectory(String targetDir) throws IOException {
		FTPClient ftpClient = getClient();
		ftpClient.makeDirectory(targetDir);
	}

	/**
	 * 复制文件.
	 * @param sourceFileName 源文件名称
	 * @param sourceDir 源文件夹
	 * @param targetDir 目标文件夹
	 * @throws IOException 异常
	 */
	public void copyFile(String sourceFileName, String sourceDir, String targetDir) throws IOException {
		FTPClient ftpClient = getClient();
		ByteArrayInputStream in = null;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			if (!existDirectory(targetDir)) {
				createDirectory(targetDir);
			}
			ftpClient.setBufferSize(1024 * 2);
			// 变更工作路径
			ftpClient.changeWorkingDirectory(sourceDir);
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 将文件读到内存中
			ftpClient.retrieveFile(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"), fos);
			in = new ByteArrayInputStream(fos.toByteArray());
			if (in != null) {
				ftpClient.changeWorkingDirectory(targetDir);
				ftpClient.storeFile(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"), in);
			}
		} finally {
			// 关闭流
			if (in != null) {
				in.close();
			}
			if (fos != null) {
				fos.close();
			}
		}


	}

    public static List<File> getFiles(File folder) {
        List<File> files = new ArrayList();
        iterateFolder(folder, files);
        return files;
    }

    private static void iterateFolder(File folder, List<File> files) {
        File[] flist = folder.listFiles();
        files.add(folder);
        if (flist != null && flist.length != 0) {
            File[] var3 = flist;
            int var4 = flist.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File f = var3[var5];
                if (f.isDirectory()) {
                    iterateFolder(f, files);
                } else {
                    files.add(f);
                }
            }
        } else {
            files.add(folder);
        }

    }

    public static File getUniqueFile(final File file) {
        if (!file.exists()) {
            return file;
        }

        File tmpFile = new File(file.getAbsolutePath());
        File parentDir = tmpFile.getParentFile();
        int count = 1;
        String extension = FilenameUtils.getExtension(tmpFile.getName());
        String baseName = FilenameUtils.getBaseName(tmpFile.getName());
        do {
            tmpFile = new File(parentDir, baseName + "(" + count++ + ")." + extension);
        } while (tmpFile.exists());
        return tmpFile;
    }
}
