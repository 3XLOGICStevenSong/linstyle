package com.djb.highway.common.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

public class UploadUtil {

    public static String doUpload(HttpServletRequest request,
            HttpServletResponse response, FormFile formfile, Integer u_id)
            throws ServletException, Exception {

        String webPath = "";

		StringBuffer userPath = new StringBuffer();
		if (u_id != null) {
			userPath.append(u_id.toString());
			userPath.append("/");
		}

		webPath = "userDeployImg/" + userPath.toString() + formfile.getFileName();
		String filepath = request.getSession().getServletContext().getRealPath("/") + webPath;

        File file = new File(filepath);
        if (!file.exists()) {
            File imgPath = file.getParentFile();
            imgPath.mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(filepath); // 创建输出流
        fos.write(formfile.getFileData()); // 写入
        fos.flush();// 释放
        fos.close(); // 关闭

        //生成缩略图
        zoomImage(filepath, 120);

        return webPath;

    }

    /**
     * 
     * 生成缩略图
     * 
     * @param srcImgPath
     * @param smallImgPath
     * @param wh
     * @throws IOException
     */
    public static void zoomImage(String srcImgPath, int wh) throws IOException {

        BufferedImage im = getImage(srcImgPath);

        /* 原始图像的宽度和高度 */
        int width = im.getWidth();
        int height = im.getHeight();

        int toHeight = wh;// 新生成的缩略图像的长度
        int toWidth = (wh * width) / height;// 新生成的缩略图像的宽度
        if (toWidth < wh) {
            toWidth = wh;
            toHeight = (toWidth * height) / width;// 新生成的缩略图像的宽度
        }

        /* 新生成结果图片 */
        BufferedImage result = new BufferedImage(toWidth, toHeight,
                BufferedImage.TYPE_INT_RGB);

        result.getGraphics().drawImage(
                im.getScaledInstance(toWidth, toHeight,
                        java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        cutImage(result, getSmallFileName(srcImgPath), wh);
    }

    /**
     * @param path
     *            要转化的图像的路径
     * @return BufferedImage
     */
    private static BufferedImage getImage(String srcImgPath) throws IOException {
        return javax.imageio.ImageIO.read(new File(srcImgPath));
    }

    /**
     * 
     * @param srcImgPath
     * @param smallImgPath
     * @param wh
     * @throws IOException
     */
    private static void cutImage(BufferedImage bufferedImage,
            String smallImgPath, int wh) throws IOException {

        BufferedImage distin = null;

        int w = wh;
        int h = wh;

        // 返回源图片的宽度。
        int srcW = bufferedImage.getWidth();
        // 返回源图片的高度。
        int srcH = bufferedImage.getHeight();
        int x = 0, y = 0;
        // 使截图区域居中
        x = srcW / 2 - w / 2;
        y = srcH / 2 - h / 2;
        srcW = srcW / 2 + w / 2;
        srcH = srcH / 2 + h / 2;
        // 生成图片
        distin = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = distin.getGraphics();
        g.drawImage(bufferedImage, 0, 0, w, h, x, y, srcW, srcH, null);
        ImageIO.write(distin, getExtension(smallImgPath),
                new File(smallImgPath));
    }

    /**
     * 返回文件的文件后缀名
     * 
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1,
                    fileName.length());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回缩略图全路径
     * 
     * @param fileName
     * @return
     */
    public static String getSmallFileName(String fileName) {
        try {
            return fileName.substring(0, fileName.lastIndexOf(".")) + "_s"
                    + "." + getExtension(fileName);
        } catch (Exception e) {
            return null;
        }
    }

}
