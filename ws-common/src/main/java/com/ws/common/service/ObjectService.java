package com.ws.common.service;

import com.ws.common.entity.Object;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ObjectService {
    List<Object> getObjects();

    void createObject(Object object);

    Object addObject(@ModelAttribute Object object);

    String savePhoto(InputStream photo, String title) throws Exception;
}
