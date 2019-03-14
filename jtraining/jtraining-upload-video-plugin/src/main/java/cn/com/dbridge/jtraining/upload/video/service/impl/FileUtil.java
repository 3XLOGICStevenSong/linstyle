package cn.com.dbridge.jtraining.upload.video.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;


/**
 * ファイルについてのツールクラス
 * 
 * @author 陳軍
 * @date 2018/12/24 14:48
 */
public class FileUtil {

	/**
	 * pdfファイルのページ数を取得する
	 * 
	 * @param file
	 *            pdfファイル
	 * @return 総ページ数
	 * @throws IOException
	 */
    public static int getTextPages(File file) throws IOException {
		PDDocument pdfReader = null;
		try {
            pdfReader = PDDocument.load(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
		int pages = pdfReader.getNumberOfPages();
		return pages;
	}

    /**
     * 
     * @Title: ReadVideoTime
     * @Description: 获取视频时长
     * @param FileUrl
     * @return
     */
    public static Long getVideoTime(File source) {
        Long ls = null;
        try {
            MultimediaObject instance = new MultimediaObject(source);
            MultimediaInfo result = instance.getInfo();
            ls = result.getDuration() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
}
