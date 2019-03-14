package cn.com.dbridge.lifecare.framework.util;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

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
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		int pages = pdfReader.getNumberOfPages();
		System.out.println("pdf总页数为：" + pages);
		return pages;
	}
	/**
	 * ビデオファイルの総時間を取得する
	 * 
	 * @param file
	 *            ビデオファイル
	 * @return ビデオの総時間(mm:ss)
	 * @throws Exception
	 */
	public static String getVideoTime(File file) throws Exception {
		Encoder encoder = new Encoder();
		MultimediaInfo m = null;
		try {
			m = encoder.getInfo(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		long ls = m.getDuration();
		int minutes = (int) (ls / 60000);
		int seconds = (int) ((ls % 60000) / 1000);
		return minutes + ":" + seconds;
	}

}
