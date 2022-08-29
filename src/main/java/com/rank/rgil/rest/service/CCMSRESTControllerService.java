/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rank.rgil.rest.service;

import java.util.List;

import com.rank.rgil.response.AgentLoginResponse;
import com.rank.rgil.response.CustomerDto;


/**
 * @since 30-Nov-2018
 * @author Sukanta
 */

public interface CCMSRESTControllerService {
    
	    
    public String getHelthCheck();//chayan
    
    public String getUsersFromRGIL();//chayan

	public String getActivityClean();//chayan
    
    
    /**
	 * @author Sukanta
	 * @since 03-Dec-2018
	 * @param credential, which is storing loginId and password as JSON format
	 * @return AgentLoginResponse object which is carrying the information after an successful login attempt
	 * of an agent
     * @throws Exception 
	 */
    public AgentLoginResponse getAgentLoginResponse(String credential) throws Exception;//chayan
    
    /**
	 * @author Sukanta
	 * @since 03-Dec-2018
	 * @param credential, which is storing loginId and password as JSON format
	 * @return AgentLoginResponse object which is carrying the information after an successful login attempt
	 * of an agent
     * @throws Exception 
	 */
    public AgentLoginResponse getAgentReadyResponse(String credential) throws Exception;//chayan
    
    public AgentLoginResponse deleteRooms(String credential) throws Exception;//chayan
    
    /**
   	 * @author Sukanta
   	 * @since 04-Dec-2018
   	 * @param callInfo, which is storing callId and agent credential as JSON format
   	 * @return AgentLoginResponse object which is carrying the information after an successful login attempt
	 * of an agent
     * @throws Exception 
   	 */
    public AgentLoginResponse getAgentLogoutResponse(String callInfo) throws Exception;//chayan
    
    /**
   	 * @author Sukanta
   	 * @since 04-Dec-2018
   	 * @param callInfo, which is storing agentId credential as JSON format
   	 * @return AgentLoginResponse object which is carrying the information after an successful login attempt
	 * of an agent
     * @throws Exception 
   	 */
    public AgentLoginResponse getAgentNotReadyResponse(String callInfo) throws Exception;//chayan
    
    /**
   	 * @author Sukanta
   	 * @since 04-Dec-2018
   	 * @param callInfo, which is storing agentId credential as JSON format
   	 * @return AgentLoginResponse object which is carrying the information after an successful login attempt
	 * of an agent
     * @throws Exception 
   	 */
    public List<CustomerDto> getCallLogResponse(String callInfo) throws Exception;//chayan
    
    
}
