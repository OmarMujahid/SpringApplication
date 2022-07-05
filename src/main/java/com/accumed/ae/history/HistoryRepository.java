package com.accumed.ae.history;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
    History findByClientID(Long ClientID);
    List<History> findAllByClientID(Long ClientID);
    @Query(value = "SELECT * FROM dbo.history t WHERE t.time_of_request > :timer AND t.clientid = :id AND t.type = :type", nativeQuery = true)
    List<History> findHistory(@Param("timer") java.time.LocalDateTime timer, @Param("id") Long id, @Param("type") String type);
}

