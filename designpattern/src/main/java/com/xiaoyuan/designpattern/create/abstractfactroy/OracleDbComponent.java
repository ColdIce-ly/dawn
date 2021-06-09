package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:41  
 */
public class OracleDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}
