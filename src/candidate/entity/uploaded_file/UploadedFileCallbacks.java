package candidate.entity.uploaded_file;

import java.io.File;
import java.util.List;

public interface UploadedFileCallbacks {
    boolean createFileUploadTable();
    UploadedFile insertFile(UploadedFile uploadedFile);
    UploadedFile getById(Integer id);
    List<UploadedFile> getAllFiles();
    String isFileUploaded(Integer candidateId);
    boolean downloadFile(Integer candidateId, File file);

}