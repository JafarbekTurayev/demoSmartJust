package com.example.demosmartjust.repository;

import com.example.demosmartjust.entity.ApplicationFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ApplicationFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationFileRepository extends JpaRepository<ApplicationFile, Long> {
    @Query(
        value = "SELECT i FROM ApplicationFile i WHERE 1 = 1" +
        " and (:id is null or i.id = :id)" +
        " and ((:fileStorageHashId is null or :fileStorageHashId = '') or LOWER(i.fileStorageHashId) LIKE LOWER(concat('%', :fileStorageHashId,'%')))" +
        " and (:applicationId is null or i.application.id = :applicationId)",
        nativeQuery = false
    )
    Page<ApplicationFile> findAllPaging(
        Pageable pageable,
        @Param("id") Long id,
        @Param("fileStorageHashId") String fileStorageHashId,
        @Param("applicationId") Long applicationId
    );

    List<ApplicationFile> findAllByOrderByIdAsc();

    int deleteAllByApplicationId(Long applicationId);

    List<ApplicationFile> findAllByApplicationId(Long applicationId);
}
