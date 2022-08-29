package com.rank.rgil.response;

import java.io.Serializable;

// import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sukanta
 * @since 05-Dec-2018
 * @Purpose the following Class is used for accepting file upload data
 */
@JsonInclude(Include.NON_NULL)
public class ParticipantDto implements Serializable{

    @JsonIgnore
	private static final long serialVersionUID = -4972117242384277898L;
    
	private Long id;
    private String perticipentId;
    private String name;
    private String loginId;
    private String nameAndLoginId;
    private String participantType;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAndLoginId() {
        return nameAndLoginId;
    }

    public void setNameAndLoginId(String nameAndLoginId) {
        this.nameAndLoginId = nameAndLoginId;
    }

    public String getPerticipentId() {
        return perticipentId;
    }

    public void setPerticipentId(String perticipentId) {
        this.perticipentId = perticipentId;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ParticipantDto){
        	ParticipantDto element = (ParticipantDto) obj;
            if(element != null && this.participantType.equals(element.participantType) && this.id.equals(element.id)){
                return true;
            }
        }
        return false;
    }

    
}

