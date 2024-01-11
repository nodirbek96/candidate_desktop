/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candidate.entity.uploaded_file;

import java.time.LocalDateTime;

public class UploadedFile {
    private Integer id;
    private Integer candidateId;
    private String name;
    private String extension;
    private String fileUrl;
    private String savedUrl;
    private Integer size;
    private LocalDateTime createdDate;
}
