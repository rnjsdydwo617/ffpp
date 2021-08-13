package com.example.test.mvc.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;
import java.util.logging.Logger;


@Controller
public class BoardController {
    @Autowired
    private BoardService bVc;

    @PostMapping(value = "/BoardRegister")
    public void board_write(BoardVO vo , HttpServletRequest req , HttpServletResponse res) throws IOException {
        int result = bVc.BoardRegister(vo);

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        if(result == 0){
            out.println("<script>alert('게시글 등록에 실패하였습니다.'); location.href='/body/fashion/write'; </script>");
        } else {
            out.println("<script>alert('게시글 등록에 성공 하였습니다.'); location.href='/'; </script>");
            out.flush();
        }
    }

    @RequestMapping(value="/mine/imageUpload.do", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request,
                            HttpServletResponse response,
                            MultipartHttpServletRequest multiFile,
                            @RequestParam MultipartFile upload) throws Exception{
        UUID uid = UUID.randomUUID();

        OutputStream out = null;
        PrintWriter printWriter = null;

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();

            String path = "/Imgupload"+"ckImage/";
            String ckUploadPath = path + uid + "_" +fileName;
            File folder = new File(path);

            if (!folder.exists()){
                try {
                    folder.mkdirs();
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush();

            String callback = request.getParameter("CKEditorFunNum");
            printWriter = response.getWriter();
            String fileUrl = "/mine/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName;

            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (out != null){out.close();}
                if (printWriter != null){printWriter.close();}
            }catch (IOException e ){e.printStackTrace();}
        }
        return;
    }
    @RequestMapping(value = "mine/ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value = "uid")String uid,
                         @RequestParam(value = "fileName")String fileName,
                         HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        String path = "/Imgupload"+"ckImage/";
        String sPath = path + uid + "_" + fileName;
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


