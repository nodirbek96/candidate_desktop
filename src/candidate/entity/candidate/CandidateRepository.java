/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candidate.entity.candidate;

import candidate.entity.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandidateRepository implements CandidateCallbacks {

    private Connection connection;

    @Override
    public boolean createCandidateTable() {
        connection = DBConnection.makeConnection();
        boolean status = false;
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "CREATE TABLE IF NOT EXISTS candidates "
                    + "(id int(11) NOT NULL AUTO_INCREMENT, "
                    + "user_id int(11) NOT NULL, "
                    + "firstname VARCHAR(255) NOT NULL, "
                    + "lastname VARCHAR(255) NOT NULL, "
                    + "middlename VARCHAR(255) NOT NULL, "
                    + "birthdate DATE NOT NULL, "
                    + "address VARCHAR(255), "
                    + "phone VARCHAR(255), "
                    + "job_place VARCHAR(255), "
                    + "occupation VARCHAR(255) NOT NULL, "
                    + "education_place VARCHAR(255), "
                    + "education VARCHAR(255), "
                    + "relative VARCHAR(255), "
                    + "position VARCHAR(255), "
                    + "passport VARCHAR(255), "
                    + "end_date DATE, "
                    + "result VARCHAR(255), "
                    + "created_date TIMESTAMP default CURRENT_TIMESTAMP NOT NULL, "
                    + "PRIMARY KEY(id))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("candidate table created");
            status = true;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public CandidateDto insertCandidate(CandidateDto candidate) {
        CandidateDto insertedCandidate = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "INSERT INTO candidates(user_id,firstname,lastname,middlename,birthdate,address,phone,job_place,"
                    + "occupation,education_place,education,relative,position,passport,end_date,result) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, candidate.getUserId());
            preparedStatement.setString(2, candidate.getFirstname());
            preparedStatement.setString(3, candidate.getLastname());
            preparedStatement.setString(4, candidate.getMiddleName());
            preparedStatement.setDate(5, Date.valueOf(candidate.getBirthDate()));
            preparedStatement.setString(6, candidate.getAddress());
            preparedStatement.setString(7, candidate.getPhone());
            preparedStatement.setString(8, candidate.getJobPlace());
            preparedStatement.setString(9, candidate.getOccupation());
            preparedStatement.setString(10, candidate.getEducationPlace());
            preparedStatement.setString(11, candidate.getEducation());
            preparedStatement.setString(12, candidate.getRelative());
            preparedStatement.setString(13, candidate.getPosition());
            preparedStatement.setString(14, candidate.getPassport());
            preparedStatement.setDate(15, Date.valueOf(candidate.getEndDate()));
            preparedStatement.setString(16, candidate.getResult());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                query = "SELECT * FROM candidates WHERE id='" + id + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                insertedCandidate = new CandidateDto();
                while (resultSet.next()) {
                    insertedCandidate.setId(resultSet.getInt(1));
                    insertedCandidate.setUserId(resultSet.getInt(2));
                    insertedCandidate.setFirstname(resultSet.getString(3));
                    insertedCandidate.setLastname(resultSet.getString(4));
                    insertedCandidate.setMiddleName(resultSet.getString(5));
                    insertedCandidate.setBirthDate(resultSet.getDate(6).toLocalDate());
                    insertedCandidate.setAddress(resultSet.getString(7));
                    insertedCandidate.setPhone(resultSet.getString(8));
                    insertedCandidate.setJobPlace(resultSet.getString(9));
                    insertedCandidate.setOccupation(resultSet.getString(10));
                    insertedCandidate.setEducationPlace(resultSet.getString(11));
                    insertedCandidate.setEducation(resultSet.getString(12));
                    insertedCandidate.setRelative(resultSet.getString(13));
                    insertedCandidate.setPosition(resultSet.getString(14));
                    insertedCandidate.setPassport(resultSet.getString(15));
                    insertedCandidate.setEndDate(resultSet.getDate(16).toLocalDate());
                    insertedCandidate.setResult(resultSet.getString(17));
                    insertedCandidate.setCreatedDate(resultSet.getTimestamp(18).toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return insertedCandidate;
    }

    @Override
    public CandidateDto updateCandidate(CandidateDto candidate) {
        CandidateDto updatedCandidate = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "UPDATE candidates SET firstname=?,lastname=?, middlename=?, "
                    + "birthdate=?, address=?, phone=?, job_place=?, occupation=?, "
                    + "education_place=?, education=?, relative=?, position=?, passport=?, "
                    + "end_date=?, result=? WHERE id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, candidate.getFirstname());
            preparedStatement.setString(2, candidate.getLastname());
            preparedStatement.setString(3, candidate.getMiddleName());
            preparedStatement.setDate(4, Date.valueOf(candidate.getBirthDate()));
            preparedStatement.setString(5, candidate.getAddress());
            preparedStatement.setString(6, candidate.getPhone());
            preparedStatement.setString(7, candidate.getJobPlace());
            preparedStatement.setString(8, candidate.getOccupation());
            preparedStatement.setString(9, candidate.getEducationPlace());
            preparedStatement.setString(10, candidate.getEducation());
            preparedStatement.setString(11, candidate.getRelative());
            preparedStatement.setString(12, candidate.getPosition());
            preparedStatement.setString(13, candidate.getPassport());
            preparedStatement.setDate(14, Date.valueOf(candidate.getEndDate()));
            preparedStatement.setString(15, candidate.getResult());
            preparedStatement.setInt(16, candidate.getId());
            preparedStatement.execute();
            updatedCandidate = candidate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedCandidate;
    }

    @Override
    public CandidateDto getCandidateById(Integer id) {
        CandidateDto candidate = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "SELECT * FROM candidates WHERE id='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                candidate = new CandidateDto(
                        resultSet.getInt(1), //id
                        resultSet.getInt(2), // userid
                        resultSet.getString(3), // firstname
                        resultSet.getString(4), // lastname
                        resultSet.getString(5), // middlename 
                        resultSet.getDate(6).toLocalDate(), // birthdate
                        resultSet.getString(7), //address
                        resultSet.getString(8), // phone
                        resultSet.getString(9), //job place
                        resultSet.getString(10), // occupation
                        resultSet.getString(11), // education place 
                        resultSet.getString(12), // education
                        resultSet.getString(13), // relative 
                        resultSet.getString(14), // position
                        resultSet.getString(15), // passport
                        resultSet.getDate(16).toLocalDate(), // enddate
                        resultSet.getString(17), // result
                        resultSet.getTimestamp(18).toLocalDateTime() // created
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return candidate;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<CandidateDto> list;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "SELECT * FROM candidates ORDER BY id DESC ";
            list = getCandidates(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private List<CandidateDto> getCandidates(String query) throws SQLException {
        List<CandidateDto> list;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new CandidateDto(
                    resultSet.getInt(1), //id
                    resultSet.getInt(2), // userid
                    resultSet.getString(3), // firstname
                    resultSet.getString(4), // lastname
                    resultSet.getString(5), // middlename 
                    resultSet.getDate(6).toLocalDate(), // birthdate
                    resultSet.getString(7), //address
                    resultSet.getString(8), // phone
                    resultSet.getString(9), //job place
                    resultSet.getString(10), // occupation
                    resultSet.getString(11), // education place 
                    resultSet.getString(12), // education
                    resultSet.getString(13), // relative 
                    resultSet.getString(14), // position
                    resultSet.getString(15), // passport
                    resultSet.getDate(16).toLocalDate(), // enddate
                    resultSet.getString(17), // result
                    resultSet.getTimestamp(18).toLocalDateTime() // created
            ));
        }
        return list;
    }

    @Override
    public boolean deleteCandidateById(Integer id) {
        boolean status = false;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "DELETE FROM candidates WHERE id='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            status = true;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public List<CandidateDto> searchByFirstFirstnameOrLastnameOrMiddlename(String suffix) {
        List<CandidateDto> list = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "SELECT * FROM candidates WHERE firstname LIKE '%" + suffix + "%' OR lastname LIKE '%" + suffix + "%' OR middlename LIKE '%" + suffix + "%'";
            list = getCandidates(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<CandidateDto> searchByPhoneOrPassport(String suffix) {
        List<CandidateDto> list = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "SELECT * FROM candidates WHERE phone LIKE '%" + suffix + "%' OR passport LIKE '%" + suffix + "%'";
            list = getCandidates(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<CandidateDto> searchByJobPlaceOrOccupationOrEducationOrPosition(String suffix) {
        List<CandidateDto> list = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "SELECT * FROM candidates WHERE job_place LIKE '%" + suffix + "%' OR occupation LIKE '%" + suffix + "%' OR education LIKE '%" + suffix + "%' OR "
                    + " position LIKE '%" + suffix + "%'";
            list = getCandidates(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<CandidateDto> searchByBirthDate(String suffix) {
        List<CandidateDto> list = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "SELECT * FROM candidates WHERE birthdate LIKE '%" + suffix + "%'";
            list = getCandidates(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

}
