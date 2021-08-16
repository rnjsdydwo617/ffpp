package com.example.test.mvc.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RequestMapping(value = "/ck")
@RestController
public class CkRestAPI {
    @Value("${global.upload.url}")
    private String sysPath;

    @PostMapping(value = "/uploads")
    public void imgUploads(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam MultipartFile upload) throws Exception {
        String uploadPath = null;
        String outUrl = null;
        String uid;
        String enImgFileName;
        OutputStream out = null;
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String fileName = upload.getOriginalFilename();


        int pos = fileName.lastIndexOf( "." );
        String ext = fileName.substring( pos + 1 );
        String _fileName = fileName.substring(0, pos);

        try{
            byte[] bytes = upload.getBytes();
            chkDir(sysPath);
            if(ext.equals("jpeg")){
                enImgFileName = _fileName+".jpg";
                uid = makeUid(enImgFileName);
            } else {
                uid = makeUid(fileName);
            }
            uploadPath = sysPath + uid;//저장경로
            String domain = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
            outUrl = "/uploads/"+uid;
            //http://jaeilkim.cafe24.com/board/receive_img?CKEditor=board_content&CKEditorFuncNum=1&langCode=ko
            out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();

//            printWriter.println("{\"filename\" : " +
//                    "\""+uid+"\", " +
//                    "\"uploaded\" : 1, " +
//                    "\"url\":\""+outUrl+"\", "
//            );

            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + outUrl
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
            printWriter.flush();

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return;
    }
    @PostMapping("/test")
    public void imageUpload(HttpServletRequest req , HttpServletResponse res , MultipartHttpServletRequest mulf ,
                            @RequestParam MultipartFile upload) throws Exception {
        System.out.println("imageUpload 클래스 실행 되었음.");

        UUID uid = UUID.randomUUID(); //랜덤 문자열 생성
        OutputStream out =  null;
        PrintWriter printWriter = null;

        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        try {
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();

            String upPath = sysPath + uid + "_" + fileName;
            chkDir(sysPath);

            out = new FileOutputStream(new File(upPath));
            out.write(bytes);
            out.flush();

            String callback = req.getParameter("CKEditorFuncNum");
            printWriter = res.getWriter();
            String fileUrl = "/body/fashion/write" + uid + "&fileName" + fileName;

            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush();


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null) { out.close(); }
                if(printWriter != null) { printWriter.close(); }

            }catch (IOException e) { e.printStackTrace(); }
        }
        return;
    }
    private String makeUid(String fileName){
        return UUID.randomUUID()+fileName;
    }

    private void chkDir(String path){
        File folder = new File(path);
        if (!folder.exists()){
            try {
                folder.mkdirs();
            } catch (Exception e){
                e.getStackTrace();
            }
        }
    }

    @RequestMapping(value = "/ImgSubmit")
    public void ckSubmit(@RequestParam(value = "fileName")String fileName,
                         HttpServletResponse response)
            throws IOException {

        String path = "/uploads/";
        String sPath = path + fileName;
        File imgFile = new File(sPath);

        if (imgFile.isFile()){
            byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;

            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;

            try {
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();

                while ((readByte = fileInputStream.read(buf))!=-1){
                    outputStream.write(buf,0,readByte);
                }
                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf,0,length);
                out.flush();
            }catch (IOException e){
            }finally {
                outputStream.close();
                fileInputStream.close();
                out.close();
            }

        }
    }
}
