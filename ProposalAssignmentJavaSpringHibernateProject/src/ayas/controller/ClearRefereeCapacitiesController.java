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
import ayas.model.AssignedPanelistToProposal;
import ayas.util.AyasUtil;
import ayas.util.SessionData;
import org.springframework.validation.BindException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
/**
 *
 * @author 
 */
public class ClearRefereeCapacitiesController extends SimpleFormController {
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
        Integer numReferees = panelCommand.getPanelistCount();

        
        ArrayList<ProposalAndItsPanelists> assignments = (ArrayList<ProposalAndItsPanelists>)panelCommand.getProposals();
        ProposalAndItsPanelists assignment0 = (ProposalAndItsPanelists)assignments.get(0);
        ArrayList<AssignedPanelistToProposal> panelists = (ArrayList<AssignedPanelistToProposal>) assignment0.getPanelists();

        Integer assignmentIds[] = new Integer[numReferees];
        Integer capacities[] = new Integer[numReferees];
        for( int i = 0; i < numReferees; i++){
            AssignedPanelistToProposal a = panelists.get(i);
            assignmentIds[i] = a.getPanelistToProposalId().getAssignedPanelist().getAssignmentId();
            capacities[i] = 0;
        }

        dataService.updateRefereeCapacities(capacities, assignmentIds);
        String returnUrl = "panel.htm?panelId=" + panelCommand.getPanel().getId();;
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
