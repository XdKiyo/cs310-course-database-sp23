package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import com.github.cliftonlabs.json_simple.*;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import edu.jsu.mcis.cs310.tas_sp23.Shift;

/**
 * 
 * Utility class for DAOs.  This is a final, non-constructable class containing
 * common DAO logic and other repeated and/or standardized code, refactored into
 * individual static methods.
 * 
 */
public final class DAOUtility {
    
    //int totalMinutes;
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift)
    {
        int totalMinutes = 0;
    /*            
        for (Punch punch : dailypunchlist) 
        {
            punch.adjust(shift);
            //totalMinutes =           
        }
    */    
        for (int i = 0; i < dailypunchlist.size(); i++)
        {
            //totalMinutes += dailypunchlist[i];
                    
        }
        
        
        return totalMinutes;
        
    }

    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
            

}