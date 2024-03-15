package com.example.demosmartjust.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A CooperationContractFile.
 */
@Entity
@Table(name = "application_file")
public class ApplicationFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICATION_FILE_ID")
    @SequenceGenerator(name = "SEQ_APPLICATION_FILE_ID", sequenceName = "SEQ_APPLICATION_FILE_ID", allocationSize = 1, initialValue = 1)
    private Long id;

    @NotNull
    @Column(name = "file_storage_hash_id", nullable = false)
    private String fileStorageHashId;
    @NotNull
    @Column(name = "smart_just_file_id", nullable = false)
    private String smartJustFileId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ApplicationFileType type;

    public ApplicationFileType getType() {
        return type;
    }

    public void setType(ApplicationFileType type) {
        this.type = type;
    }

    public String getSmartJustFileId() {
        return smartJustFileId;
    }

    public void setSmartJustFileId(String smartJustFileId) {
        this.smartJustFileId = smartJustFileId;
    }

    @ManyToOne
    private Application application;

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getFileStorageHashId() {
        return this.fileStorageHashId;
    }

    public void setFileStorageHashId(String fileStorageHashId) {
        this.fileStorageHashId = fileStorageHashId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationFile)) {
            return false;
        }
        return id != null && id.equals(((ApplicationFile) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
