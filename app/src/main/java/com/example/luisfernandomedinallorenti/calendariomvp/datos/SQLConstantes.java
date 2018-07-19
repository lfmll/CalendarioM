package com.example.luisfernandomedinallorenti.calendariomvp.datos;

public class SQLConstantes {
    public static final String DB_NAME="dbagenda.db";
    public static final String TABLE_EVENTO="eventos";

    public static final String COLUMN_ID="id";
    public static final String COLUMN_NOMBRE="nombre";
    public static final String COLUMN_DESCRIPCION="descripcion";
    public static final String COLUMN_FECHA="fecha";

    public static final String SQL_CREATE_TABLE_EVENTO=
            "CREATE TABLE "+TABLE_EVENTO+"("+
                    COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_NOMBRE+" TEXT NOT NULL,"+
                    COLUMN_DESCRIPCION+" TEXT,"+
                    COLUMN_FECHA+" DATETIME NOT NULL );";
    public static final String SQL_DELETE="DROP TABLE"+TABLE_EVENTO;
    public static final String[] ALL_COLUMNS={COLUMN_NOMBRE,COLUMN_DESCRIPCION,COLUMN_FECHA};
    public static final String SEARCH_BY_NOMBRE="nombre=?";
    public static final String SELECT_ALL_EVENTO="SELECT * FROM eventos";
}
