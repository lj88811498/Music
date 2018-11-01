package upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.request.RequestContextHolder;

import cn.owntt.entity.Music;
import cn.owntt.service.IMusicDao;
import cn.owntt.service.impl.MusicDaoImpl;

public class UploadProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 保存文件的目录
	private static String PATH_FOLDER = "/";
	Vector his=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath("/upload");
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String author = request.getParameter("author");
		
		if (author == null || "".equals(author)|| author.trim().length() == 0) {
//       	 model.put("info", "上传者不能为空!");
//       	 return "upload";
			request.getSession().setAttribute("info", "上传者不能为空!");
			//request.getRequestDispatcher("jsp/upload.jsp").forward(request, response);
			return;
        } else {
		IMusicDao musicDao = new MusicDaoImpl();
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(1024 * 1024 * 10);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> list = upload.parseRequest(request);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String prefix = "jsp/mp3/" + date + "/";
			
			for (FileItem item : list) {
				System.out.println(item.getName() + " - " + item.isFormField());
				if (!item.isFormField()) {
					
					//63025278
					//if (item.getSize() / (1024 * 1024) > 11) {
					if (item.getSize() / (1024 * 1024) > 110) {
						System.out.println("文件size ： " + item.getSize() / (1024 * 1024)  + "MB");
						request.getSession().setAttribute("info", "上传失败,单首歌曲不能超过10M大小!");
						return;
					}
					
					String fileName = item.getName();
					String saveName = new Date().getTime()+fileName.substring(fileName.lastIndexOf("."));
					
					String extention = fileName.substring(fileName.lastIndexOf(".") + 1);
					
					if ("、SACD、CDA、APE、FLAC、AIFF、MP3、AAC、WMA、".contains("、" + extention.toUpperCase() + "、" )) {
						
						saveName = System.currentTimeMillis() + "." + extention.toLowerCase();
						
						// 保存后图片的浏览器访问路径
						String filePath= request.getRealPath("/"); 
						
						String picUrl = filePath + prefix + "/" + saveName;
						
						File f = new File(filePath + prefix);
						if (!f.exists()) {
		    				f.mkdir();
		    			}
						
						// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/"+saveName;
						//names.add(picUrl);
						System.out.println(picUrl + " picUrl");
						// 真正写到磁盘上
						item.write(new File(filePath + prefix, saveName)); // 第三方提供的
						
						//add db
						Music m = new Music();
			            m.setAuthor(author);
			            m.setmSize(item.getSize() + "");
			            m.setUrl(prefix + saveName);
			            m.setName(fileName);
			            
			            musicDao.insert(m);
		    			System.out.println(m.toString());
		    			request.getSession().setAttribute("info", "上传成功!");
					} else {
						//model.put("info", "文件类型不正确，请上传格式为音乐格式的文件!");
						//return "upload";
						return;
					}
				}
			}
		
		} catch (Exception e) {
//			model.put("info", "服务器异常，上传失败!");
		}
        }
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
