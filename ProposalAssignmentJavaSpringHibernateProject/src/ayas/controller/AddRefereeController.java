/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.controller;

import ayas.business.DataService;
import ayas.command.AddRefereeCommand;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import ayas.util.AyasUtil;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author 
 */

public class AddRefereeController extends SimpleFormController {

    private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        //PanelCommand panelCommand = new PanelCommand();
        //return panelCommand;        
        AddRefereeCommand refereeCommand = new AddRefereeCommand();
        return refereeCommand;
    }


    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {

        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        AddRefereeCommand refereeCommand = new AddRefereeCommand();
        refereeCommand.setPanelCommand(panelCommand);
        Map map = new HashMap();
        map.put("newRefereeCount", new Integer(1));
        return map;

    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws NoSuchAlgorithmException {

        PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
        AddRefereeCommand refereeCommand = new AddRefereeCommand();
        refereeCommand.setPanelCommand(panelCommand);
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newRefereeCount", "necessary");

        if (errors.hasErrors()) {
            refereeCommand.setNewRefereeCount(1);
            errors.reject("addReferee.notInteger");
            return;
        }
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

          PanelCommand panelCommand = AyasUtil.getSessionData(request).getCurrentPanelCommand();
          AddRefereeCommand refereeCommand = (AddRefereeCommand) command;
          refereeCommand.setPanelCommand(panelCommand);
          
          dataService.addReferee(panelCommand, refereeCommand.getNewRefereeCount());          
          
          return new ModelAndView(new RedirectView("panel.htm?panelId=" + panelCommand.getPanelId() ));
    }

    /**
     * @return the dataService
     */
    public DataService getDataService() {
        return dataService;
    }

    /**
     * @param dataService the dataService to set
     */
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}
