package com.ws.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="USER")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated user ID")
    private Long id;

    @Column(name = "USERNAME")
    @ApiModelProperty(notes = "The username of user")
    private String username;

    @Column(name = "PASSWORD")
    @ApiModelProperty(notes = "The password of user")
    private String password;

    @Column(name = "ROLE")
    @ApiModelProperty(notes = "The role of user")
    private String role;

    @Column(name = "email")
    @ApiModelProperty(notes = "The email of user")
    private String email;

    @Column(name = "first_name")
    @ApiModelProperty(notes = "The FirstName of user")
    private String FirstName;

    @Column(name = "last_name")
    @ApiModelProperty(notes = "The LastName of user")
    private String LastName;

    @Column(name = "LAST_LOGIN")
    @ApiModelProperty(notes = "The last login of user")
    private String Last_login;

    @Column(name = "Type")
    @ApiModelProperty(notes = "The Type of user")
    private String Type;

    @Column(name = "Phone_Number")
    @ApiModelProperty(notes = "The Phone_Number of user")
    private String Phone_Number;

    @Column(name = "Photo")
    @ApiModelProperty(notes = "The Photo of user")
    private String Photo;


}
