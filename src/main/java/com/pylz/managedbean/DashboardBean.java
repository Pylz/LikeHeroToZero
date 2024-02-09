package com.pylz.managedbean;

import com.pylz.entities.EmissionData;
import com.pylz.service.EmissionDataService;
import com.pylz.util.CSVUtils;
import org.primefaces.model.file.UploadedFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@RequestScoped
public class DashboardBean {

    private static final Logger logger = LogManager.getLogger(DashboardBean.class);

    private UploadedFile file;
    private EmissionDataService emissionDataService;

    private String redirectPage;

    public DashboardBean() {
        // Standard constructor
    }

    @Inject
    public DashboardBean(EmissionDataService emissionDataService) {
        this.emissionDataService = emissionDataService;
    }

    public void upload() {
        if (file != null && !file.getFileName().isEmpty()) {
            try {
                List<EmissionData> emissionsFromCSV = CSVUtils.parseCSV(file.getInputStream());
                for (EmissionData emissionData : emissionsFromCSV) {
                    if (!emissionDataService.existsByYearAndEmissions(emissionData.getYear(), emissionData.getCo2Emissions())) {
                        emissionDataService.saveEmission(emissionData);
                    }
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "File uploaded successfully."));
            } catch (IOException e) {
                logger.error("File upload failed", e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "File upload failed. Please try again."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No file selected."));
        }
    }

    public void deleteAllEmissions() {
        emissionDataService.deleteAllEmissions();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, redirectPage + "?faces-redirect=true");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "All emissions data deleted."));
    }

    // Getters and Setters
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }
}
