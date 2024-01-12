package candidate.entity.uploaded_file;

import java.util.List;

public interface UploadedFileCallbacks {
    boolean createFileUploadTable();
    UploadedFile insertFile(UploadedFile uploadedFile);
    UploadedFile getById(Integer id);
    List<UploadedFile> getAllFiles();

}
