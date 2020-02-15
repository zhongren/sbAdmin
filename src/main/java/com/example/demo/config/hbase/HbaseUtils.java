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

    private static Admin getAdmin() {
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
     * @param tableName    表名
     * @param columnFamily 列
     */
    public static void createTable(String tableName, String[] columnFamily) {
        try {
            Admin admin = getAdmin();
            TableName tablename = getTable(tableName).getName();//定义表名
            //TableDescriptor对象通过TableDescriptorBuilder构建；
            TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(tablename);
            List<ColumnFamilyDescriptor> familyList = new ArrayList<>();
            for (String cf : columnFamily) {
                ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build();//构建列族对象
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
    public static void deleteTable(String tableName) {
        try {
            Admin admin = getAdmin();
            TableName table = getTable(tableName).getName();
            if (admin.tableExists(table)) {
                admin.disableTable(table);
                admin.deleteTable(table);
            }
        } catch (IOException e) {
            log.error("hbase删除表报错", e);
        }
    }


    /**
     * @param tableName    表名
     * @param rowKey       行键
     * @param columnFamily 列族名
     * @param qualifier    列名
     * @param value        值
     */
    public static void putRow(String tableName, String rowKey, String columnFamily, String qualifier, String value) {
        Table table = getTable(tableName);
        Put put = new Put(rowKey.getBytes());
        //参数：1.列族名  2.列名  3.值
        put.addColumn(columnFamily.getBytes(), qualifier.getBytes(), value.getBytes());
        try {
            table.put(put);
        } catch (IOException e) {
            log.error("hbase插入记录报错", e);
        }
    }

    /**
     * 批量插入数据
     *
     * @param tableName
     * @param puts
     * @return
     */
    public static void putRows(String tableName, List<PutBean> puts) {
        try {
            Table table = getTable(tableName);
            List<Put> putList = new ArrayList<>();
            for (PutBean putBean : puts) {
                Put put = new Put(putBean.getRowKey().getBytes());
                //参数：1.列族名  2.列名  3.值
                put.addColumn(putBean.getFamily().getBytes(), putBean.getQualifier().getBytes(), putBean.getValue().getBytes());
            }
            table.put(putList);
        } catch (IOException e) {
            log.error("hbase批量插入记录报错", e);
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
            Result result = table.get(get);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getValue(String tableName, String rowKey, String columnFamily, String qualifier) {
        Result result = getRow(tableName, rowKey);
        if (null!=result){
            byte [] value = result.getValue(Bytes.toBytes(columnFamily),
                    Bytes.toBytes(qualifier));
            return Bytes.toString(value);
        }
        return null;
    }

    /**
     * 删除一条记录
     *
     * @param tableName 表名
     * @param rowKey    唯一标识行
     * @return 是否删除成功
     */
    public static void deleteRow(String tableName, String rowKey) {
        try {
            Table table = getTable(tableName);
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除列族
     *
     * @param tableName
     * @param columnFamily
     * @return
     */
    public static void deleteFamily(String tableName, String columnFamily) {
        try {
            HBaseAdmin admin = (HBaseAdmin) getAdmin();
            Table table = getTable(tableName);
            admin.deleteColumnFamily(table.getName(), columnFamily.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQualifier(String tableName, String rowKey, String columnFamily, String qualifier) {
        try {
            Table table = getTable(tableName);
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
