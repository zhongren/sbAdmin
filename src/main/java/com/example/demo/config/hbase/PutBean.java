package com.example.demo.config.hbase;

import lombok.Data;

@Data
public class PutBean {
    String rowKey;
    String family;
    String qualifier;
    String value;
}
