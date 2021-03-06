package com.jacob.service;

import java.util.List;

import com.jacob.model.Picture;

public interface PictureServiceInterface {
    public Picture findPictureById(int id);
    public void savePicture(Picture picture);
    public Picture findPictureByPicUrl(String url);
//    public String generateKeynameForPic(String pic_name);
    public List<Picture> getAllPictures();
}
