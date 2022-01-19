package carsharing.command;

import carsharing.Table;

interface Command {
    void execute();
    void setT(Table ... t);
}
