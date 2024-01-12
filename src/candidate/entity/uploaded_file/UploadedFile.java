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

    public UploadedFile() {
    }

    public UploadedFile(Integer candidateId, String name, String extension, String fileUrl, String savedUrl, Integer size) {
        this.candidateId = candidateId;
        this.name = name;
        this.extension = extension;
        this.fileUrl = fileUrl;
        this.savedUrl = savedUrl;
        this.size = size;
    }

    public UploadedFile(Integer id, Integer candidateId, String name, String extension, String fileUrl, String savedUrl, Integer size, LocalDateTime createdDate) {
        this.id = id;
        this.candidateId = candidateId;
        this.name = name;
        this.extension = extension;
        this.fileUrl = fileUrl;
        this.savedUrl = savedUrl;
        this.size = size;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSavedUrl() {
        return savedUrl;
    }

    public void setSavedUrl(String savedUrl) {
        this.savedUrl = savedUrl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "UploadedFile{" +
                "id=" + id +
                ", candidateId=" + candidateId +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", savedUrl='" + savedUrl + '\'' +
                ", size=" + size +
                ", createdDate=" + createdDate +
                '}';
    }
}
