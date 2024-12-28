package com.advanced.trace;

//로그를 시작할 때의 상태정보, 로그 종료 시 사용

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;   //로그 시작 시간 -> 전체 수행 시간을 구함
    private String message;     //시작 시 사용한 로그 메시지

}
