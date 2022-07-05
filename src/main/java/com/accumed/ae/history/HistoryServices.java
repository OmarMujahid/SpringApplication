package com.accumed.ae.history;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hibernate.loader.Loader.SELECT;

@Service
public class HistoryServices {
    @Value("${numberOfRequestsAllowed}")
    private Integer numberOfRequestsAllowed;
    private final HistoryRepository historyRepository;
            @Autowired

    public HistoryServices(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getHistory() {
                return  historyRepository.findAll();
    }

    public void submitRequest(String type ,String clientID , String uri, String method, String ipAddress, Integer statusCode, String requestBody,
                              java.time.LocalDateTime timeOfRequest, String responseBody) {
//                String type = null;
//                if(uri.contains("client/submit")){
//                    type = "Generate Code";
//                }else if(uri.contains("client/verificationCode")){
//                        type = "Submit Code";
//                }
                History history = new History(
                        method,
                        ipAddress,
                        statusCode,
                        requestBody,
                        timeOfRequest,
                        responseBody,
                        uri,
                        type, clientID);
                historyRepository.save(history);
    }

    public void deleteAll() {
        historyRepository.deleteAll();
            }
    java.time.LocalDateTime now = LocalDateTime.now();



    public List<History> requestsPastTime(Long clientID, String type){
        java.time.LocalDateTime now = LocalDateTime.now().minusMinutes(15L);
    return historyRepository.findHistory(now, clientID, type);
    }
    public boolean isRepeated(Long clientID, String type){
        return requestsPastTime(clientID,type).size() < numberOfRequestsAllowed;
    }


}
