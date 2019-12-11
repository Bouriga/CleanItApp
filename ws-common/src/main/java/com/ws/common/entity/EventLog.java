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
@Table(name="EVENT_LOG")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EventLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated event ID")
    private Long id;

    @Column(name = "EMAIL")
    @ApiModelProperty(notes = "The email of user")
    private String email;

    @Column(name = "USERHOST")
    @ApiModelProperty(notes = "The userHost of user")
    private String userHost;

    @Column(name = "TIMESTAMP")
    @ApiModelProperty(notes = "The time of connexion")
    private String timestamp;

    @Column(name = "ACTION")
    @ApiModelProperty(notes = "The action do by user")
    private String action;


}
