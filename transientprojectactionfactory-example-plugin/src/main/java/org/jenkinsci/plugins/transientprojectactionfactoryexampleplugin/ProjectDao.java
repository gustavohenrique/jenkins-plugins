package org.jenkinsci.plugins.transientprojectactionfactoryexampleplugin;

import hudson.model.AbstractProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import jenkins.model.Jenkins;

public class ProjectDao {
    
    private final AbstractProject project;

    public ProjectDao(AbstractProject project) {
        this.project = project;
    }

    public MyProject getOrCreateObject() {
        try {
            final String path = getFilePath();
            return (MyProject) Jenkins.XSTREAM.fromXML(new FileInputStream(path));
        }
        catch (final Exception e) {
            return new MyProject();
        }
    }

    public void save(MyProject object) {
        try {
            Jenkins.XSTREAM.toXML(object, getFile());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getFilePath() {
        return project.getRootDir() + System.getProperty("file.separator", "/") + "myproject.xml";
    }
    
    private OutputStreamWriter getFile() throws Exception {
        FileOutputStream outputStream = new FileOutputStream(getFilePath());
        return new OutputStreamWriter(outputStream, "UTF-8");
    }
}
