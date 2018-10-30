package com.practice.demo.noDuplicate.controller;

import com.practice.demo.noDuplicate.service.NoDuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * @author jerrywang
 * @create 2018/9/7.
 */
@Controller
@RequestMapping("/noDuplicate")
public class NoDuplicateController {

    @Autowired
    private NoDuplicateService noDuplicateService;

    @RequestMapping("")
    public String page() {
        return "noDuplicate/noDuplicate";
    }

    private List<String> list;

    @ResponseBody
    @RequestMapping("/scan")
    public String scan() {
        list = noDuplicateService.getData();

        String path = "/Users/jerrywang/Movies/词以类记/tired.pkg/";
//        String path = "/Volumes/Seagate Backup /";
        File dir = new File(path);
        scaner(dir);

        System.out.println("扫描完成!!");
        return "true";
    }

    public void scaner (File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            System.out.println("into dir : " + dir.getAbsolutePath() + "; file num : " + files.length);
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
                if (f.isFile()) {
                    if (f.getName().indexOf(".") != 0) {
                        if (!list.contains(f.getAbsolutePath())) {
                            Map<String, String> param = new HashMap<>();
                            param.put("fileName", f.getName());
                            param.put("fileMD5", getFileMD5(f));
                            param.put("filePath", f.getAbsolutePath());
                            noDuplicateService.save(param);
                        } else {
                            System.out.println("skip...");
                        }
                    }
                } else {
                    scaner(f);
                }
            }
        }
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest =MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description
     * @author JerryWang
     * @date 2018/9/8 22:47
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Map<String, Object>> queryList() {
        Map<String, String> param = new HashMap<>();
        List<Map<String, Object>> list = noDuplicateService.queryList(param);

        int r = 0;
        int g = 0;
        int b = 0;
        String temp = "";
        Random random = new Random();
        for (Map<String, Object> m : list) {
            if (temp.equals(m.get("FILE_MD5"))) {


            } else {
                temp = m.get("FILE_MD5").toString();
                r = random.nextInt(255);
                g = random.nextInt(255);
                b = random.nextInt(255);
            }
            m.put("r", r);
            m.put("g", g);
            m.put("b", b);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("deleteItem")
    public String deleteItem(String id, String path) {
        // 删除文件
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("删除成功!");
        // 删除数据
        noDuplicateService.deleteItem(Integer.parseInt(id));

        return "true";
    }

    public static void main(String[] args) {
        String path = "/Users/jerrywang/Movies/词以类记/mm/";
        File pathf = new File(path);
        File[] files = pathf.listFiles();
        for (File f : files) {
            String md5 = getFileMD5(f);
            System.out.println(md5);
        }
    }
}
