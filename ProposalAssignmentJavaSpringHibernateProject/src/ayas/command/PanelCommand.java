/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ayas.command;

import ayas.command.helper.ProposalAndItsPanelists;
import ayas.model.Panel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PanelCommand implements Serializable {
    private Panel panel;
    private List<ProposalAndItsPanelists> proposals = new ArrayList();
    
    private int panelId;
    private List allAssignments;
    private boolean showCapacities = true;
    private boolean showProvisions = true;
    private String missingPairs;

    public PanelCommand(){
        setShowCapacities(true);
        setShowProvisions(true);
    }

    public Integer getPanelistCount() {
        return proposals.iterator().next().getPanelists().size();
    }
    
    public Integer getProposalCount(){
        return proposals.size();
    }
    
    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    /**
     * @return the proposals
     */
    public List<ProposalAndItsPanelists> getProposals() {
        return proposals;
    }

    /**
     * @param proposals the proposals to set
     */
    public void setProposals(List<ProposalAndItsPanelists> proposals) {
        this.proposals = proposals;
    }

    /**
     * @return the allAssignments
     */
    public List getAllAssignments() {
        return allAssignments;
    }

    /**
     * @param allAssignments the allAssignments to set
     */
    public void setAllAssignments(List allAssignments) {
        this.allAssignments = allAssignments;
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    /**
     * @return the showCapacities
     */
    public boolean isShowCapacities() {
        return showCapacities;
    }

    /**
     * @param showCapacities the showCapacities to set
     */
    public void setShowCapacities(boolean showCapacities) {
        this.showCapacities = showCapacities;
    }

    /**
     * @return the showProvisions
     */
    public boolean isShowProvisions() {
        return showProvisions;
    }

    /**
     * @param showProvisions the showProvisions to set
     */
    public void setShowProvisions(boolean showProvisions) {
        this.showProvisions = showProvisions;
    }

    /**
     * @return the missingPairs
     */
    public String getMissingPairs() {
        return missingPairs;
    }

    /**
     * @param missingPairs the missingPairs to set
     */
    public void setMissingPairs(String missingPairs) {
        this.missingPairs = missingPairs;
    }
}
