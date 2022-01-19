package carsharing.command;

import carsharing.Table;

interface Command<T extends Table> {
    void execute();
    void setT(T t);
}
