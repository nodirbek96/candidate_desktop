package candidate.entity.uploaded_file;

import candidate.entity.DBConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

public class UploadedFileRepository implements UploadedFileCallbacks {
    private Connection connection;

    @Override
    public boolean createFileUploadTable() {
        connection = DBConnection.makeConnection();
        boolean status = false;
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            String query = "CREATE TABLE IF NOT EXISTS files " +
                    "(id int(11) NOT NULL AUTO_INCREMENT, " +
                    "candidate_id int(11) NOT NULL, " +
                    "file_name VARCHAR(255) NOT NULL, " +
                    "file_url VARCHAR(255) NOT NULL, " +
                    "saved_url VARCHAR(255), " +
                    "size int(11), " +
                    "payload BLOB NOT NULL, " +
                    "created_date TIMESTAMP default CURRENT_TIMESTAMP NOT NULL, " +
                    "PRIMARY KEY(id))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("files table created");
            status = true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public UploadedFile insertFile(UploadedFile uploadedFile) {
        connection = DBConnection.makeConnection();
        UploadedFile insertedUploadedFile = null;
        try {
            if (connection.isClosed()) {
                connection = DBConnection.makeConnection();
            }
            FileInputStream fileInputStream = new FileInputStream(uploadedFile.getFileUrl());
            String query = "INSERT INTO files(candidate_id,file_name,file_url,saved_url,size,payload) " +
                    "VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, uploadedFile.getCandidateId());
            preparedStatement.setString(2, uploadedFile.getName());
            preparedStatement.setString(3, uploadedFile.getFileUrl());
            preparedStatement.setString(4, uploadedFile.getSavedUrl());
            preparedStatement.setInt(5, uploadedFile.getSize());
            preparedStatement.setBlob(6, fileInputStream);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                query = "SELECT * FROM files WHERE id='" + id + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                insertedUploadedFile = new UploadedFile();
                while (resultSet.next()) {
                    insertedUploadedFile.setId(resultSet.getInt(1));
                    insertedUploadedFile.setCandidateId(resultSet.getInt(2));
                    insertedUploadedFile.setName(resultSet.getString(3));
                    insertedUploadedFile.setFileUrl(resultSet.getString(4));
                    insertedUploadedFile.setSavedUrl(resultSet.getString(5));
                    insertedUploadedFile.setSize(resultSet.getInt(6));
                    insertedUploadedFile.setCreatedDate(resultSet.getTimestamp(8).toLocalDateTime());
                }

            }
        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return insertedUploadedFile;
    }

    @Override
    public UploadedFile getById(Integer id) {
        return null;
    }

    @Override
    public List<UploadedFile> getAllFiles() {
        return null;
    }
}
