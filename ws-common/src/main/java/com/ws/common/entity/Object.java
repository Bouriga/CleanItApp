package com.ws.common.entity;

import com.flickr4java.flickr.photos.Photo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name="Object")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Object implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated object ID")
    private int Id;

    @Column(name = "Category")
    @ApiModelProperty(notes = "The Category of object")
    private String Category;

    @Column(name = "Description")
    @ApiModelProperty(notes = "The Description of object")
    private String Description;

    @Column(name = "Seller_Disponibility")
    @ApiModelProperty(notes = "The Seller_Disponibility of object")
    private String Seller_Disponibility;

    @Column(name = "Shippement")
    @ApiModelProperty(notes = "The Shippement of object")
    private String Shippement;

    @Column(name = "Adress")
    @ApiModelProperty(notes = "The Adress of object")
    private String Adress;

    @Column(name = "Status")
    @ApiModelProperty(notes = "The Status of object")
    private String Status;

    @Column(name = "Sub_category")
    @ApiModelProperty(notes = "The Sub_category of object")
    private String Sub_category;

    @Column(name = "Title")
    @ApiModelProperty(notes = "The Title of object")
    private String Title;

    @Column(name = "Type")
    @ApiModelProperty(notes = "The Type of object")
    private String Type;

    @Column(name = "Photo")
    @ApiModelProperty(notes = "The Photo of object")
    private String Photo;


}
