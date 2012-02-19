package org.johngull.netbeans.cppcheck;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import java.io.File;

import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.cookies.OpenCookie;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.text.NbDocument;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Vitaly Bondar
 */
public class SourceOpener {
    
    private SourceOpener() {
        
    }
    
    public static void OpenSourceLine(String fullPath, int line) {
        //open
        DataObject data;
        try {
             data = DataObject.find(FileUtil.toFileObject(new File(fullPath)));
        }
        catch(DataObjectNotFoundException ex) {
            return;
        }            
        data.getLookup().lookup(OpenCookie.class).open();
           

        //goto line
        JTextComponent editor = EditorRegistry.lastFocusedComponent();// focusedComponent();//lastfocused
        if(editor!=null)
        {
            Document doc = editor.getDocument();
            editor.setCaretPosition(NbDocument.findLineOffset((StyledDocument)doc, line-1));
        }
    }
}
