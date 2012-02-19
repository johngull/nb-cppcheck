package org.johngull.netbeans.cppcheck;

import java.util.HashMap;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import org.johngull.netbeans.cppcheck.StaticAnalysisItem;

/**
 *
 * @author Vitaly Bondar
 */
public class StaticAnalysisModel extends  AbstractTableModel
{
    private HashMap<StaticAnalysisItem.SAErrorType, Boolean> activated_ = new HashMap<StaticAnalysisItem.SAErrorType, Boolean>();    
    private HashMap<StaticAnalysisItem.SAErrorType, Vector<StaticAnalysisItem> > errors_ = new HashMap<StaticAnalysisItem.SAErrorType, Vector<StaticAnalysisItem> >();
    private int rowCount_;
    
    private HashMap<StaticAnalysisItem.SAErrorType, Integer> typeRowBegin_ = new HashMap<StaticAnalysisItem.SAErrorType, Integer>();
    private HashMap<StaticAnalysisItem.SAErrorType, Integer> typeRowEnd_ = new HashMap<StaticAnalysisItem.SAErrorType, Integer>();
    
    final static private StaticAnalysisItem.SAErrorType[] types = {
                    StaticAnalysisItem.SAErrorType.Error,
                    StaticAnalysisItem.SAErrorType.Warning,
                    StaticAnalysisItem.SAErrorType.Portability,
                    StaticAnalysisItem.SAErrorType.Performance,
                    StaticAnalysisItem.SAErrorType.Style,
                    StaticAnalysisItem.SAErrorType.Information,
                    StaticAnalysisItem.SAErrorType.UnusedFunction};
    
    public StaticAnalysisModel() {
        rowCount_ = 0;
        for(StaticAnalysisItem.SAErrorType t : types) {
            if(activated_.get(t)==null)
            {
                activated_.put(t, Boolean.TRUE);
                errors_.put(t, new Vector<StaticAnalysisItem>());
            }
        }
    }
    
    public void clear()  {
        for(StaticAnalysisItem.SAErrorType t : types) {
                errors_.get(t).clear();
        }
    }
    
    public String getColumnName(int col) {
        switch(col) {
            case 0:
                return "File";
            case 1:
                return "Type";
            case 2:
                return "Description";
            default:
                return "";
        }
    }
    public int getRowCount() { 
        return rowCount_; 
    }
    public int getColumnCount() { 
        return 3; 
    }
    
    public StaticAnalysisItem rowItem(int row) {
        if(row >= rowCount_)
            return null;
        
        StaticAnalysisItem item = null;
        for(StaticAnalysisItem.SAErrorType t : types) {
            if(activated_.get(t) && row>=typeRowBegin_.get(t) && row<typeRowEnd_.get(t)) {
                item = errors_.get(t).get(row-typeRowBegin_.get(t));
                break;
            }
        }
        return item;
    } 
    
    public Object getValueAt(int row, int col) {
        
        StaticAnalysisItem item = rowItem(row);
        if(item==null)
            return null;
        
        String res = new String();
        switch(col) {
            case 0:
                res = item.fileName() + " (" + String.valueOf(item.line()) + " )";
                break;
            case 1:
                res = typeString(item.type());
                break;
            case 2:
                res= item.description();
                break;
        }
        
        return res;
    }
    
    
    public void recalcRowCount() {
        rowCount_ = 0;
        for(StaticAnalysisItem.SAErrorType t : types) {
            if(activated_.get(t))
            {
                typeRowBegin_.put(t, rowCount_);
                rowCount_ += errors_.get(t).size();
                typeRowEnd_.put(t, rowCount_);
            }
            else
            {
                typeRowBegin_.put(t, -1);
                typeRowEnd_.put(t, -1);
            }
        }
        
    }
    
    public void addItem(StaticAnalysisItem item) {
        errors_.get(item.type()).add(item);
        recalcRowCount();
        fireTableRowsUpdated(typeRowBegin_.get(item.type()), rowCount_-1);
    }
    
    private String typeString(StaticAnalysisItem.SAErrorType type) {
        switch(type) {
            case Error:
                return "Error";
            case Warning:
                return "Warning";
            case Portability:
                return "Portability";
            case Performance:
                return "Performance";
            case Style:
                return "Style";
            case Information:
                return "Information";
            case UnusedFunction:
                return "Unused function";
            default:
                return "?";
        }
    }
}