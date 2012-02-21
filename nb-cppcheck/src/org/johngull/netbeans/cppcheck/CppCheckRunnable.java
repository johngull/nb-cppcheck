package org.johngull.netbeans.cppcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.openide.ErrorManager;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitaly Bondar
 */
public class CppCheckRunnable implements Runnable {
    private Vector<AnalizedCodeInfo> files_ = new Vector<AnalizedCodeInfo>();
    private StaticAnalysisModel model_;
    
    public CppCheckRunnable(Vector<AnalizedCodeInfo> files, StaticAnalysisModel model) {
	files_.addAll(files);
        model_ = model;
    }
    
    private StaticAnalysisItem.SAErrorType detectType(String type) {
        type = type.toLowerCase();
        if(type.contains("error"))
            return StaticAnalysisItem.SAErrorType.Error;
        if(type.contains("warning"))
            return StaticAnalysisItem.SAErrorType.Warning;
        if(type.contains("style"))
            return StaticAnalysisItem.SAErrorType.Style;
        if(type.contains("performance"))
            return StaticAnalysisItem.SAErrorType.Performance;
        if(type.contains("portability"))
            return StaticAnalysisItem.SAErrorType.Portability;
        if(type.contains("information"))
            return StaticAnalysisItem.SAErrorType.Information;
        
        return StaticAnalysisItem.SAErrorType.Information;
    }
    
    @Override
    public void run() {
        
        if(model_==null)
            return;
        
	try {
            
            for(AnalizedCodeInfo file : files_) {
                Runtime rt = Runtime.getRuntime();
                String run = "cppcheck  --enable=all --template {file}<||>{line}<||>{severity}<||>{message}<||>{id}";
                for(String include : file.includes())
                    run += " -I \"" + include + "\"";
                run += " \"" + file.fullPath() + "\"";
                Process pr = rt.exec( run, (String[])(null), new File(file.projectRoot()));

                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

                String line=null;

                while((line=input.readLine()) != null) {
                    String[] ss = line.split("\\<\\|\\|\\>");
                    if(ss.length>=4) {
                        StaticAnalysisItem item = new StaticAnalysisItem(detectType(ss[2]), file.fullPath(), Integer.parseInt(ss[1]), file.fileName(), ss[3]);
                        model_.addItem(item);
                    }
                }
                
                pr.waitFor();
            }
            
	} catch (Exception ex) {
            ErrorManager.getDefault().notify(ErrorManager.WARNING, ex);
        }
    }
}
