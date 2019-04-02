package com.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.project.common.config.Global;
/**
 * 图片的上传和下载
 * @author 张鹏浩
 *
 */
public class UploadUtil {
    /**
     * 上传图片
     *
     * @return
     */
    public static List<String> uploadImg(HttpServletRequest request,String id) throws IOException {
        System.out.println("================================上传图片开始");
        List<String> list = new ArrayList<String>();
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        //年月日
        String dateNowStr = sdf.format(date);
        String realPath = "";
        File isfile =new File(request.getSession().getServletContext().getRealPath("/"));
        File fileS=isfile.getParentFile().getParentFile().getParentFile();
        realPath= fileS.getPath()+"/image/";
        File fileW =new File(realPath);
        //如果文件夹不存在则创建
        if  (!fileW .exists()  && !fileW .isDirectory())
        {
            System.out.println("//不存在");
            fileW .mkdir();
        } else
        {
            System.out.println("//目录存在");
        }
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                // 定义上传路径
                String path= realPath+"/"+dateNowStr+"/"+id+"/";
                File defile=new File(path);
                defile.getParentFile().mkdirs();
                if(defile.exists()){
                    defile.delete();
                }
                while (iter.hasNext()) {
                    // 取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            System.out.println(myFileName);
                            // 重命名上传后的文件名
                            String fileName = "";
                            fileName = id
                                    + file.getOriginalFilename();
                            fileName = file.getOriginalFilename();
                            File localFile = new File(path,fileName);
//					        if(localFile.exists()) {
//					          // 文件已经存在，输出文件的相关信息
//					            System.out.println(localFile.getAbsolutePath());
//					            System.out.println(localFile.getName());
//					            System.out.println(localFile.length());
//					        } else {
                            //  先创建文件所在的目录
                            try {
                                // 创建新文件
                                localFile.getParentFile().mkdirs();
                                if(localFile.exists()){
                                    localFile.delete();
                                }
                                localFile.createNewFile();
                                file.transferTo(localFile);
                                list.add(path+fileName);
                            } catch (IOException e) {
                                System.out.println("创建新文件时出现了错误。。。");
                                e.printStackTrace();
                            }
//					        }

                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 上传图片
     *
     * @return
     */
    public static List<String> uploadImgCz(HttpServletRequest request,String id) throws IOException {
        System.out.println("================================上传图片开始");
        List<String> list=new  ArrayList<String>();
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        //年月日
        String dateNowStr = sdf.format(date);
        String realPath = "";
        File isfile =new File(request.getSession().getServletContext().getRealPath("/"));
        File fileS=isfile.getParentFile().getParentFile().getParentFile();
        realPath= fileS.getPath()+"/image/";
        File fileW =new File(realPath);
        //如果文件夹不存在则创建
        if  (!fileW .exists()  && !fileW .isDirectory())
        {
            System.out.println("//不存在");
            fileW .mkdir();
        } else
        {
            System.out.println("//目录存在");
        }
        File file=new File(realPath+"/"+dateNowStr+"/"+id+".png");
        file.delete();
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                // 定义上传路径
                String path= realPath+"/"+dateNowStr+"/"+id+"/";
                while (iter.hasNext()) {
                    // 取得上传文件
                    MultipartFile file1 = multiRequest.getFile(iter.next());
                    if (file1 != null) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file1.getOriginalFilename();
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            System.out.println(myFileName);
                            // 重命名上传后的文件名
                            String fileName = "";
                            fileName =  file1.getOriginalFilename();
                            File localFile = new File(path,fileName);
//					        if(localFile.exists()) {
//					          // 文件已经存在，输出文件的相关信息
//					            System.out.println(localFile.getAbsolutePath());
//					            System.out.println(localFile.getName());
//					            System.out.println(localFile.length());
//					        } else {
                            //  先创建文件所在的目录
                            try {
                                // 创建新文件
                                localFile.getParentFile().mkdirs();
                                if(localFile.exists()){
                                    localFile.delete();
                                }
                                localFile.createNewFile();
                                file1.transferTo(localFile);
                                list.add(path+fileName);
                            } catch (IOException e) {
                                System.out.println("创建新文件时出现了错误。。。");
                                e.printStackTrace();
                            }
//					        }

                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static byte[] downloadImg(String path) {
        byte[] buffer = null;
        try {
            File file = new File(path);
            long fileSize = file.length();
            FileInputStream fi;
            fi = new FileInputStream(file);
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
                    && (numRead = fi.read(buffer, offset, buffer.length
                    - offset)) >= 0) {
                offset += numRead;
            }

            fi.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;

    }






    /**
     * 上传头像图片
     *
     * @return
     */
    public static List<String> uploadHeadImg(HttpServletRequest request,String id) {
        System.out.println("------------------------------上传那图片");
        List<String> list = new ArrayList<String>();
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //年月日
        String dateNowStr = sdf.format(date);
//        String realPath ="/usr/corn/images";
        String realPath = Global.getProfile();
        File fileW =new File(realPath);
        //如果文件夹不存在则创建
        if  (!fileW .exists()  && !fileW .isDirectory())
        {
            System.out.println("//不存在");
            fileW .mkdir();
        } else
        {
            System.out.println("//目录存在");
        }
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    int i = 0;
                    // 记录上传过程起始时的时间，用来计算上传时间
                    int pre = (int) System.currentTimeMillis();
                    // 取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            System.out.println(myFileName);
                            // 重命名上传后的文件名
                            String fileName =System.currentTimeMillis()+ file.getOriginalFilename();
                            // 定义上传路径
                            String fileStr=realPath+"/"+dateNowStr+"/"+id+"/";
                            String pathstr = fileStr+fileName;
                            File filef =new File(fileStr);
                            //如果文件夹不存在则创建
                            if  (!filef.exists()  && !filef.isDirectory())
                            {
                                System.out.println("//不存在");
                                filef.mkdirs();
                            } else
                            {
                                System.out.println("//目录存在");
                            }
                            File localFile = new File(pathstr);
                            if(localFile.exists()){
                                localFile.delete();
                            }
                            localFile.createNewFile();
                            file.transferTo(localFile);
                            list.add(Global.getProfile()+dateNowStr+"/"+id+"/"+fileName);
                            //list.add("http://192.168.0.140:8090/image/"+dateNowStr+"/"+id+"/"+fileName);
                            //list.add("http://192.168.0.117:8080/headImg/"+dateNowStr+"/"+id+"/"+fileName);
                            ///
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传了"+list.size()+"张图片");
        return list;
    }
}



