package com.example.test.mvc.board;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

//json데이터를 view에서 넘겨줄 일이 있으면 레스트컨트롤러를 사용 해야함.
@RestController
public class BoardREST {

    @Autowired
    private BoardService bSvc;



    @RequestMapping(value = "/body/fashion/write")
    public ModelAndView BoardGet1(){
        ModelAndView mav = new ModelAndView();
        List<BoardVO> BoardGet = bSvc.BoardGet();
        mav.addObject("boardGet" , BoardGet);
        mav.setViewName("hello");
        return mav;
    }




    //이미지 내부폴더로 업로드
    @RequestMapping(value="/imageUpload", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request,
                            HttpServletResponse response,
                            MultipartHttpServletRequest multiFile,
                            @RequestParam MultipartFile upload) throws Exception{

        System.out.println("imageupload 동작");

        UUID uid = UUID.randomUUID();

        OutputStream out = null;
        PrintWriter printWriter = null;

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();

            String path = "resources/Imgupload/";
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
            String fileUrl = "/ckImgSubmit?uid=" + uid + "&fileName=" + fileName;

            printWriter.println("<script>location.href='"+fileUrl+"'</script>");
            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\", \"CKEditorFunNum\" : \""+callback+"\"}");
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
    //업로드 된 이미지 가져오기

}
