package com.ws.common.controller;


import com.ws.common.entity.Object;
import com.ws.common.repository.ObjectRepository;
import com.ws.common.service.ObjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/object")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}

)
@Api(value = "ObjectControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Object Controller")
public class ObjectController {

    @Autowired
    private ObjectService objectService;


    private final ObjectRepository objectrepository;


    @Autowired
    public ObjectController(ObjectRepository objectrepository) {
        this.objectrepository = objectrepository;

    }

    @GetMapping(value = "/allObjects")
    @ApiOperation("Gets all objects")
    public List<Object> findAll() {
        return objectService.getObjects();
    }

    @GetMapping(value = "/objectById/{Id}")
    @ApiOperation("Gets object by id")
    public Object findById(@PathVariable("Id") int Id) {
        return objectrepository.findById(Id).orElse(null);
    }


    @PostMapping(value = "/ajoutObject")
    @ApiOperation("ajout Object")
    public Object ajoutObject(@ModelAttribute Object object) {
        return objectService.addObject(object);
    }

    @PostMapping(value = "/updateObject/{Id}")
    @ApiOperation("update object")
    public Object updateObject(@ModelAttribute Object object) {

        objectrepository.save(object);

        return object;
    }


    @DeleteMapping(value = "/deleteObject/{Id}")
    @ApiOperation("delete object")
    public Object deleteObject(@PathVariable("Id") int Id) {

        Object object = objectrepository.findById(Id).orElse(null);
        if (object != null) {
            objectrepository.delete(object);
        }

        return object;
    }

}

