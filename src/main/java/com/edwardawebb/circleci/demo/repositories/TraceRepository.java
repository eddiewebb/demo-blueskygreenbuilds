package com.edwardawebb.circleci.demo.repositories;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TraceRepository extends InMemoryHttpTraceRepository {
    
}
