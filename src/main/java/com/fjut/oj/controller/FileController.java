package com.fjut.oj.controller;

import com.fjut.oj.interceptor.CheckUserIsAdmin;
import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.LocalJudgeFilePO;
import org.codehaus.plexus.util.IOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 文件上传控制器
 * @author axiang [20190702]
 */
@Controller
@CrossOrigin
@RequestMapping("/file")
@ResponseBody
public class FileController {
    @Value("${oj.baseFilePath}")
    private String baseFilePath;

    @Value("${oj.baseJudgeFilePath}")
    private String baseJudgeFilePath;

    @Value("${oj.picPath}")
    private String picPath;

    @Value("${oj.picVerifyPath}")
    private String picVerifyPath;

    @PostMapping("/uploadPic")
    public JsonInfoVO uploadPicture(HttpServletRequest request) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        String username = multipartRequest.getParameter("username");
        String verifyType = multipartRequest.getParameter("verifyType");
        if (null != username) {
            MultipartFile multipartFile = multipartRequest.getFile("file");
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateStr = format.format(today);
            // 保存文件名为 例：admin-1-20190702.jpg
            String imgPath = baseFilePath + picVerifyPath + username + "-" + verifyType + "-" + dateStr + ".jpg";
            System.out.println(imgPath);
            try {
                InputStream is = multipartFile.getInputStream();
                OutputStream out = new FileOutputStream(imgPath);
                IOUtil.copy(is, out);
                System.out.println(multipartFile.getOriginalFilename());
                out.close();
                is.close();
                JsonInfoVO.setSuccess("文件保存成功！");
            } catch (Exception e) {
                JsonInfoVO.setError("文件储存错误！");
            }
        } else {
            JsonInfoVO.setFail("用户名为空！");
        }
        return JsonInfoVO;
    }


    /**
     * FIXME: 由于在前端上使用url上传，无法在请求中带上token鉴权，所以暂时不用拦截器拦截权限问题
     *
     * @param request
     * @return
     */
    @PostMapping("/uploadFile")
    public JsonInfoVO uploadFile(HttpServletRequest request) {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 保存文件名为原名
        String filePath = baseFilePath + multipartFile.getOriginalFilename();
        try {
            InputStream is = multipartFile.getInputStream();
            OutputStream out = new FileOutputStream(filePath);
            IOUtil.copy(is, out);
            out.close();
            is.close();
            JsonInfoVO.setSuccess("文件保存成功！");
        } catch (Exception e) {
            JsonInfoVO.setError("文件储存错误！");
        }
        return JsonInfoVO;
    }

    @CheckUserIsAdmin
    @GetMapping("/getLocalJudgeFile")
    public JsonInfoVO getLocalJudgeFile() {
        JsonInfoVO JsonInfoVO = new JsonInfoVO();
        List<LocalJudgeFilePO> judgeFiles = new ArrayList<>();
        File baseDir = new File(baseJudgeFilePath);
        if (baseDir.isDirectory()) {
            File[] problemDir = baseDir.listFiles();
            for (File problemFile : problemDir) {
                LocalJudgeFilePO judgeFile = new LocalJudgeFilePO();
                String inputFileNamesStr = "";
                String outputFIleNamesStr = "";
                String otherFileNamesStr = "";
                String spjFileNameStr = "";
                judgeFile.setProblemName(problemFile.getName());
                File[] problemDetailFile = problemFile.listFiles();
                for (File f : problemDetailFile) {
                    if (f.isFile() && f.exists()) {
                        if (f.getName().lastIndexOf(".") != -1) {
                            String suffix = f.getName().substring(f.getName().lastIndexOf(".") + 1);
                            String prefix = f.getName().substring(0, f.getName().lastIndexOf("."));
                            if (("in").equals(suffix)) {
                                inputFileNamesStr += (f.getName() + "\n");
                            } else if (("out").equals(suffix)) {
                                outputFIleNamesStr += (f.getName() + "\n");
                            } else if ("spj".equals(prefix)) {
                                spjFileNameStr += (f.getName() + "\n");
                            } else {
                                otherFileNamesStr += (f.getName() + "\n");
                            }
                        } else {
                            otherFileNamesStr += (f.getName() + "\n");
                        }

                    }
                }
                judgeFile.setInputFiles(inputFileNamesStr);
                judgeFile.setOutputFiles(outputFIleNamesStr);
                judgeFile.setSpjFiles(spjFileNameStr);
                judgeFile.setOtherFiles(otherFileNamesStr);
                judgeFiles.add(judgeFile);
            }
            JsonInfoVO.setSuccess();
            JsonInfoVO.addInfo(judgeFiles);
        } else {
            JsonInfoVO.setFail("未找到目录！");
        }

        return JsonInfoVO;
    }

}
