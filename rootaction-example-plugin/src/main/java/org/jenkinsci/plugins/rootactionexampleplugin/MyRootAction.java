package org.jenkinsci.plugins.rootactionexampleplugin;

import hudson.Extension;
import hudson.model.RootAction;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

@Extension
public class MyRootAction implements RootAction {
    
    private final PersonDao dao = new PersonDao();
    
    public Person getPerson() {
        return dao.getOrCreateObject();
    }
    
    public void doSave(final StaplerRequest request, final StaplerResponse response) throws Exception {
        JSONObject form = request.getSubmittedForm();
        
        Person person = new Person();
        person.setName(form.getString("name"));
        person.setPhones(bindToList(form.get("phones")));
        
        dao.save(person);
        
        //response.forwardToPreviousPage(request);
        response.sendRedirect(request.getContextPath());
    }
    
    private List bindToList(Object object) {
        List<String> list = new ArrayList<String>();

        if (object == null) {
            return list;
        }

        String key = "phone";
        if (object.getClass() == JSONObject.class) {
            JSONObject obj = (JSONObject) object;
            list.add(obj.getString(key));
        }
        
        if (object.getClass() == JSONArray.class) {
            JSONArray array = (JSONArray) object;
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                list.add(obj.getString(key));
            }
        }
        return list;
    }

    public String getIconFileName() {
        return "/plugin/rootaction-example-plugin/images/icon.png";
    }

    public String getDisplayName() {
        return "RootAction Example Plugin";
    }

    public String getUrlName() {
        return "rootactionExamplePlugin";
    }
}
