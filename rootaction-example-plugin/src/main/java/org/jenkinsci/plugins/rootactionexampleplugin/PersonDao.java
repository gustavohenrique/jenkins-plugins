package org.jenkinsci.plugins.rootactionexampleplugin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import jenkins.model.Jenkins;

public class PersonDao {

    public Person getOrCreateObject() {
        try {
            final String path = getFilePath();
            return (Person) Jenkins.XSTREAM.fromXML(new FileInputStream(path));
        }
        catch (final Exception e) {
            return new Person();
        }
    }

    public void save(Person object) {
        try {
            Jenkins.XSTREAM.toXML(object, getFile());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getFilePath() {
        return Jenkins.getInstance().getRootDir() + System.getProperty("file.separator", "/") + "person.xml";
    }

    private static OutputStreamWriter getFile() throws Exception {
        FileOutputStream outputStream = new FileOutputStream(getFilePath());
        return new OutputStreamWriter(outputStream, "UTF-8");
    }
}
