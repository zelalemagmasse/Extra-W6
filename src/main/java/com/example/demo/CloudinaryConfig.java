package com.example.demo;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.cloudinary.Singleton;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {

    private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(@Value("${cloudinary.apikey}") String  key,
                            @Value("${cloudinary.apisecret}")  String secrete,
                            @Value("${cloudinary.cloudname}")   String cloud){
      cloudinary=Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secrete;
        cloudinary.config.apiKey=key;
    }
    public Map upload(Object file, Map options){
        try{
            return cloudinary.uploader().upload(file,options);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String createUrl(String name, int width,int height,String action){
        return cloudinary.url()
                .transformation(new Transformation()
                .width(width).height(height)
                .border("2px_solid_black").crop(action)).imageTag(name);
    }
}
