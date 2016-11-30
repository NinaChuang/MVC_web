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

@WebServlet("/contorllerFielUpload/")
@MultipartConfig
public class ContorllerFielUpload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				System.out.println("multiparts/"+multiparts.size());
				for (FileItem item : multiparts) {
					 System.out.println("item.isFormField()."+item.isFormField());
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						System.out.println("name=" + name);
						// item.write(new File("" + File.separator + name));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
