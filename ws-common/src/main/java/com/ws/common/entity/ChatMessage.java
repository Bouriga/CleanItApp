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
@Table(name="ChatMessage")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ChatMessage implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated ChatMessage ID")
    private int Id;

    @Column(name = "Message")
    @ApiModelProperty(notes = "The Message of chat")
    private String message;

    @Column(name = "Receiver ")
    @ApiModelProperty(notes = "The Receiver in chat")
    private String receiver ;

    @Column(name = "Sender ")
    @ApiModelProperty(notes = "The Sender in chat")
    private String sender;
}