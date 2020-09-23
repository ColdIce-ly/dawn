package com.xiaoyuan.designpattern.abstractfactroy;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:41  
 */
public class OralceDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}
