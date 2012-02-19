package org.johngull.netbeans.cppcheck;

/**
 *
 * @author Vitaly Bondar
 */
public class StaticAnalysisItem {
    public enum SAErrorType {
        Error,
        Warning,
        Portability,
        Performance,
        Style,
        Information,
        UnusedFunction
    }
    
    private SAErrorType type_;
    private String fullPath_;
    private int line_;
    private String fileName_;
    private String descripton_;
    
    public StaticAnalysisItem() {
        type_ = SAErrorType.Error;
        fullPath_ = "";
        line_ = 0;
        fileName_ = "";
        descripton_ = "";
    }
    
    public StaticAnalysisItem(SAErrorType type, String fullPath, int line,
                              String fileName, String description) {
        type_ = type;
        fullPath_ = fullPath;
        line_ = line;
        fileName_ = fileName;
        descripton_ = description;
    }
    
    public SAErrorType type() {
        return type_;
    }
    
    public void setType(SAErrorType type) {
        type_ = type;
    }
    
    public String fullPath() {
        return fullPath_;
    }
    
    public void setFullPath(String fullPath) {
        fullPath_ = fullPath;
    }
    
    public int line() {
        return line_;
    }
    
    public void setLine(int line) {
        line_ = line;
    }
    
    public String fileName() {
        return fileName_;
    }
    
    public void setFileName(String fileName) {
        fileName_ = fileName;
    }
    
    public String description() {
        return descripton_;
    }
    
    public void setDescription(String description) {
        descripton_ = description;
    }
}
