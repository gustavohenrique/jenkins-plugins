package org.jenkinsci.plugins.transientprojectactionfactoryexampleplugin;

import hudson.model.Action;
import hudson.model.AbstractProject;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

public class MyAction implements Action {

    private final AbstractProject job;
    private final ProjectDao dao;

    public MyAction(AbstractProject job) {
        this.job = job;
        this.dao = new ProjectDao(job);
    }
    
    public MyProject getMyProject() {
        return dao.getOrCreateObject();
    }
    
    public void doSave(final StaplerRequest request, final StaplerResponse response) throws Exception {
        final JSONObject form = request.getSubmittedForm();
        MyProject myProject = request.bindJSON(MyProject.class, form.getJSONObject("myProject"));
        myProject.setProjectName(job.getName());
        
        dao.save(myProject);
        
        String url = request.getRootPath() + "/" + job.getUrl();
        response.sendRedirect(url);
    }
    
    public List<String> getUrls() {
        List<String> urls = new ArrayList<String>();
        urls.add("http://www.gustavohenrique.net");
        urls.add("http://tipsforlinux.com");
        urls.add("http://gustavohenrique.com");
        return urls;
    }

    public String getIconFileName() {
        return "/plugins/transientprojectactionfactory-example-plugin/images/icon.png";
    }

    public String getDisplayName() {
        return "TransienteProjectActionFactory Example";
    }

    public String getUrlName() {
        return "transientprojectactionfactoryExamplePlugin";
    }
}
