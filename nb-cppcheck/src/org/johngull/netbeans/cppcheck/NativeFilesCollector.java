package org.johngull.netbeans.cppcheck;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.netbeans.modules.cnd.api.project.NativeProject;
import org.netbeans.modules.cnd.api.project.NativeProjectRegistry;
import org.netbeans.modules.cnd.api.project.NativeFileItem;

/**
 *
 * @author Vitaly Bondar
 */
public class NativeFilesCollector {
    
    private NativeFilesCollector() {
        
    }
    
    public static Vector<AnalizedCodeInfo> collectFromAllOpened() {
        Vector<AnalizedCodeInfo> res = new Vector<AnalizedCodeInfo>();
        
        Collection<NativeProject> nps = NativeProjectRegistry.getDefault().getOpenProjects();
        for(NativeProject np : nps) {
            List<NativeFileItem> fl = np.getAllFiles();

            for(NativeFileItem fi : fl)
                res.add(new AnalizedCodeInfo(fi.getAbsolutePath(), fi.getName(), np.getProjectRoot()));
        }
            
        return res;
    }
            
}
