/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author bernelia000
 */
public class FileUploader {
     public static void upload(String a) throws UnsupportedEncodingException, IOException{
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://www.berney.tech");
        File c = new File(a);
        FileEntity entity = new FileEntity(c, 
        ContentType.create("multipart/form-data", "UTF-8"));        

        // Request parameters and other properties.
        // List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        //params.add(new BasicNameValuePair("param-1", "12345"));
        // params.add(new BasicNameValuePair("param-2", "Hello!"));
        httppost.setEntity(entity);

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity yeet = response.getEntity();
        out.println(response);
        if (yeet != null) {
            try (InputStream instream = yeet.getContent()) {
                // do something useful
            }
        }
    }
}   

