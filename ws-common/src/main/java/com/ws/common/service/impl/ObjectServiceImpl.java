package com.ws.common.service.impl;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.ws.common.entity.Object;
import org.scribe.model.Verifier;
import com.ws.common.repository.ObjectRepository;
import com.ws.common.service.ObjectService;
import org.scribe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ObjectServiceImpl implements ObjectService {

    private final ObjectRepository objectrepository;


    private Flickr flickr;

    private UploadMetaData uploadMetaData = new UploadMetaData();

    private String apiKey = "413de1383763442f31f2d1b02e16e391";

    private String sharedSecret = "457c781783133e51";

    @Autowired
    private ObjectService objectService;


    public void connect() {
        flickr = new Flickr(apiKey, sharedSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
        auth.setToken("72157693414522601-8c6bd50959d6dd08");
        auth.setTokenSecret("0dc12c5843ac44bb");
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
    }

    public void auth() {
        flickr = new Flickr(apiKey, sharedSecret, new REST());

        AuthInterface authInterface = flickr.getAuthInterface();

        Token token = authInterface.getRequestToken();
        System.out.println("token: " + token);

        String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
        System.out.println("Follow this URL to authorise yourself on Flickr");
        System.out.println(url);
        System.out.println("Paste in the token it gives you:");
        System.out.print(">>");

        String tokenKey = JOptionPane.showInputDialog(null);

        Token requestToken = authInterface.getAccessToken(token, new Verifier(tokenKey));
        System.out.println("Authentication success");

        Auth auth = null;
        try {
            auth = authInterface.checkToken(requestToken);
        } catch (FlickrException e) {
            e.printStackTrace();
        }

        // This token can be used until the user revokes it.
        System.out.println("Token: " + requestToken.getToken());
        System.out.println("Secret: " + requestToken.getSecret());
        System.out.println("nsid: " + auth.getUser().getId());
        System.out.println("Realname: " + auth.getUser().getRealName());
        System.out.println("Username: " + auth.getUser().getUsername());
        System.out.println("Permission: " + auth.getPermission().getType());

    }

    @Override
    public String savePhoto(InputStream photo, String title) throws Exception {
        connect();
        uploadMetaData.setTitle(title);
        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }


    @Autowired
    public ObjectServiceImpl(ObjectRepository objectrepository) {
        this.objectrepository = objectrepository;
    }

    @Override
    public List<Object> getObjects() {
        return objectrepository.findAll();
    }

    @Override
    public void createObject(Object object) {
        objectrepository.save(object);
    }


    @Override
    public Object addObject(Object object) {
        /*String photoUrl;
        if (object != null) {
            if (file != null && !file.isEmpty()) {
                InputStream stream = null;
                try {
                    stream = file.getInputStream();
                    photoUrl = objectService.savePhoto(stream, object.getTitle());
                    object.setPhoto(photoUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/
            objectService.createObject(object);

            return object;
        }


}
