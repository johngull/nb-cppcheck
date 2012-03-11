package org.johngull.netbeans.cppcheck;

import java.io.File;

import org.openide.filesystems.FileUtil;
import org.netbeans.modules.cnd.modelutil.CsmUtilities;
import org.netbeans.modules.cnd.api.model.CsmFile;

/**
 *
 * @author Vitaly Bondar
 */
public class SourceOpener {
    
    private SourceOpener() {
        
    }
    
    public static void OpenSourceLine(String fullPath, int line) {
        CsmUtilities.openSource(CsmUtilities.getCsmFile(FileUtil.toFileObject(new File(fullPath)), false, true), line, 0);
    }
}
