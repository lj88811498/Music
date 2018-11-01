package cn.owntt.web.controller;


import cn.owntt.entity.Music;
import cn.owntt.service.IMusicDao;
import cn.owntt.service.impl.MusicDaoImpl;
import cn.owntt.util.Base64;
import cn.owntt.util.CharRegExUtil;
import cn.owntt.util.JsonUtil;
import cn.owntt.util.RecordLogUtil;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Controller
public class IndexController {

    private IMusicDao musicDao = new MusicDaoImpl();

    //http://yyx.guyubao.com/music/index.do
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Object info = session.getAttribute("info");
    	if ("".equals(info) || info == null) {
    		session.setAttribute("info", "等待上传。。。");
    	}
        return "upload";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object index2(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
    					 @RequestParam(value = "name", required = false, defaultValue = "") String name,
    					 HttpServletRequest request) {
    	
        JSONObject js = JsonUtil.createOkJson();
        try {
        	request.setCharacterEncoding("utf-8");
			name = URLDecoder.decode(name, "utf-8");
			if (!"".equals(name)) {
				
				name = CharRegExUtil.replaceSpecStr(name);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        js.put("currentPage", page);
        try {
            List<Music> music = musicDao.selectMusics(page, name);
            js.put("data", music);
            int count = musicDao.count(name);
            js.put("size", count);
            if (count % 10 > 0) {
            	js.put("totalPage", count/10 + 1);
            } else {
            	js.put("totalPage", count/10);
            }
        } catch (Exception e) {

        }
        return js;
    }

    //使用http get方法来调用
    //http://localhost:8080/AnnotationMVC/uploadFile_local.do?author=Monkey&path=F:/gequ/04/
    @ResponseBody
    @RequestMapping(value = "/uploadFile_local", method = RequestMethod.GET)
    public Object upload(String path, String author) {
    	File fpath = new File(path);
    	String[] files = fpath.list();
    	JSONObject js = JsonUtil.createOkJson();
    	if (author == null || "".equals(author)|| author.trim().length() == 0) {
    		js.put("info", "no author") ;
    		return js;
    	}
    	try {
    		
    	
    	for(String str : files) {
    		String url = path + str;
    		File file = new File(url);
    		//设置相对路径
//        String realPath = request.getSession().getServletContext().getRealPath("/upload");
    		String realPath = "d:\\mp4\\";
//        String realPath = "/var/www/html/music/jsp/mp4/";
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String date = sdf.format(new Date());
    		realPath += date;
    		//获取文件的格式
    		String extention = str.substring(str.lastIndexOf(".") + 1);
    		//对格式进行筛选
    		//SACD、CDA、APE、FLAC、AIFF
    		//MP3、AAC、WMA
    		
    		if ("、SACD、CDA、APE、FLAC、AIFF、MP3、AAC、WMA、".contains("、" + extention.toUpperCase() + "、" )) {
    			//servlet中获得项目绝对路径
    			HttpServletRequest r = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    			String filePath= r.getRealPath("/"); 
    			String prefix = "jsp/mp3/" + date + "/";
    			String serverPath = filePath + prefix;
    			//在路径下创建文件夹
//            File f = new File(realPath);
    			File f = new File(serverPath);
    			String fileName = str;
    			fileName = System.currentTimeMillis() + "." + extention.toLowerCase();
//            String uploadPath = realPath + File.separator + fileName;
    			String uploadPath = serverPath + fileName;
    			if (!f.exists()) {
    				f.mkdir();
    			}
    			
    			//文件的传输
    			
    			FileInputStream fis = new FileInputStream(url);
    			BufferedInputStream bis = new BufferedInputStream(fis);
    			DataInputStream dis = new DataInputStream(bis);
    			
    			FileOutputStream fos = new FileOutputStream(uploadPath);
    			//FileOutputStream fos=new FileOutputStream(url);
    			BufferedOutputStream bos = new BufferedOutputStream(fos);
    			DataOutputStream dos = new DataOutputStream(bos);
    			
    			
    			int temp2;
    			long second=new Date().getTime();
    			while((temp2=dis.read())!=-1){
    				dos.write(temp2);
    			}
    			dos.flush();
    			fos.close();
    			bos.close();
    			dos.close();
    			
    			fis.close();
    			bis.close();
    			dis.close();
    			
    			
    			
    			Music m = new Music();
    			m.setAuthor(author);
    			m.setmSize(file.length() + "");
    			m.setUrl(prefix + fileName);
    			m.setName(str);
    			
    			musicDao.insert(m);
    			System.out.println(m.toString());
    			js.put("info", "ok");
    		} else {
    		} 
    	}
    	} catch (Exception e) {
			js.put("info", "error");
		}
    	System.out.println("批量上传完毕！");
        return js;
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Object upload(@RequestParam("file") MultipartFile file, String author, ModelMap model) throws Exception {
        if (author == null || "".equals(author)|| author.trim().length() == 0) {
        	 model.put("info", "上传者不能为空!");
        	 return "upload";
        }
    	//设置相对路径
//        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        String realPath = "d:\\mp4\\";
//        String realPath = "/var/www/html/music/jsp/mp4/";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        realPath += date;
        //获取文件的格式
        String extention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //对格式进行筛选
        //SACD、CDA、APE、FLAC、AIFF
        //MP3、AAC、WMA
        
        if ("、SACD、CDA、APE、FLAC、AIFF、MP3、AAC、WMA、".contains("、" + extention.toUpperCase() + "、" )) {
        	//servlet中获得项目绝对路径
            HttpServletRequest r = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String filePath= r.getRealPath("/"); 
            String prefix = "jsp/mp3/" + date + "/";
            String serverPath = filePath + prefix;
        	//在路径下创建文件夹
//            File f = new File(realPath);
            File f = new File(serverPath);
            String fileName = file.getOriginalFilename();
            fileName = System.currentTimeMillis() + "." + extention.toLowerCase();
//            String uploadPath = realPath + File.separator + fileName;
            String uploadPath = serverPath + File.separator + fileName;
            if (!f.exists()) {
               f.mkdir();
            }
         
            //文件的传输
            file.transferTo(new File(uploadPath));
            long size = file.getSize();
            
            String path = date + "/" + fileName;
            
            Music m = new Music();
            m.setAuthor(author);
            m.setmSize(size + "");
            m.setUrl(prefix + fileName);
            m.setName(file.getOriginalFilename());
            
            musicDao.insert(m);
            System.out.println(m.toString());
            model.put("info", "文件上传成功!");
        } else {
        	model.put("info", "文件类型不正确，请上传格式为音乐格式的文件!");
        }
        return "upload";
    }

    @RequestMapping(value = "/uploadFile2", method = RequestMethod.POST)
    public Object uploadFile2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		Vector his=null;
		//factory.setRepository(new File(TEMP_FOLDER));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> list = upload.parseRequest(request);
			System.out.println("9999999999999999333333 "+list.size());
			
			
			for (FileItem item : list) {
				if (!item.isFormField()) {
					String fileName = item.getName();
					String saveName = new Date().getTime()+fileName.substring(fileName.lastIndexOf("."));
					// 保存后图片的浏览器访问路径
					String picUrl = request.getRealPath("/")+"\\"+saveName;
					// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/"+saveName;
					//names.add(picUrl);
					System.out.println(picUrl+" picUrl");
					// 真正写到磁盘上
					item.write(new File(request.getRealPath("/"), saveName)); // 第三方提供的
					his=(Vector)request.getSession().getAttribute("historyFile");
					if(his==null){
						Vector history=new Vector();
						history.add(picUrl);
						request.getSession().setAttribute("historyFile", history);
					}else{
						his.add(picUrl);
					}
				}
			}
			//request.getSession().setAttribute("historyFile", his);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload2";
	}
    
    
    @RequestMapping("/download")
    public String download(String fileName, HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        try {
        	//http://localhost:8080/AnnotationMVC/download?fileName=A匆匆那年 - 王菲.mp3
            //获取文件名
//            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        	fileName = URLEncoder.encode(fileName, "ISO-8859-1");
        	fileName = URLDecoder.decode(fileName, "UTF-8");
            
        	Music music = musicDao.selectMusicById(Integer.parseInt(fileName));
        	
        	if (music == null) {
        		response.getWriter().append("no this music!");
            	return null;
        	}
//            String serverPath = "D:\\mp3\\";
            String serverPath= request.getRealPath("/");
            String filePath = serverPath + music.getUrl();
            response.setCharacterEncoding("utf-8");
            RecordLogUtil.info(music.getName() + " 进入下载！ip = " + request.getRemoteAddr());
            if(!new File(filePath).exists()) {
            	response.getWriter().append("no this music!");
            	return null;
            }
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="
            		+ new String(music.getName().getBytes("gb2312"), "ISO8859-1"));
            
            
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("multipart/form-data");
            //处理下载弹出框名字的编码问题
            //获取文件的下载路径
//            String path = request.getSession().getServletContext().getRealPath(filePath);
//            System.out.println(path);
//            path = "D:\\mp3\\X8飞曲.mp3";
            //利用输入输出流对文件进行下载
            InputStream inputStream = new FileInputStream(new File(filePath));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            response.getWriter().append("no this music!");
        } catch (IOException e) {
            response.getWriter().append("no this music!");
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }
}