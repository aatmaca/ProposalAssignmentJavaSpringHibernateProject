/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.controller;

import ayas.business.DataService;
import ayas.command.PanelCommand;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;

import ayas.command.helper.ProposalAndItsPanelists;
import ayas.util.AyasUtil;
import ayas.util.SessionData;
import org.springframework.validation.BindException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 *
 * @author 
 */
public class ClearProposalProvisionsController extends SimpleFormController {
     private DataService dataService;

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();
        return panelCommand;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        SessionData sessionData = AyasUtil.getSessionData(request);
        PanelCommand panelCommand = sessionData.getCurrentPanelCommand();
        Integer numProposals = panelCommand.getProposals().size();
        Integer provisions[] = new Integer[numProposals];
        Integer recordIds[] = new Integer[numProposals];
        for( int i = 0; i < numProposals; i ++){
            recordIds[i] = panelCommand.getProposals().get(i).getProposal().getRecordId();
            provisions[i] = 0;
        }
        dataService.updateProposalProvisions( provisions, recordIds);

        String returnUrl = "panel.htm?panelId=" + panelCommand.getPanel().getId();
        return new ModelAndView(new RedirectView(returnUrl));
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
