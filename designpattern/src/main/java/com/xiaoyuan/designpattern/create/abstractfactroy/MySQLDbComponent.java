package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 * @date : 2020/9/23 16:41  
 */
public class MySQLDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new MySQLConnection();
    }

    @Override
    public ICommand getCommand() {
        return new MySQLCommand();
    }
}
