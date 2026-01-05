package com.yourorg.telemedicine.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageDto implements Serializable {
	 private Long id;
	  private String name;
	    private String contact;
	    private String email;
	    private String Msg;

}
