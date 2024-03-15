package com.example.demosmartjust.repository;

import com.example.demosmartjust.entity.Application;
import com.example.demosmartjust.entity.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query(value = "SELECT a FROM Application a " +
            "WHERE (:id is null or a.id = :id) " +
            "AND (:status is null or a.applicationStatus = :status) " +
            "AND (:outDateStr is null or a.outDateStr = :outDateStr) " +
            "AND (:outNumber is null or a.outNumber = :outNumber) " +
            "AND (:inDateStr is null or a.inDateStr = :inDateStr) " +
            "AND (:inNumber is null or a.inNumber = :inNumber)")
    Page<Application> findAllPaging(
            Pageable pageable,
            @Param("id") Long id,
            @Param("status") ApplicationStatus status,
            @Param("outDateStr") String outDateStr,
            @Param("outNumber") String outNumber,
            @Param("inDateStr") String inDateStr,
            @Param("inNumber") String inNumber
    );
}
