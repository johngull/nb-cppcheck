package org.johngull.netbeans.cppcheck;

/**
 *
 * @author Vitaly Bondar
 */
public class AnalizedCodeInfo {
    
    private String fullPath_;
    private String fileName_;
    private String projectRoot_;
    
    public AnalizedCodeInfo() {
        fullPath_ = "";
        fileName_ = "";
        projectRoot_ = "";
    }
    
    public AnalizedCodeInfo(String fullPath, String fileName, String projectRoot) {
        fullPath_ = fullPath;
        fileName_ = fileName;
        projectRoot_ = projectRoot;
    }
    
    public String fullPath() {return fullPath_;}
    public String fileName() {return fileName_;}
    public String projectRoot() {return projectRoot_;}
    
    public void setFullPath(String fullPath) {
        fullPath_ = fullPath;
    }
    
    public void setFileName(String fileName) {
        fileName_ = fileName;
    }
    
    public void setProjectRoot(String projectRoot) {
        projectRoot_ = projectRoot;
    }
}
