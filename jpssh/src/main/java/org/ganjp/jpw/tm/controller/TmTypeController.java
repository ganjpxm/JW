package org.ganjp.jpw.tm.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.tm.model.State;
import org.ganjp.jpw.tm.model.StateList;
import org.ganjp.jpw.tm.model.TmType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TmTypeController extends BaseController {

	@RequestMapping(value = "/tmType", method = RequestMethod.POST)
	public ModelAndView createUser(TmType user) {
//		userService.createUser(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/createSuccess");
		mav.addObject("user", user);
		return mav;
	}
	
	/**
	 * <p>format=html|json|xml</p>
	 * 
	 * @param mm
	 * @return
	 */
	@RequestMapping(value = "/tmType")
	public String showTmTypeMix(ModelMap mm) {
		mm.addAttribute("key", getTmTypes());
		return "tm/tmType";
	}
	
	/**
	 * <p>format=html|json|xml</p>
	 * 
	 * @param mm
	 * @return
	 */
	@RequestMapping(value = "/json")
	public @ResponseBody Map<String,Object> getJson() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ResultCode", 0);
		map.put("Record", getTmTypes());
		return map;
	}
	
	@RequestMapping(value = "/excel")
	public String showExcel(ModelMap mm) {
		mm.addAttribute("tmTypes", getTmTypes());
		return "userListExcel";
	}

	@RequestMapping(value = "/pdf")
	public String showUserListInPdf(ModelMap mm) {
		mm.addAttribute("tmTypes", getTmTypes());
		return "tmTypePdf";
	}

	@RequestMapping(value = "/ftl")
	public String showFreemarker(ModelMap mm) {
		mm.addAttribute("tmTypes", getTmTypes());
		return "tm/tmTypeFtl";
	}
	
	@RequestMapping(value = "/vm")
	public String showVelocity(ModelMap mm) {
		mm.addAttribute("tmTypes", getTmTypes());
		return "tm/tmTypeVm";
	}
	
	public List<TmType> getTmTypes() {
		Calendar calendar = new GregorianCalendar();
		List<TmType> tmTypeList = new ArrayList<TmType>();
		TmType tmType1 = new TmType();
		tmType1.setStrType("JWeb iOS 1");
		calendar.set(2013, 10, 20);
		tmType1.setDateType(calendar.getTime());
		
		TmType tmType2 = new TmType();
		tmType2.setStrType("JWeb iOS 2");
		calendar.set(2013, 10, 21);
		tmType2.setDateType(calendar.getTime());
		tmTypeList.add(tmType1);
		tmTypeList.add(tmType2);
		
		return tmTypeList;
	}
	
	public List<Map<String,String>> getTypes() {
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("strType", "ganjp");
		mapList.add(map1);
		return mapList;
	}
	
	
	//-------------------------------------- spring android ------------------------
	/**
     * Retrieve a list of states. Accepts a GET request for XML
     * @return An XML array of states
     */
    @RequestMapping(value = "states", method = RequestMethod.GET, produces = "application/xml")
    public @ResponseBody StateList fetchStatesXml() {
        List<State> states = getStates();
        StateList stateList = new StateList(states);
        return stateList;
    }
	
    /**
     * Retrieve a list of states. Accepts a GET request for JSON
     * @return A JSON array of states
     */
    @RequestMapping(value = "states", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<State> fetchStatesJson() {
        return getStates();
    }

    /**
     * Retrieve a single state. Accepts a GET request for JSON with a parameter for the state abbreviation
     * @param abbreviation contains the state abbreviation to use when finding the corresponding state
     * @return A JSON state
     */
    @RequestMapping(value = "state/{abbreviation}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody State fetchStateJson(@PathVariable String abbreviation) {
        return getStateByAbbreviation(abbreviation);
    }

    /**
     * Retrieve a single state. Accepts a GET request for XML with a parameter for the state abbreviation
     * @param abbreviation contains the state abbreviation to use when finding the corresponding state
     * @return An XML state
     */
    @RequestMapping(value = "state/{abbreviation}", method = RequestMethod.GET, produces = "application/xml")
    public @ResponseBody State fetchStateXml(@PathVariable String abbreviation) {
        return getStateByAbbreviation(abbreviation);
    }
    
    /**
     * @return a List of states
     */
    private List<State> getStates() {
        if (states == null) {
            states = new ArrayList<State>();
            states.add(new State("ALABAMA", "AL"));
            states.add(new State("ALASKA", "AK"));
            states.add(new State("ARIZONA", "AZ"));
            states.add(new State("ARKANSAS", "AR"));
            states.add(new State("CALIFORNIA", "CA"));
            states.add(new State("COLORADO", "CO"));
            states.add(new State("CONNECTICUT", "CT"));
            states.add(new State("DELAWARE", "DE"));
            states.add(new State("FLORIDA", "FL"));
            states.add(new State("GEORGIA", "GA"));
            states.add(new State("HAWAII", "HI"));
            states.add(new State("IDAHO", "ID"));
            states.add(new State("ILLINOIS", "IL"));
            states.add(new State("INDIANA", "IN"));
            states.add(new State("IOWA", "IA"));
            states.add(new State("KANSAS", "KS"));
            states.add(new State("KENTUCKY", "KY"));
            states.add(new State("LOUISIANA", "LA"));
            states.add(new State("MAINE", "ME"));
            states.add(new State("MARYLAND", "MD"));
            states.add(new State("MASSACHUSETTS", "MA"));
            states.add(new State("MICHIGAN", "MI"));
            states.add(new State("MINNESOTA", "MN"));
            states.add(new State("MISSISSIPPI", "MS"));
            states.add(new State("MISSOURI", "MO"));
            states.add(new State("MONTANA", "MT"));
            states.add(new State("NEBRASKA", "NE"));
            states.add(new State("NEVADA", "NV"));
            states.add(new State("NEW HAMPSHIRE", "NH"));
            states.add(new State("NEW JERSEY", "NJ"));
            states.add(new State("NEW MEXICO", "NM"));
            states.add(new State("NEW YORK", "NY"));
            states.add(new State("NORTH CAROLINA", "NC"));
            states.add(new State("NORTH DAKOTA", "ND"));
            states.add(new State("OHIO", "OH"));
            states.add(new State("OKLAHOMA", "OK"));
            states.add(new State("OREGON", "OR"));
            states.add(new State("PENNSYLVANIA", "PA"));
            states.add(new State("RHODE ISLAND", "RI"));
            states.add(new State("SOUTH CAROLINA", "SC"));
            states.add(new State("SOUTH DAKOTA", "SD"));
            states.add(new State("TENNESSEE", "TN"));
            states.add(new State("TEXAS", "TX"));
            states.add(new State("UTAH", "UT"));
            states.add(new State("VERMONT", "VT"));
            states.add(new State("VIRGINIA", "VA"));
            states.add(new State("WASHINGTON", "WA"));
            states.add(new State("WEST VIRGINIA", "WV"));
            states.add(new State("WISCONSIN", "WI"));
            states.add(new State("WYOMING", "WY"));
        }

        return states;
    }

    private State getStateByAbbreviation(String abbreviation) {
        List<State> states = getStates();
        for (State state : states) {
            if (state.getAbbreviation().compareToIgnoreCase(abbreviation) == 0) {
                return state;
            }
        }
        return null;
    }
    
    private static List<State> states;
}
