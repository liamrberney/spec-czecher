package com.berneytech.spec.czecher;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.*;
import org.apache.http.impl.client.*;

/**
 *
 * @author liamrberney
 */
public class FileUploader {
     public static boolean upload(String a, String User, String pass) throws UnsupportedEncodingException, IOException{
       /* HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.berney.tech/api/auth/upload-specs");
        
        File c = new File(a);
        FileWriter fr = new FileWriter(c, true);
        fr.write(User);
        fr.write(pass);
        fr.close();
        FileEntity entity = new FileEntity(c, 
        ContentType.create("multipart/form-data", "UTF-8"));        
        httppost.setEntity(entity);
        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity yeet = response.getEntity();
        out.println(response);
        if (yeet != null) {
            try (InputStream instream = yeet.getContent()) {
                // do something useful
            }
        }*/
     //return response=good
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("https://api.berney.tech/api/auth/upload-specs");

        FileBody bin = new FileBody(new File(a));
        StringBody username = new StringBody(User);
        StringBody password = new StringBody(pass);

        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("File", bin);
        reqEntity.addPart("Username", username);
        reqEntity.addPart("Password", password);
        httppost.setEntity(reqEntity);

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();
    return true;
    }
}   

