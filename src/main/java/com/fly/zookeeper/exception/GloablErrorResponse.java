package com.fly.zookeeper.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GloablErrorResponse {
    private String message;
    private String errReason;
}
