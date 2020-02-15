package com.example.demo.config.hbase;

import com.example.demo.common.util.AppCtx;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HbaseUtils {

    private static Admin getAdmin(){
        Connection connection = AppCtx.getBean(Connection.class);
        Admin admin = null;
        try {
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * 获取表
     *
     * @param tableName 表名
     * @return
     */
    public static Table getTable(String tableName) {
        TableName tablename = TableName.valueOf(tableName);
        Connection connection = AppCtx.getBean(Connection.class);
        Table table = null;
        try {
            table = connection.getTable(tablename);
        } catch (IOException e) {
            log.error("hbase获取表报错", e);
        }
        return table;
    }

    /**
     * 创建表
     *
     * @param tableName 表名
     * @param cols      列
     */
    public static void createTable(String tableName, String[] cols) {
        try {
            Admin admin = getAdmin();
            TableName tablename = getTable(tableName).getName();//定义表名
            //TableDescriptor对象通过TableDescriptorBuilder构建；
            TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(tablename);
            List<ColumnFamilyDescriptor> familyList = new ArrayList<>();
            for (String col : cols) {
                ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(col)).build();//构建列族对象
                familyList.add(family);
            }
            tableDescriptor.setColumnFamilies(familyList);//设置列族
            admin.createTable(tableDescriptor.build());//创建表
        } catch (IOException e) {
            log.error("hbase创建表报错", e);
        }

    }
    /**
     * 删除表
     *
     * @param tableName
     * @return
     */
    public static boolean deleteTable(String tableName) {
        try {
            Admin admin = getAdmin();
            TableName table = getTable(tableName).getName();
            admin.disableTable(table);
            admin.deleteTable(table);
        } catch (IOException e) {
            log.error("hbase删除表报错", e);
        }
        return true;
    }



    /**
     * @param tableName 表名
     * @param rowKey    行键
     * @param family    列族名
     * @param qualifier 列名
     * @param value     值
     */
    public static void put(String tableName, String rowKey, String family, String qualifier, String value) {
        Table table = getTable(tableName);
        Put put = new Put(rowKey.getBytes());
        //参数：1.列族名  2.列名  3.值
        put.addColumn(family.getBytes(), qualifier.getBytes(), value.getBytes());
        try {
            table.put(put);
        } catch (IOException e) {
            log.error("hbase插入记录报错", e);
        }
    }

    /**
     * 获取单条数据
     *
     * @param tableName 表名
     * @param rowKey    唯一标识
     * @return 查询结果
     */
    public static Result getRow(String tableName, String rowKey) {
        try {
            Table table = getTable(tableName);
            Get get = new Get(Bytes.toBytes(rowKey));
            return table.get(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;}


}
