package com.djb.highway.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.djb.highway.framework.log.Logger;
import com.djb.highway.road.dao.ICameraDao;
import com.djb.highway.road.entity.CameraEntity;

/**
 * Servlet implementation class CameraServlet
 */
@WebServlet("/CameraServlet")
public class CameraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Logger
     */
    protected final Logger logger = new Logger(this.getClass());

    // @Autowired
    // @Qualifier("cameraDao")
    private ICameraDao cameraDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CameraServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("doGet", ">>" + "doGet");
        // test
        long lngTime = new Date().getTime();
        showImage(request, response);
        logger.debug("doGet", "doGet" + "<<");
        // test
        lngTime = (new Date().getTime()) - lngTime;
        logger.error("doGet", "<<[End:doGet] ClassName:" + this.getClass().getName() + "经过时间[" + lngTime + "]毫秒");
        // logger.error("doGet", "<<[End:doGet] ClassName:"
        // + this.getClass().getName() + " new Date().getTime()[" + (new
        // Date().getTime()) + "]毫秒");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void showImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strCId = request.getParameter("c_id");
        String pic_path = request.getParameter("path");

        // test
        if (!CommonUtil.isNullOrBlank(pic_path) && pic_path.indexOf("210.76.163.58") != -1) {
            // request.getRequestDispatcher(pic_path).forward(request,
            // response);
            response.sendRedirect(pic_path);
            return;
        }

        // 读取方式
        // response.setContentType("image/jpeg");
        try {
            if (!CommonUtil.isNullOrBlank(pic_path)) {
                doWrite(pic_path, response);
            } else if (!CommonUtil.isNullOrBlank(strCId)) {

                if (cameraDao == null) {
                    cameraDao = (ICameraDao) ResourceLocator.getBean("cameraDao", this.getServletContext());
                }
                Integer c_id = CommonUtil.toInt(request.getParameter("c_id"), 0);
                CameraEntity paramCameraEntity = new CameraEntity();
                paramCameraEntity.setC_id(c_id);
                CameraEntity resultCameraEntity = (CameraEntity) cameraDao.getObject(paramCameraEntity);

                pic_path = resultCameraEntity.getPic_path();
                if (resultCameraEntity != null) {
                    if (!CommonUtil.isNullOrBlank(pic_path) && pic_path.indexOf("210.76.163.58") != -1) {
                        response.sendRedirect(pic_path);
                        return;
                    }
                    doWrite(pic_path, response);
                }
            }

        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

    private static void doWrite(String picUrl, HttpServletResponse response) throws Exception {
        if (picUrl == null) {
            return;
        }
        try {
            // 读取方式
            response.setContentType("image/jpeg");
            
            // 构造URL
            URL url = new URL(picUrl);
            // 打开URL连接
            URLConnection con = url.openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            // 得到URL的输入流
            InputStream input = con.getInputStream();

            // 创建缓冲区
            byte[] buffer = new byte[1024 * 2];
            // 读取到的数据长度
            int len;
            // 响应输出流
            ServletOutputStream out = response.getOutputStream();
            while ((len = input.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            input.close();
        } catch (SocketTimeoutException e) {
            // e.printStackTrace();
            throw e;
        } catch (MalformedURLException e) {
            // e.printStackTrace();
            throw e;
        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            throw e;
        } catch (IOException e) {
            // e.printStackTrace();
            throw e;
        }

    }
}
