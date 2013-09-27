package org.jenkinsci.plugins.transientprojectactionfactoryexampleplugin;

import org.kohsuke.stapler.DataBoundConstructor;

public class MyProject {

    private String projectName;
    private String owner;
    private String url;
    
    public MyProject() {}
    
    @DataBoundConstructor
    public MyProject(final String owner, final String url) {
        this.owner = owner;
        this.url = url;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
