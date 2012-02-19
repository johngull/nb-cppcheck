package org.johngull.netbeans.cppcheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

/**
 *
 * @author Vitaly Bondar
 */

@ActionID(category = "Build",
id = "org.johngull.netbeans.cppcheck.AllOpenedToolBarAction")
@ActionRegistration(iconBase = "org/johngull/netbeans/cppcheck/cppcheck.png",
displayName = "#CTL_AllOpenedToolBarAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/Build", position = 500)
})
@Messages("CTL_AllOpenedToolBarAction=C/C++ Static Analysis - Opened Projects")
public final class AllOpenedToolBarAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        SATopComponent table = SATopComponent.getInstance();
        WindowManager.getDefault().findMode("output").dockInto(table);
        table.openAtTabPosition(0);
        table.requestActive();
        table.model().clear();
        
        CppCheckRunnable cppcheck = new CppCheckRunnable(NativeFilesCollector.collectFromAllOpened(), table.model());
        cppcheck.run();
    }
}
