package com.zzz.demo.controller;

import com.zzz.demo.entity.User;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Api(value = "文件下载")
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String downLoad(HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        String filename="__UNI__4815010_1129133943.apk";
        String filePath = "F:\\googleDown" ;
        try {
            URL url = new URL("http://127.0.0.1");
            File file = new File("F:\\googleDown\\__UNI__4815010_1129133943.apk");
            FileUtils.copyURLToFile(url,file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*String filename=fileName;
        String filePath = "/usr/softmp" ;*/
        /*File file = new File(filePath + "/" + filename);
        System.out.println(fileName+"**"+file);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            System.out.println("文件不存在");
        }*/
        return null;
    }

    /*@PostMapping("/upLoadHeadImg")
    public RebackMessage upLoadHeadImg(User user,@RequestParam(value = "file") MultipartFile file) {
        try {
            //userService.updateHeadImg(file,user);
            return null;
        } catch (Exception e) {}
        return null;
    }*/

    @PostMapping("/upLoadHeadImg")
    public ReBackMessage upLoadHeadImg(HttpServletRequest request) {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        MultipartRequest req = (MultipartRequest) request;
        MultipartFile file = req.getFile("file");
        userService.updateHeadImg(file,user);
        return null;
    }

    @PostMapping("/talkImg")
    public ReBackMessage talkImg(HttpServletRequest request){
        String toUserId = request.getParameter("toUserId");
        String formUserId = request.getParameter("formUserId");
        MultipartRequest req = (MultipartRequest) request;
        MultipartFile file = req.getFile("file");
        ReBackMessage rebackMessage = userService.talkImg(toUserId,formUserId,file);
        return  rebackMessage;
    }


}
