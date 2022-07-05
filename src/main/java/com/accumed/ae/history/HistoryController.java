package com.accumed.ae.history;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

@RestController
@RequestMapping(path = "/history")
public class HistoryController {
    private final HistoryServices historyServices;
//    private final WebConfig webConfig;
//    private final InterceptorRegistry registry;
//    private final WebConfig.LoggerInterceptor x;

    @Autowired

    public HistoryController(HistoryServices historyServices) {
        this.historyServices = historyServices;
    }

    @GetMapping(path = "/getLogs", name = "getHistory")
    public List<History> getHistory(){
        return historyServices.getHistory();
    }

    @DeleteMapping(path="/deleteLogs")
    public void deleteLogs(){
        historyServices.deleteAll();
    }
}
