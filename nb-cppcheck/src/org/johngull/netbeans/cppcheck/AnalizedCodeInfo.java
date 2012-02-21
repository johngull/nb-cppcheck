package org.johngull.netbeans.cppcheck;

import java.util.Vector;

/**
 *
 * @author Vitaly Bondar
 */
public class AnalizedCodeInfo {
    
    private String fullPath_;
    private String fileName_;
    private String projectRoot_;
    private Vector<String> includes_;
    
    public AnalizedCodeInfo() {
        fullPath_ = "";
        fileName_ = "";
        projectRoot_ = "";
        includes_ = new Vector<String>();
    }
    
    public AnalizedCodeInfo(String fullPath, String fileName, 
                            String projectRoot, Vector<String> includes) {
        fullPath_ = fullPath;
        fileName_ = fileName;
        projectRoot_ = projectRoot;
        setIncludes(includes);
    }
    
    public String fullPath() {return fullPath_;}
    public String fileName() {return fileName_;}
    public String projectRoot() {return projectRoot_;}
    public Vector<String> includes() {return includes_;}
    
    public void setFullPath(String fullPath) {
        fullPath_ = fullPath;
    }
    
    public void setFileName(String fileName) {
        fileName_ = fileName;
    }
    
    public void setProjectRoot(String projectRoot) {
        projectRoot_ = projectRoot;
    }
    
    public void setIncludes(Vector<String> includes) {
        if(includes_==null)
            includes_ = new Vector<String>();
        includes_.addAll(includes);
    }
}
