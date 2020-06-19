package Gui;///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.sun.xml.internal.ws.wsdl.writer.document.Part;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Collection;
//
///**
// *
// * @author Hatem
// */
//@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 100, // 10 MB
//        maxFileSize = 1024 * 1024 * 150, // 50 MB
//        maxRequestSize = 1024 * 1024 * 200)      // 100 MB
//public class UploadServlet extends HttpServlet {
//
//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//        Collection<Part> parts = req.getParts();
//        Part data = parts.iterator().next();
//        try(InputStream is = data.getInputStream()) {}
//            // store or do something with the input stream
//        }
//    }
//}
//
