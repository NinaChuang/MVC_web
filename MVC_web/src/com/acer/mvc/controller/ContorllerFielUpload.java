package com.acer.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

@WebServlet("/contorllerFielUpload/")
@MultipartConfig
public class ContorllerFielUpload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Gson gson;
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)

	throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String contentType = request.getContentType();
         System.out.println("isMultipart."+contentType);
		// process only if it is multipart content
		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String path = this.getServletContext().getRealPath("/pics"); 
						File directory = new File(path);  
			            if (!directory.exists()) {  
			                FileUtils.forceMkdir(directory);  
			            }  
						String name = new File(item.getName()).getName();
					    item.write(new File(path + File.separator + name));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			gson = new Gson();
			String msg = "{'error':'data not found.'}";
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(msg));
		}
	}

}
