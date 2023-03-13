package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Employee;
import edu.jsu.mcis.cs310.tas_sp23.EventType;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import java.sql.*;
import java.time.LocalDateTime;

public class PunchDAO {

    private static final String QUERY_FIND = "SELECT * FROM event WHERE id = ?";

    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public Punch create(Punch punch){
        int key = 0;
        ResultSet rs = null;
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        Employee employee = employeeDAO.find(punch.getBadge());   
        
    return punch;
}
    
    // public PunchDAO list(Punch punch){
        
    
    // working on adding list method
    
    public Punch find(int id){
        Punch punch = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        //Create badge variable. Use BadgeDAO to find it.

                        BadgeDAO badgeDAO = new BadgeDAO(daoFactory);
                        String badgeId = rs.getString("badgeid");
                        Badge badge = badgeDAO.find(badgeId);

                        //Get eventtype. punchtype

                        int eventtypeid = rs.getInt("eventtypeid");
                        EventType punchtype = EventType.values()[eventtypeid];

                        int terminalid = rs.getInt("terminalid");

                        //Get timestamp from database. It must be casted into LocalDateTime. 

                        java.sql.Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
                        timestamp = rs.getTimestamp("timestamp");
                        LocalDateTime originalTimeStamp = timestamp.toLocalDateTime();  

                        //create punch variable.

                        punch = new Punch(id, terminalid, badge, originalTimeStamp, punchtype);
                        
                        
                        
                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return punch;

    }

}
//create method
 public int create(punch) {

        int key = 0;
        ResultSet rs = null;
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        Employee employee = employeeDAO.find(punch.getBadge());

       if (punch.getTerminalId() == employee.getDepartment().getTerminalId() || punch.getTerminalId() == 0) {
                PreparedStatement ps = null;
            try {
                  Connection conn = daoFactory.getConnection();
                if (conn.isValid(0)) {
                    final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(punch.getOriginalTimestamp());
                    final int eventTypeId = EventType.valueOf(punch.getPunchType().name()).ordinal();

                    ps = conn.prepareStatement(QUERY_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, punch.getTerminalId());
                    ps.setString(2, punch.getBadge().getId());
                    ps.setString(3, date);
                    ps.setInt(4, eventTypeId);

                    int result = ps.executeUpdate();
                    if (result > 0) {

                        rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            key = rs.getInt(1);
                        }

                    }
                }
