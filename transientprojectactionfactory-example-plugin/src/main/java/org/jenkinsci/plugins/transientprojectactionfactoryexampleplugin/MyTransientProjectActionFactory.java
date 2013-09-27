package org.jenkinsci.plugins.transientprojectactionfactoryexampleplugin;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;
import hudson.model.AbstractProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Extension
public class MyTransientProjectActionFactory extends TransientProjectActionFactory {

    @Override
    public Collection<? extends Action> createFor(AbstractProject project) {
        final List<MyAction> actions = new ArrayList<MyAction>();
        actions.add(new MyAction(project));
        return actions;
    }
}
