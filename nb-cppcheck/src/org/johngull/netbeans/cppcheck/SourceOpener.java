package org.johngull.netbeans.cppcheck;

import java.io.File;

import org.openide.filesystems.FileUtil;
import org.netbeans.modules.cnd.modelutil.CsmUtilities;

/**
 *
 * @author Vitaly Bondar
 */
public class SourceOpener {
    
    private SourceOpener() {
        
    }
    
    public static void OpenSourceLine(String fullPath, int line) {
        CsmUtilities.openSource(FileUtil.toFileObject(new File(fullPath)), line, 0);
    }
}
