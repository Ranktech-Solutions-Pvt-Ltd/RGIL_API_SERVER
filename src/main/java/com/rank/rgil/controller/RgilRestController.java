package com.rank.rgil.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rank.rgil.event.ResponseEvent;
import com.rank.rgil.response.AgentLoginResponse;
import com.rank.rgil.response.CustomerDto;
import com.rank.rgil.rest.service.CCMSRESTControllerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest")
public class RgilRestController {

    private static final Logger logger = LogManager.getLogger(RgilRestController.class);

	@Autowired
	private CCMSRESTControllerService rgilRESTControllerServiceImpl;
    
    @GetMapping("/hello")
    public ResponseEntity<String> gethello(){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
        
    }

    /**
	 * 
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginagent",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<ResponseEvent<AgentLoginResponse>> agentLogin(@RequestBody String credential) throws Exception {
		logger.info("############### Inside of agentLogin "+credential+" ##############");
		return new ResponseEntity<ResponseEvent<AgentLoginResponse>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getAgentLoginResponse(credential)), HttpStatus.OK);
        
    }
        
        @RequestMapping(value="/deleterooms",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<ResponseEvent<AgentLoginResponse>> deleteRooms(@RequestBody String credential) throws Exception {
		logger.info("############### Inside of deleterooms "+credential+" ##############");
		return new ResponseEntity<ResponseEvent<AgentLoginResponse>>(ResponseEvent.response(rgilRESTControllerServiceImpl.deleteRooms(credential)), HttpStatus.OK);
        
    }
        
        @RequestMapping(value="/alive",method= RequestMethod.GET)
    public ResponseEntity<ResponseEvent<String>> getHelthCheck() throws Exception { 
		logger.info("############### Inside of getHelthCheck ##############");
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getHelthCheck()), HttpStatus.OK);
		
	}
    
    @RequestMapping(value="/getUsers",method= RequestMethod.GET)
    public ResponseEntity<ResponseEvent<String>> getUsers() throws Exception { 
		logger.info("############### Inside of getUsers from RGIL ##############");
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getUsersFromRGIL()), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/onlineagent",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<ResponseEvent<AgentLoginResponse>> agentReady(@RequestBody String credential) throws Exception {
		logger.info("############### Inside of agentReady "+credential+" ##############");
		return new ResponseEntity<ResponseEvent<AgentLoginResponse>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getAgentReadyResponse(credential)), HttpStatus.OK);
        
    }
        
        /**
	 * 
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/offlineagent",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<ResponseEvent<AgentLoginResponse>> agentNotReady(@RequestBody String credential) throws Exception {
		logger.info("############### Inside of agentNotReady "+credential+" ##############");
		return new ResponseEntity<ResponseEvent<AgentLoginResponse>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getAgentNotReadyResponse(credential)), HttpStatus.OK);
        
    }
	

	
	/**
	 * 
	 * @param callInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logoutagent",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<ResponseEvent<AgentLoginResponse>> agentLogout(@RequestBody String callInfo) throws Exception {
		logger.info("############### Inside of agentLogout "+callInfo+" ##############");
		return new ResponseEntity<ResponseEvent<AgentLoginResponse>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getAgentLogoutResponse(callInfo)), HttpStatus.OK);
        
    }
	
        /**
	 * 
	 * @param callInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/calllog",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<ResponseEvent<List<CustomerDto>>> agentCallLog(@RequestBody String callInfo) throws Exception {
		logger.info("############### Inside of agentCallLog "+callInfo+" ##############");
		return new ResponseEntity<ResponseEvent<List<CustomerDto>>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getCallLogResponse(callInfo)), HttpStatus.OK);
        
    }

	@RequestMapping(value="/cleanactivity",method= RequestMethod.GET)
    public ResponseEntity<ResponseEvent<String>> getActivityClean() throws Exception { 
		logger.info("############### Inside of getActivityClean from RGIL ##############");
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(rgilRESTControllerServiceImpl.getActivityClean()), HttpStatus.OK);
		
	}

}
