package com.zzz.demo.controller;

import com.zzz.demo.util.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/qrCode")
@Controller
public class QrCodeController {


    @RequestMapping("/getQrCode")
    public void getQrCode() throws Exception {
        // 存放在二维码中的内容
        String text = "没有内容";
        // 嵌入二维码的图片路径
        String imgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577245738554&di=06674bc731482ac581532f6769e0aee7&imgtype=0&src=http%3A%2F%2Fbbsimg.tianshi2.net%2Fforum%2F201602%2F14%2F203805ftnyqureelqskq1f.jpg";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\Admin\\Desktop\\ooo\\code1.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://blog.csdn.net/ybhuangfugui/article/details/100913641";
        // 嵌入二维码的图片路径
        String imgPath = "C:\\Users\\Admin\\Desktop\\ooo\\29.png";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\Admin\\Desktop\\ooo\\code1.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

}
